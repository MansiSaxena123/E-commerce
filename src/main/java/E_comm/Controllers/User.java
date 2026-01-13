package E_comm.Controllers;


import E_comm.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class User {
    
    @Autowired
    private UserService userService;

    // http://localhost:8081/home/users
    @GetMapping("/users")
    public List<E_comm.Model.User> users(){
        return  this.userService.getUserList();
    }

    // http://localhost:8081/home/current-user
    @GetMapping("/current-user")
    public String getCurrentUserName(Principal principal){
        return principal.getName();
    }
}
