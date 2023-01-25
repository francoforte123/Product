package Products.Products.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    private String category;
    private double price;
    private int quantity;
    private String placeOfOrigin;
    private String brand;
    @Column(name = "identification_code", unique = true)
    private String identificationCode;
    private boolean isDelete;
}
