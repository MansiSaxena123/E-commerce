package Shoppera.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private long id;
    @JoinColumn(unique = true)
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Cart cart;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
