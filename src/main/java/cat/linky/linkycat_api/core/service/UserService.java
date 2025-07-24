package cat.linky.linkycat_api.core.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cat.linky.linkycat_api.core.model.User;
import cat.linky.linkycat_api.core.repository.IUserRepository;

@Service
public class UserService {
    
    private final IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public void insert(User user) {

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);

        repository.save(user);
    }

}
