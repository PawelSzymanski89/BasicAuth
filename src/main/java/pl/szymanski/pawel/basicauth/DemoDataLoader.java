package pl.szymanski.pawel.basicauth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.szymanski.pawel.basicauth.domain.ApiClient;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class DemoDataLoader implements CommandLineRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        ApiClient user = new ApiClient();
        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode("TESTPAS123"));
        user.setUsername("USER");
        entityManager.persist(user);
        entityManager.flush();

        System.out.println(user);

        ApiClient admin = new ApiClient();
        admin.setRole("ROLE_ADMIN");
        admin.setPassword(passwordEncoder.encode("TESTPAS123"));
        admin.setUsername("ADMIN");
        entityManager.persist(admin);
        entityManager.flush();

        System.out.println(admin);

    }

}
