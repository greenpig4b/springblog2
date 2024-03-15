package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserRepository;
import shop.mtcoding.blog.user.UserRequest;

@Import(UserRepository.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired  // 의존성주입
    UserRepository userRepository;

    @Autowired
    EntityManager em;

    @Test
    public void findByUserName_test(){
        //given
        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setUserName("ssar");
        reqDTO.setPassword("1234");

        //when
        User user = userRepository.findByUserNameAndPassword(reqDTO);

        System.out.println(user);
    }



}
