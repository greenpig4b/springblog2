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
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserRepository;
import shop.mtcoding.blog.user.UserRequest;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final HttpSession session;


    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO){
        try {
            User sessinUser = userRepository.findByUserNameAndPassword(loginDTO);
            session.setAttribute("sessionUser",sessinUser);
        }catch (EmptyResultDataAccessException e){
            throw new Exception401("유저닉네임 혹은 비밀번호가 틀렸습니다.");
        }

        return "redirect:/";
    }

    // 회원가입
    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDTO){

        try {
            User user = userRepository.save(reqDTO.toEntity());
            session.setAttribute("sessionUser",user);
            return "redirect:/";
        }catch (DataIntegrityViolationException e){
            throw new Exception400("이미있는 유저닉네임입니다.");
        }

    }

    @GetMapping("/user/join-form")
    public String joinForm() {

        return "/user/join-form";
    }

    @GetMapping("/user/login-form")
    public String loginForm() {

        return "/user/login-form";
    }

    @GetMapping("/user/update-form")
    public String updateForm(HttpServletRequest request) {

        User user = (User) session.getAttribute("sessionUser");
        userRepository.findById(user.getId());
        request.setAttribute("userList",user);

        return "/user/update-form";
    }

    @Transactional
    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO updateDTO){

        User user = (User) session.getAttribute("sessionUser");
        User updateUser = userRepository.findById(user.getId());
        updateUser.update(updateDTO);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {

        session.invalidate();
        return "redirect:/";
    }
}
