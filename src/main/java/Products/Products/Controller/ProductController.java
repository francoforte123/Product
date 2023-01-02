package Products.Products.Controller;

import Products.Products.Entities.Product;
import Products.Products.Exception.AlreadyRegisteredException;
import Products.Products.Exception.GenericException;
import Products.Products.Exception.NotFoundException;
import Products.Products.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts() throws NotFoundException {
        return productService.getAllProduct();
    }

    @PostMapping("/createProduct")
    public ResponseEntity create(@RequestBody Product product) throws AlreadyRegisteredException {
        return productService.createTheProduct(product);
    }

    @GetMapping("/singleProduct")
    public ResponseEntity getSingleProduct(@RequestParam Long id) throws NotFoundException {
        return productService.singleProduct(id);
    }

    @GetMapping("/productWithCode")
    public ResponseEntity getProductByCode(@RequestParam String code) throws NotFoundException {
        return productService.getProductWithCode(code);
    }

    @GetMapping("/productWithName")
    public ResponseEntity getProductByName(@RequestParam String name) throws NotFoundException {
        return productService.getProductWithName(name);
    }

    @PutMapping("/deleteProduct")
    public void deleteProduct(@RequestParam long id) throws NotFoundException, GenericException {
        productService.modyfyProduct(id);
    }
}

