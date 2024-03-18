package shop.mtcoding.blog.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserJPARepository;

import java.util.List;
import java.util.Optional;

//임포트 할 필요가없음
@DataJpaTest
public class UserJAPRepositoryTest {
    @Autowired
    UserJPARepository userJPARepository;

    @Test
    public void save_test(){
        //given
        User user = User.builder()
                .userName("happy")
                .password("1234")
                .email("happy@nate.com")
                .build();
        //when
        userJPARepository.save(user);
        //then

    }

    @Test
    public void findById(){
        //given
        Integer id = 5;
        //when
        Optional<User> userOP = userJPARepository.findById(id);
        //유제 객체가 있으면 꺼낸다 이렇게하면 널포인트입섹션이 뜨지않는다.
        if (userOP.isPresent()){
            User user = userOP.get();
            System.out.println("결과~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+user.getUserName());
        }
        //then
    }

    @Test
    public void findAll(){
        //given

        //when
        List<User> userOP = userJPARepository.findAll( Sort.by(Sort.Direction.DESC,"id"));

        //then
        System.out.println(userOP);
    }

    @Test
    public void paging_test() throws JsonProcessingException {
        //given
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(1,2,sort);
        //when
        Page<User> userPG = userJPARepository.findAll(pageable);

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(userPG);
        System.out.println(json);
    }
    //데이터베이스 ANSI -- 정피필요

    @Test
    public void findByUserNameAndPassword(){
        //given
        String userName = "ssar";
        String password = "1234";
        //when
        User user = userJPARepository.findByUserNameAndPassword(userName,password);
        //then
        System.out.println(user);
    }

}
