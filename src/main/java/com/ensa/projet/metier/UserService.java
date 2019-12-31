package com.ensa.projet.metier;

 import com.ensa.projet.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    void addUser(User user);
    void deleteUser(long user_id);
    void updateUser(User user);
    User getUserById(long user_id);
    Page<User> getServiceEmployees(long service_id,Pageable pageable);

}
