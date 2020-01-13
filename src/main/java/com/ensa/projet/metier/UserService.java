package com.ensa.projet.metier;

 import com.ensa.projet.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.security.core.userdetails.UserDetailsService;

 import java.util.List;

public interface UserService extends UserDetailsService {

    User createUser(User user);
    void deleteUser(long user_id);
    User updateUser(User user);
    User getUserById(long user_id);
    Page<User> getServiceEmployees(long service_id,Pageable pageable);
    User getServiceChef(long service_id);
    Page<User> getAllUsers(Pageable pageable);
    Page<User> getUsersByRole(String role, Pageable pageable);

    List<User> getUsersById(List<Long> users_ids);
     UserDetails loadUserById(Long id);
    Page<User> searchUsers(String by, String value,Pageable pageable);
}
