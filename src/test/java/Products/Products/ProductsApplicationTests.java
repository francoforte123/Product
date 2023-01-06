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
		product.setIdentificationCode("A001");
		product.setDelete(false);

		productRepository.save(product);
		Assertions.assertNotNull(product);
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
		product.setIdentificationCode("A002");
		product.setDelete(false);

		productRepository.save(product);
		Assertions.assertNotNull(product);
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


	@Test
	public void getProductWithCode(){
		createTheProduct1();
		createTheProduct2();

		String findProductWithCode= "A001";

		Optional<Product> optionalProduct= productRepository.findWithCode(findProductWithCode);
		Assertions.assertEquals(optionalProduct.get().getIdentificationCode(), findProductWithCode);
		System.out.println(optionalProduct);
	}


	@Test
	public void getProductWithName(){
		createTheProduct1();
		createTheProduct2();

		String findProductWithName= "aranciata";

		Optional<Product> optionalProduct= productRepository.findWithName(findProductWithName);
		Assertions.assertEquals(optionalProduct.get().getName(), findProductWithName);
		System.out.println(optionalProduct);
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
}
