package Shoppera.service;

//import Shoppera.Entity.User;
import Shoppera.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String registerUser(User user) {

        String name = user.getUsername();
        String password = user.getPassword();

        System.out.println(user);
        return "ok";
    }


}
