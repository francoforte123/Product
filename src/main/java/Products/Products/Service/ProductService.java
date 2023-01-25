package Products.Products.Service;

import Products.Products.Entities.Product;
import Products.Products.Exception.AlreadyRegisteredException;
import Products.Products.Exception.GenericException;
import Products.Products.Exception.NotFoundException;
import Products.Products.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProduct() throws NotFoundException {
        List<Product> getProducts = productRepository.getAllProducts();
        if (getProducts.isEmpty()) throw new NotFoundException("the product is not found");
        return getProducts;
    }

    public Product createTheProduct(Product product) throws AlreadyRegisteredException {
        Product out;
        try {
            out = productRepository.save(product);
        } catch (DataAccessException e) {
            throw new AlreadyRegisteredException("the code " + product.getIdentificationCode() + " già registrato, " + "or " + product.getName() + " già registrato");
        }
        return out;
    }

    public Product singleProduct(long id) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.getProductById(id);
        return optionalProduct.orElseThrow(() -> new NotFoundException("the product with id: " + id + " not found"));
    }


    public Product getProductWithCode(String code) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findWithCode(code);
        if (optionalProduct.isEmpty()) throw new NotFoundException("the product with code: " + code + ", not found");
        return optionalProduct.get();
    }

    public Product getProductWithName(String name) throws NotFoundException {
        Optional<Product> getProduct = productRepository.findWithName(name);
        if (getProduct.isEmpty()) throw new NotFoundException("the product with name: " + name + ", not found");
        return getProduct.get();
    }

    public Product deleteProduct(long id) throws NotFoundException, GenericException {
        Optional<Product> deleteProductFromDb = productRepository.getProductById(id);
        if (deleteProductFromDb.isEmpty()) {
            throw new NotFoundException("the product with id: " + id + ", not found");
        }
        if (deleteProductFromDb.get().isDelete() == false) {
            deleteProductFromDb.get().setDelete(true);
        } else {
            throw new GenericException("exists product");
        }
        return productRepository.save(deleteProductFromDb.get());
    }


    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public Product findTheProductIfExists(String code) {
        Optional<Product> getProduct = productRepository.getProductIfExists(code);
        if (!getProduct.isPresent()) throw new NotFoundException("the product non found");
        return getProduct.get();
    }

    public List<Product> getAllProductsDeleted() {
        List<Product> optionalProduct = productRepository.getProductsDeleted();
        if (optionalProduct.isEmpty()) throw new NotFoundException("the products not found");
        return optionalProduct;
    }
}
