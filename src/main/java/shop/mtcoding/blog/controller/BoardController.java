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
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardRepository;
import shop.mtcoding.blog.board.BoardRequest;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardRepository boardRepository;
    private final HttpSession session;


    //Model : 안에 리퀘스트 포함하고있음
    @GetMapping({ "/"})
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("boardList",boardList);
        return "index";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO){
       User user = (User) session.getAttribute("sessionUser");
       boardRepository.save(reqDTO.toEntity(user));

        return "redirect:/";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {

        return "/board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        User user = (User) session.getAttribute("sessionUser");
        Board board = boardRepository.findByIdJoinUser(id);


        boolean isOwner = false;
        if (user != null){
            isOwner = false;

            if (user.getId() == board.getUser().getId()){
                isOwner = true;
            }
        }

        request.setAttribute("isOwner",isOwner);
        request.setAttribute("board",board);
        return "/board/detail";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateform(@PathVariable Integer id, HttpServletRequest request){
        Board board = boardRepository.findByIdJoinUser(id);

        if (board.getUser().getId() == null){
            throw new Exception404("해당 게시글을 찾을 수 없습니다");
        }

        request.setAttribute("board",board);

         return "/board/update-form";
    }

    @Transactional
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id,BoardRequest.UpdateDTO reqDTO){

        User user = (User) session.getAttribute("sessionUser");
        Board board = boardRepository.findByIdJoinUser(id);

        if (user.getId() != board.getUser().getId()){
            throw new Exception403("권한이없습니다.");
        }

        board.update(reqDTO);

        return "redirect:/board/"+id;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id){
        User user = (User) session.getAttribute("sessionUser");
        Board board = boardRepository.findByIdJoinUser(id);

        if (user.getId() != board.getUser().getId()){
            throw new Exception403("권한이없습니다.");
        }

        boardRepository.deleteById(id);

        return "redirect:/";
    }

}
