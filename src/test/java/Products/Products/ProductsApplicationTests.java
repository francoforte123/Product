package Products.Products;

import Products.Products.Entities.Product;
import Products.Products.Repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductsApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	public void createTheProduct1(){
		Product product= new Product();
		product.setName("aranciata");
		product.setCategory("bibita");
		product.setPrice(0.50);
		product.setQuantity(10);
		product.setPlaceOfOrigin("Germania");
		product.setBrand("MD");
		product.setIdentificationCode("0001");
		product.setDelete(false);

		productRepository.save(product);
		Assertions.assertNotNull(product);
		System.out.println(product);
	}

	@Test
	public void createTheProduct2(){
		Product product= new Product();
		product.setName("sprite");
		product.setCategory("bibita");
		product.setPrice(0.60);
		product.setQuantity(10);
		product.setPlaceOfOrigin("Italia");
		product.setBrand("MD");
		product.setIdentificationCode("0002");
		product.setDelete(false);

		productRepository.save(product);
		Assertions.assertNotNull(product);
		System.out.println(product);
	}


	@Test
	public void getAllProducts(){
		createTheProduct1();
		createTheProduct2();

		List<Product> productList= productRepository.findAll();
		Assertions.assertNotNull(productList);
	}


	@Test
	public void getSingleProductWithId(){
		createTheProduct1();
		createTheProduct2();

		long findProductWithId= 1;

		Optional<Product> optionalProduct= productRepository.findById(findProductWithId);
		Assertions.assertEquals(optionalProduct.get().getId(), findProductWithId);
	}


	/*@Test
	public void getProductWithCode(){
		createTheProduct1();
		createTheProduct2();

		String findProductWithCode= "0001";

		Optional<Product> optionalProduct= productRepository.findWithCode(findProductWithCode);
		Assertions.assertEquals(optionalProduct.get().getIdentificationCode(), findProductWithCode);
	}*/


	@Test
	public void getProductWithName(){
		createTheProduct1();
		createTheProduct2();

		String findProductWithName= "aranciata";

		Optional<Product> optionalProduct= productRepository.findWithName(findProductWithName);
		Assertions.assertEquals(optionalProduct.get().getName(), findProductWithName);
	}


	@Test
	public void deleteTheProduct(){
		createTheProduct1();
		createTheProduct2();

		long deleteProductWithId= 2;

		Optional<Product> deleteProduct= productRepository.findById(deleteProductWithId);
		deleteProduct.get().setDelete(true);

		Assertions.assertEquals(productRepository.save(deleteProduct.get()).isDelete(), true);
	}


	//la funzione funziona solo se il prodotto ha in sè per sè il valore della variabile "isDelete" a true
	@Test
	public void getProductWithCodeIfExists(){
		createTheProduct1();
		createTheProduct2();

		String code= "0001";

		Optional<Product> findTheProduct = productRepository.getProductIfExists(code);
		findTheProduct.get().setDelete(true);

		Assertions.assertEquals(findTheProduct.get().isDelete(), true);
		System.out.println(findTheProduct);
	}
}