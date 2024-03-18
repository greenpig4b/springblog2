package shop.mtcoding.blog.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;

import java.util.Optional;

@RequiredArgsConstructor
@Service  // IOC 등록
public class UserService {

    private final UserJPARepository userJPARepository; // D.I

    //회원가입
    @Transactional
    public void join(UserRequest.JoinDTO reqDTO){
        //1. 유효성검사 (컨트롤러 책임)
        //2. 유저네임 중복검사 (서비스책임) -DB연결필요
        Optional<User> userOP = userJPARepository.findByUserName(reqDTO.getUserName());

        if (userOP.isPresent()){
            throw new Exception400("중복된 유저네임 입니다");
        }
        userJPARepository.save(reqDTO.toEntity());
    }

    //로그인
    public User login(UserRequest.LoginDTO reqDTO){
        //해시검사

        //값이 null이면
        User sessionUser =  userJPARepository.findByUserNameAndPassword(reqDTO.getUserName(),reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지않았습니다"));
        return sessionUser;
    }
}
