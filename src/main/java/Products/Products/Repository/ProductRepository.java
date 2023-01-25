package Products.Products.Repository;

import Products.Products.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM products where identification_code = ? AND is_delete = false",nativeQuery = true)
    Optional<Product> findWithCode(String code);

    @Query(value = "select * from products where name= ? and is_delete= false", nativeQuery = true)
    Optional<Product> findWithName(String name);

    @Query(value = "select * from products where is_delete= false", nativeQuery = true)
    List<Product> getAllProducts();

    @Query(value = "select * from products where id= ? and is_delete= false", nativeQuery = true)
    Optional<Product> getProductById(long id);

    @Query(value = "SELECT * FROM products where identification_code = ? AND is_delete = true",nativeQuery = true)
    Optional<Product> getProductIfExists(String code);

    @Query(value = "select * from products where is_delete= true", nativeQuery = true)
    List<Product> getProductsDeleted();
}
//creare se è possibile i due test per le ultime due funzioni create