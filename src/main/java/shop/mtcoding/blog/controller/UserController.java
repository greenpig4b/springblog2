package shop.mtcoding.blog.controller;

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog.user.*;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final HttpSession session;
    private final UserJPARepository userJPARepository;
    private final UserService userService;

    //:TODO -- 회원정보 조회 API 필요

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO){
//        try {
//            User sessinUser = userRepository.findByUserNameAndPassword(loginDTO);
//            session.setAttribute("sessionUser",sessinUser);
//        }catch (EmptyResultDataAccessException e){
//            throw new Exception401("유저닉네임 혹은 비밀번호가 틀렸습니다.");
//        }
        User sessionUser = userService.login(loginDTO);
        session.setAttribute("sessionUser",sessionUser);

        return "redirect:/";
    }

    // 회원가입
    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDTO){
        userService.join(reqDTO);
        return "redirect: /";
    }

    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO updateDTO){

        User user = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.update(user.getId(),updateDTO);
        session.setAttribute("sessionUser",newSessionUser);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {

        session.invalidate();
        return "redirect:/";
    }
}
