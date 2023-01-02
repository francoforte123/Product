package Products.Products.Service;

import Products.Products.Entities.Product;
import Products.Products.Exception.AlreadyRegisteredException;
import Products.Products.Exception.GenericException;
import Products.Products.Exception.NotFoundException;
import Products.Products.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProduct() throws NotFoundException{
        List<Product> getProducts= productRepository.findAll();
        if (getProducts.isEmpty()) throw new NotFoundException("the product is not found");
        return ResponseEntity.ok(getProducts).getBody();
    }

    public ResponseEntity createTheProduct(Product product) throws AlreadyRegisteredException {
        Optional<Product> productWithCode= productRepository.findWithName(product.getName());
        if (productWithCode.isPresent()) throw new AlreadyRegisteredException(product.getIdentificationCode() + " già registrato: " + product.getName() + " già registrato");
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    public ResponseEntity singleProduct(Long id) throws NotFoundException {
        Optional<Product> getProduct= productRepository.findById(id);
        if (getProduct.isEmpty()) throw new NotFoundException("the product " + id + "not found");
        return ResponseEntity.ok(getProduct);
    }

    public ResponseEntity getProductWithCode(String code) throws NotFoundException {
        Optional<Product> optionalProduct= productRepository.findWithCode(code);
        if (optionalProduct.isEmpty()) throw new NotFoundException("the product " + code + " not found");
        return ResponseEntity.ok().body(optionalProduct);
    }

    public ResponseEntity<Optional<Product>> getProductWithName(String name) throws NotFoundException {
        Optional<Product> getProduct= productRepository.findWithName(name);
        if (getProduct.isEmpty()) throw new NotFoundException("the product " + name + " not found");
        return ResponseEntity.ok().body(getProduct);
    }

    public void modyfyProduct(Long id) throws NotFoundException, GenericException {
        Optional<Product> deleteProductFromDb= productRepository.findById(id);
        if (deleteProductFromDb.isEmpty()){
            throw new NotFoundException("product " + id + " not found");
        }
        if (deleteProductFromDb.get().isDelete()==false){
            deleteProductFromDb.get().setDelete(true);
        }else {
            throw new GenericException("exists product");
        }
        productRepository.save(deleteProductFromDb.get());
    }
}
