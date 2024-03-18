package shop.mtcoding.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.board.*;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardRepository boardRepository;
    private final HttpSession session;
    private final BoardService boardService;

    //Model : 안에 리퀘스트 포함하고있음
    @GetMapping({ "/"})
    public String index(HttpServletRequest request) {
        List<Board> boardList =boardService.boardList();
        request.setAttribute("boardList",boardList);
        return "index";
    }


    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO){
       User user = (User) session.getAttribute("sessionUser");
       boardService.write(reqDTO,user);

       return "redirect:/";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {

        return "/board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id,HttpServletRequest request) {
        User user = (User) session.getAttribute("sessionUser");

      Board board = boardService.boardDetail(id,user);

        request.setAttribute("board",board);
        return "/board/detail";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateform(@PathVariable Integer id, HttpServletRequest request){
        Board board = boardService.updateForm(id);
        request.setAttribute("board",board);

         return "/board/update-form";
    }

    @Transactional
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id,BoardRequest.UpdateDTO reqDTO){
        User user = (User) session.getAttribute("sessionUser");
        boardService.update(id,user.getId(),reqDTO);

        return "redirect:/board/"+id;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id){
        User user = (User) session.getAttribute("sessionUser");
        boardService.delete(id,user.getId());

        return "redirect:/";
    }

}
