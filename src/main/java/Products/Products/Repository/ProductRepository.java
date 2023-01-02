package Products.Products.Repository;

import Products.Products.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM products where products.identification_code = ? AND products.is_delete = false",nativeQuery = true)
    Optional<Product> findWithCode(String code);

    @Query(value = "select * from products where products.name= ? and products.is_delete= false", nativeQuery = true)
    Optional<Product> findWithName(String name);
}
