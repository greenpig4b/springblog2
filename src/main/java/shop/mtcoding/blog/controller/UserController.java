package shop.mtcoding.blog.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

        User sessoinUser = userRepository.findByUserNameAndPassword(loginDTO);

        session.setAttribute("sessionUser",sessoinUser);

        return "redirect:/";
    }

    @GetMapping("/join-form")
    public String joinForm() {

        return "/join-form";
    }

    @GetMapping("/user/login-form")
    public String loginForm() {

        return "/user/login-form";
    }

    @GetMapping("/user/update-form")
    public String updateForm() {

        return "/user/update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
