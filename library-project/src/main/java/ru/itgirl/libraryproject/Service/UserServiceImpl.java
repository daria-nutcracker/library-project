package ru.itgirl.libraryproject.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itgirl.libraryproject.models.Role;
import ru.itgirl.libraryproject.repositories.RoleRepository;
import ru.itgirl.libraryproject.repositories.UsersRepository;
import ru.itgirl.libraryproject.models.Users;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService  {

    @PersistenceContext
    private EntityManager em;
    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByLogin(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return user;
    }

    public Users findUserById (Long userId) {
        Optional<Users> userFromDb = usersRepository.findById(userId);
        return userFromDb.orElse(new Users());
    }
    public List<Users> allUsers() {
        return usersRepository.findAll();
    }

    public boolean saveUsers(Users user) {
        Users userFromDB = usersRepository.findByLogin(user.getLogin());
        if (userFromDB != null)
            return false;
//        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        usersRepository.save(user);
        return true;
    }

    public boolean deleteUser (Long userId) {
        if (usersRepository.findById(userId).isPresent()) {
            usersRepository.deleteById(userId);
            return true;
        }
        return false;
    }
    public List<Users> usergtList (Long idMin) {
        return em.createQuery("SELECT u FROM users u where u.id > :paramId", Users.class)
                .setParameter("paramId", idMin).getResultList();
    }
}
