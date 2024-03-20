package shop.mtcoding.blog.controller;

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.utils.ApiUtil;
import shop.mtcoding.blog.user.*;

@RequiredArgsConstructor
@RestController
public class UserController {


    private final HttpSession session;
    private final UserJPARepository userJPARepository;
    private final UserService userService;

    //:TODO -- 회원정보 조회 API 필요  @GetMapping("/api/users/{id}")

    //회원정보 조회
    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> userInfo(@PathVariable Integer id){
        User user = userService.updateForm(id);
        return ResponseEntity.ok(new ApiUtil(user));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO loginDTO){
        User sessionUser = userService.login(loginDTO);
        session.setAttribute("sessionUser",sessionUser);

        return ResponseEntity.ok(new ApiUtil(null));
    }

    // 회원가입, 인증이필요없는 컨트롤러는 따로만드는게 좋다.
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO reqDTO){
        User user = userService.join(reqDTO);

        return ResponseEntity.ok(new ApiUtil(user));
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<?> update(@RequestBody @PathVariable Integer id ,UserRequest.UpdateDTO updateDTO){
        User user = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.update(user.getId(),updateDTO);

        session.setAttribute("sessionUser",newSessionUser);

        return ResponseEntity.ok(new ApiUtil(newSessionUser)); // 리턴할떄 바뀌는 row를 알려줘야한다.
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {

        session.invalidate();
        return ResponseEntity.ok(new ApiUtil(null));
    }

}
