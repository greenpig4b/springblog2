package shop.mtcoding.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.board.*;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;
    private final BoardService boardService;

    //Model : 안에 리퀘스트 포함하고있음

    //TODO -- 글목록조회 API 필요
    //TODO -- 글상세보기 API 필요
    //TODO -- 글조회 API 필요

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO) {
        User user = (User) session.getAttribute("sessionUser");
        boardService.write(reqDTO, user);

        return "redirect:/";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, BoardRequest.UpdateDTO reqDTO) {
        User user = (User) session.getAttribute("sessionUser");
        boardService.update(id, user.getId(), reqDTO);

        return "redirect:/board/" + id;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id) {
        User user = (User) session.getAttribute("sessionUser");
        boardService.delete(id, user.getId());

        return "redirect:/";
    }

}
