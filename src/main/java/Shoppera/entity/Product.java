package Shoppera.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Getter
@Setter
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue
    private long productId;
    private String productName;
    private String price;
    private Integer stock;

}
