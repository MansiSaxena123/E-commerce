package Shoppera.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "cart")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CartItem> cartItem = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
