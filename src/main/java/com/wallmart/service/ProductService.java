package com.wallmart.service;


import java.io.ObjectStreamException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wallmart.dto.MaterialDto;
import com.wallmart.enumurl.WallmartEnumUrl;
import com.wallmart.model.ImageEntities;
import com.wallmart.model.Item;
import com.wallmart.repository.ImageEntitiesRepository;
import com.wallmart.repository.ItemRepository;
import com.wallmart.signaturegenerator.SignatureGenerator;

@Service
public class ProductService {
	
	//private static Long Item = null;

	@Autowired
	SignatureGenerator signatureGenerator;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ImageEntitiesRepository imageEntitiesRepository;
	
	@Autowired
	EntityManager entityManager;
	
    WallmartEnumUrl wallmartUrl;

	@Value("${consumerid}")
	private String consumerId;

	@Value("${privatekeyversion}")
	private String privatekeyVersion;

	@Value("${wallmartproducturl-tv}")
	private String wallmartTvUrl;
	
	@Value("${wallmartproducturl-washing}")
	private String wallmartWashingMachineUrl;
	
	@Value("${wallmartproducturl-watch}")
	private String wallmartSamsungWatchUrl;
	
	@Value("${wallmartproducturl-air}")
	private String wallmartAirConditionerUrl;
	
	@Scheduled(cron=" 0 0 12 * * *")
	public void getProducts()throws InvalidKeyException, NoSuchAlgorithmException, ObjectStreamException,
	UnsupportedEncodingException, SignatureException {
		
		Long totalResult = null;
		Long itemsValue = null;
		
		Map<String, WallmartEnumUrl> compainUrl = new HashMap<>();
		compainUrl.put(wallmartTvUrl, WallmartEnumUrl.Tv);
		compainUrl.put(wallmartWashingMachineUrl, WallmartEnumUrl.Washing_Machine);
		//compainUrl.put(wallmartSamsungWatchUrl, WallmartEnumUrl.Samsung_Watch);
		compainUrl.put(wallmartAirConditionerUrl, WallmartEnumUrl.Air_Conditioner);
		
		for (Map.Entry<String, WallmartEnumUrl> urls : compainUrl.entrySet()) {
			
			String url = urls.getKey();
			WallmartEnumUrl productUrl =urls.getValue();
			Map<String, Long> totalValue = getProduct(url,productUrl);
		
		totalResult=totalValue.get("totalResults");
		itemsValue = totalValue.get("numItems");
		url=url+"&start=";
		System.out.println("list value - "+getListValue());	
		
		while (itemsValue < totalResult) {
			System.out.println("value : "+itemsValue);
			System.out.println("+++++++++++++++++++++++++++");
			Map<String, Long> totalValues = getProduct(url+itemsValue.intValue(),productUrl);
			itemsValue = itemsValue + totalValues.get("numItems");		    
		}
	}
		
		}
	

	public Map<String, Long> getProduct(String url,WallmartEnumUrl productUrl) throws InvalidKeyException, NoSuchAlgorithmException, ObjectStreamException,
			UnsupportedEncodingException, SignatureException {

       
		long inTimeStamp = System.currentTimeMillis();

		String timeStap = Long.toString(inTimeStamp);

		String generateSignature = signatureGenerator.generateSignatures();

		System.out.println("consumerId : " + consumerId);
		System.out.println("intimestamp : " + inTimeStamp);
		System.out.println("Auth Signature :" + generateSignature);

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("WM_CONSUMER.ID", consumerId);
		headers.set("WM_CONSUMER.INTIMESTAMP", timeStap);
		headers.set("WM_SEC.KEY_VERSION", privatekeyVersion);
		headers.set("WM_SEC.AUTH_SIGNATURE", generateSignature);

		HttpEntity<String> requestItem = new HttpEntity<String>(headers);
		System.out.println("================================================================================");

		ResponseEntity<MaterialDto> response = restTemplate.exchange(url, HttpMethod.GET, requestItem,
				MaterialDto.class);

		
		MaterialDto product = response.getBody();
		
		//System.out.println(response);
		
		Map<String, Long> value = new HashMap<String, Long>();
		value.put("totalResults",product.getTotalResults());
		value.put("numItems",product.getNumItems());

		System.out.println(product.toString());
		System.out.println(value.toString());
        System.out.println(" product name - "+productUrl);
		
     	List<Item> productItems = product.getItems();
		  
		productItems.forEach(items -> {  
		items.setWallmartUrlCategory(productUrl);
		
		Long s = items.getItemId();
		Optional<Item> findBy = itemRepository.findByitemId(s);
		if (findBy.isPresent()) {
			items.setId(findBy.get().getId());
		}
		itemRepository.save(items);
		
		  List<ImageEntities> itemImages = items.getImageEntities(); 
		  itemImages.stream().forEach(image -> {		
			  
		  image.setItem(items); 
		  });
		   imageEntitiesRepository.saveAll(itemImages); 
		 });
		 
		
		/*List<Item> totalItem = product.getItems();
		
		totalItem.forEach(item->{
			
			System.out.println(item.getBrandName());
		});*/
		
		return value;

	}
	public List<Item> getListValue() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Item> createQuerys = criteriaBuilder.createQuery(Item.class);
		
		createQuerys.from(Item.class);
		TypedQuery<Item> typedQuery = entityManager.createQuery(createQuerys);
		
		return typedQuery.getResultList();
		
	}
	 
	public List<Item> findAll(int start, int end){
		 CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		 CriteriaQuery<Item> criteriaQuery = cb.createQuery(Item.class);
		 Root<Item> root = criteriaQuery.from(Item.class);
		 Predicate between = cb.between(root.get("id"), start, end);
		 criteriaQuery.where(between);
		
		List<Item> result =
				entityManager
		                  .createQuery(criteriaQuery)
		 .getResultList();
		
		return result;
}
	
	public Page<Item> getSelectedItems(String brandName,String catagory,Boolean availableOnline,String stock
			,String color,int pageNumber,int pageSize,Pageable pageable) {
		
		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery<Item> createQuery = criteria.createQuery(Item.class);
		Root<Item> root = createQuery.from(Item.class);
	 List<Predicate> getPredicateValue = new ArrayList<>();  
	 //Pageable pg =  PageRequest.of(pageNumber, pageSize);
	 
	if (brandName!=null) {
		getPredicateValue.add(criteria.equal(root.get("brandName"), brandName));
	}
	if (catagory!=null) {
		getPredicateValue.add(criteria.equal(root.get("wallmartUrlCategory").as(String.class),catagory));
	}
	if (availableOnline!=null) {
		getPredicateValue.add(criteria.equal(root.get("availableOnline"),availableOnline));
	}
	if (stock!=null) {
		getPredicateValue.add(criteria.equal(root.get("stock"), stock));
	}
	if (color!=null) {
		getPredicateValue.add(criteria.equal(root.get("color"), color));
	}
		
	
	Predicate predicateValue =criteria.and(getPredicateValue.toArray(new Predicate[getPredicateValue.size()]));
	
	createQuery.where(predicateValue);
	
	//Page<Item> findAllPost = itemRepository.findAll(pg);
		 
	//List<Item> content = findAllPost.getContent();
	//System.out.println("......."+content);
	
//	List<Item> createQuery2 =  entityManager.createQuery(createQuery).setFirstResult((int)pg.getPageNumber())
//			.setFirstResult((int)pg.getPageSize()).getResultList();
			
	//createQuery2.set
	
	List<Item> createQuery2 =  entityManager.createQuery(createQuery).setFirstResult((pageNumber)*pageSize)
			.setMaxResults(pageSize).getResultList();
	int total = createQuery2.size();
	Page<Item> values = new PageImpl<> (createQuery2,pageable,total);
		return values;
		
	}
	
	public List<Item> getPaginationDetail(Integer pageNumber,Integer pageSize){
		
		Pageable pg =  PageRequest.of(pageNumber, pageSize);
		Page<Item> findAllPost = itemRepository.findAll(pg);
		
		 
		List<Item> content = findAllPost.getContent();
		System.out.println("......."+content);
		return content;		
	}
	
}



