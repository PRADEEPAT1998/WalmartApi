package com.wallmart.signaturegenerator;

import java.io.File;
import java.io.FileReader;
import java.io.ObjectStreamException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyRep;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.bc.BcPEMDecryptorProvider;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class SignatureGenerator {

	@Value(value = "${consumerid}")
	private String consumerId;

	@Value(value = "${filepath}")
	private String filePath;

	@Value(value = "${filepassword}")
	private String filePassWord;

	@Value(value = "${privatekeyversion}")
	private String privatekeyVersion;

	public String generateSignatures() throws InvalidKeyException, NoSuchAlgorithmException, ObjectStreamException,
			UnsupportedEncodingException, SignatureException {
		SignatureGenerator generator = new SignatureGenerator();

		
		/*String key = "-----BEGIN RSA PRIVATE KEY-----\r\n" + "Proc-Type: 4,ENCRYPTED\r\n"
				+ "DEK-Info: DES-EDE3-CBC,B63DB86C13FA0854\r\n" + "\r\n"
				+ "d2D9/OKvr5cdurXrPCIvEwLT+93RZzmrqCK9QnDk+GGfY1G9GqLygtlaClx9xggY\r\n"
				+ "J6L7KUXhoTZN6xWFLiyOGNUC62JieciPYH1GAiPHpxqLq9z60k6xLuQC1w4dYURW\r\n"
				+ "Nx8LLNFw0Sn464WhlbJCZ9cocHSGlCieI5k3RBbRGacLvMVyjz/k0zDxuEZT8jds\r\n"
				+ "16WWm5gHokVg8AZM8xrCILrY+1JMnro5LcsmMT6j7aLoFgRQdRcidXd+Nadew4Ea\r\n"
				+ "jyRxj9hWb5iXKk74dXQzm1EkOR7fTxe1+yuNujrXnxgZuZiUeUjc6SRFp7a8pD7m\r\n"
				+ "TyrXS4T9r2sabkmAtSoVvhTGLpXNSmGuEBUrpkNJAqk5l0KnvflDa7k3dCbWw3DL\r\n"
				+ "K+j9NKsx4JEj00PUFnPvBRXEa7wK9SiYA3oJTKPQX5uLn5PLIJdIKNSWQ4NBjrDB\r\n"
				+ "NvM5l3YCyes+mN8vltE2eX/hR85a+UtfUf1P9HQEMiFgq4cRV8Wy0hbgy1cOVJlm\r\n"
				+ "ZXyYNS0UKIRz7qq6swKcstOMdam22x3eaob+Ledb5ArPw4CJ4LpiEQB/eXf+lGTr\r\n"
				+ "vH6yI4n3aKYXSZFWAAasErk133X0a9KVbbB141U0zw9FV9ZfvmW3GeOYXHrm6XqM\r\n"
				+ "PI92fyqSpVyyRcEClP9dR9QGgwlYYtuQ85nGmiXo0XOfexgtrdUWGyYxsmD3Kp9Z\r\n"
				+ "Pf2GseKxDp8SFEwMew28rz8wiXMK3pR0s3X+ZZ/rfPV/41jTAdRgntyrU8Txzp5r\r\n"
				+ "+ZTCgcz2gV+TDg77hjo/U1IptKLqM4Iu6yF/9ejaV3pXQ+HlokVUtPtQVOHQjYQn\r\n"
				+ "LvX73LgGTpMT7Pi1gcc67g8wbJt5RrLPp5D2LGS17T+ePZ3GpC5VS2NL2hnELec7\r\n"
				+ "EBBRQDIGe/gXPzvY1qnta2h2W9lChOUgxtztyju29FUrppFRdXAU3k4H9ifGfSDP\r\n"
				+ "1vkt/dww528k3tk8CyvZKmnLM97Ah/U161gxhMcvqtAzIp4IqYIE6AkeUcHjysew\r\n"
				+ "zLUHbjBi5uiu+IxzaDrYIienqLCneh/xwm2P89eFVqUKo7J3p6rWP1kJzELPSuHy\r\n"
				+ "T/1z+pZVI6+GY9TwvhWOVajJWALGC8j6SwCnlhggL7iiUR6ZiE2RqdKwvPjHtwvK\r\n"
				+ "aJybaen5O7KenQnNjsn3PHpSkrjiYayQz0rKOIzven212xzwvR4eu9xp3QJUExKK\r\n"
				+ "G8nwedYVJwXdbeGDRd45ltzb8i8HSspnUsndISj/F6GRFw0lszFpAB4oZbcciST7\r\n"
				+ "cdy7zzYzAtmjjfTfW/3NqYWlmEdYIapSjQWxszEMhbn6ppfwnDYhIo8ktH8ihreL\r\n"
				+ "93oVWbBJBWsMCobYcGC/Phr1SVzWO3DY5IbB5hKaUN0itEFrdW7kbub2YsrwQtf7\r\n"
				+ "0fkN6byqMmyjgU0gE2wWwosOdkM5So6cariw5xtWXfFFR66/zMMc91e6jYgKPShL\r\n"
				+ "VZaaW3tsnfCiD0AiUYChKJqBo3OwYj6s7PD9GdHAsPRD6s0a+JC1bh5gxJop7Go7\r\n"
				+ "lQWChU9k5X9fK83sDDLnzKeouV6SSdNpG/5TsY+Mhmg3lmcBNEP3tA==\r\n" 
				+ "-----END RSA PRIVATE KEY-----\r\n";*/
		
		long intimestamp = System.currentTimeMillis();
		//System.out.println(key);
		//System.out.println("consumerId: " + consumerId);
		//System.out.println("intimestamp: " + intimestamp);

		Map<String, String> map = new HashMap<>();
		map.put("WM_CONSUMER.ID", consumerId);
		map.put("WM_CONSUMER.INTIMESTAMP", Long.toString(intimestamp));
		map.put("WM_SEC.KEY_VERSION", privatekeyVersion);

		String[] array = canonicalize(map);

		String signature = null;

		signature = generator.generateSignature(array[1],filePath,filePassWord);
		 //System.out.println("signature - "+signature);
		
		return signature;
	}
	

	public String generateSignature(String sign,String filePath,String filePassWord) throws NoSuchAlgorithmException, ObjectStreamException,
			InvalidKeyException, UnsupportedEncodingException, SignatureException {
		Signature signatureInstance = Signature.getInstance("SHA256WithRSA");

		String signatureString = null;
		
		  File file = new File(filePath);
		 
		  String password = filePassWord;
		
		try (PEMParser pemParser = new PEMParser(new FileReader(file))) {

			PEMEncryptedKeyPair encKeyPair = (PEMEncryptedKeyPair) pemParser.readObject();
			PEMKeyPair keyPair = encKeyPair.decryptKeyPair(new BcPEMDecryptorProvider(password.toCharArray()));

			JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
			PrivateKeyInfo privKeyInfo = keyPair.getPrivateKeyInfo();
			PrivateKey privKey = converter.getPrivateKey(privKeyInfo);
			signatureInstance.initSign(privKey);

			byte[] bytesToSign = sign.getBytes("UTF-8");
			signatureInstance.update(bytesToSign);
			byte[] signatureBytes = signatureInstance.sign();

			signatureString = Base64.encodeBase64String(signatureBytes);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		return signatureString;
	}

	protected static String[] canonicalize(Map<String, String> headersToSign) {
		StringBuffer canonicalizedStrBuffer = new StringBuffer();
		StringBuffer parameterNamesBuffer = new StringBuffer();
		Set<String> keySet = headersToSign.keySet();

		// Create sorted key set to enforce order on the key names
		SortedSet<String> sortedKeySet = new TreeSet<String>(keySet);
		for (String key : sortedKeySet) {
			Object val = headersToSign.get(key);
			parameterNamesBuffer.append(key.trim()).append(";");
			canonicalizedStrBuffer.append(val.toString().trim()).append("\n");
		}
		return new String[] { parameterNamesBuffer.toString(), canonicalizedStrBuffer.toString() };
	}

	class ServiceKeyRep extends KeyRep {
		private static final long serialVersionUID = -7213340660431987616L;

		public ServiceKeyRep(Type type, String algorithm, String format, byte[] encoded) {
			super(type, algorithm, format, encoded);
		}

		protected Object readResolve() throws ObjectStreamException {
			return super.readResolve();
		}
	}
}
