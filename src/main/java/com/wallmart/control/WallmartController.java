package com.wallmart.control;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wallmart.model.Item;
import com.wallmart.repository.ItemRepository;
import com.wallmart.service.ProductService;

@RestController
@RequestMapping("/api")
public class WallmartController {

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/get")
	public List<Item> getValue(){
	
	List<Item> listValue = (List<Item>) itemRepository.findAll();
		return listValue;	
		
	}
	
	@GetMapping("get/{id}")
	public List<Item> getid(@PathVariable("id") Long ids) {
		
		List<Item> findAllById =  itemRepository.findByItemId(ids);
		return findAllById;	
	}
	
	@GetMapping("/getid")
	public List<Item> getIds() {
		
		 List<Item> listValue = productService.getListValue();
		return listValue;	
	}
	
	@GetMapping("/findAll")
     public List<Item> findAll(@RequestParam (value = "startPage") int start,
                                   @RequestParam("endpage") int end){
	 List<Item> items = productService.findAll(start, end);
	 
	return items;
			 
	 }
	
	@GetMapping("/findbycatagory")
	public Page<Item> find(@RequestParam(name="brand",required=false)String brandName
			,@RequestParam(name="catagory",required=false)String catagory
			,@RequestParam(name="availableOnline",required=false)Boolean availableOnline
			,@RequestParam(name="stock",required=false)String stock
			,@RequestParam(name="color",required=false)String color
			,@RequestParam("pageNumber")Integer pageNumber
			,@RequestParam("pageSize")Integer pageSize
			,Pageable pageable){
	
	Page<Item> selectedItems = productService.getSelectedItems(brandName,catagory,availableOnline,stock,color,pageNumber,pageSize,pageable);
		
		return selectedItems;
		
	}
	
	@GetMapping("findbypage")
	public List<Item> getPageDetail(@RequestParam("pagenumber")Integer pageNumber,
			@RequestParam("pagesize")Integer pageSize){
			
		List<Item> paginationDetail = productService.getPaginationDetail(pageNumber, pageSize);
		return paginationDetail;		
	}
}
