package Products.Products.Controller;

import Products.Products.Entities.Product;
import Products.Products.Exception.AlreadyRegisteredException;
import Products.Products.Exception.GenericException;
import Products.Products.Exception.NotFoundException;
import Products.Products.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.ok(productService.createTheProduct(product));
    }

    @GetMapping("/singleProduct")
    public ResponseEntity getSingleProduct(@RequestParam long id) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(productService.singleProduct(id));
    }

    @GetMapping("/productWithCode")
    public ResponseEntity getProductByCode(@RequestParam String code) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductWithCode(code));
    }

    @GetMapping("/productWithName")
    public ResponseEntity getProductByName(@RequestParam String name) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductWithName(name));
    }

    @DeleteMapping("/deleteProduct")
    public Product deleteProduct(@RequestParam long id) throws NotFoundException, GenericException {
        return productService.deleteProduct(id);
    }

    @PutMapping("/updateTheProduct")
    public Product modifyTheProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(product)).getBody();
    }

    @GetMapping("/getProductIfExists")
    public ResponseEntity productIfExists(@RequestParam String code) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findTheProductIfExists(code));
    }

    @GetMapping("/getProductsDeleted")
    public List<Product> findAllTheProductDeleted() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProductsDeleted()).getBody();
    }
}

