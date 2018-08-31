package pl.reverseAuctions.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.reverseAuctions.role.Role;
import pl.reverseAuctions.role.RoleRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRepeatPassword(user.getPassword());
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        System.out.println(user);
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        user.setRepeatPassword(user.getPassword());
        System.out.println(user);
        userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean isUserExist(String username) {
        return userRepository.countByUsername(username) <= 0;
    }

    @Override
    public Boolean isUserMailExist(String mail) {
        return userRepository.countByMail(mail) <= 0;
    }
}
