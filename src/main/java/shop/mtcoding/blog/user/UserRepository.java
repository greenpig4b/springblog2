package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public User findByUserNameAndPassword(UserRequest.LoginDTO requestDTO){
        Query query = em.createQuery("select u from User u where u.userName = :userName AND u.password = :password ", User.class);
        query.setParameter("userName",requestDTO.getUserName());
        query.setParameter("password",requestDTO.getPassword());
        User user = (User) query.getSingleResult();

        return user;
    }

}
