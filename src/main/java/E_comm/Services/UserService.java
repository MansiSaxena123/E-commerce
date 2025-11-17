package E_comm.Services;

import E_comm.Model.User;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class UserService {
    private List<User> userList = new ArrayList<>();


    public UserService(){
        userList.add(new User(UUID.randomUUID().toString(), "salim"));
        userList.add(new User(UUID.randomUUID().toString(), "Shaikh"));
        userList.add(new User(UUID.randomUUID().toString(), "Shakeel"));
    }

    public List<User> getUserList(){
        return this.userList;
    }
}
