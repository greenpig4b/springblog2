package shop.mtcoding.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String detail(@PathVariable Integer id,BoardRequest.ViewDTO viewDTO,HttpServletRequest request) {

        Board board = boardRepository.findByIdJoinUser(id);



        //1. 상세보기 페이지로 넘어갔을때 주인페이지로 넘어간다.
        //2. 로그인한 유저중에 userId가 다르면  boolean 처리로 보이지않도록 한다.
        //3. request 담아서  mustache에 뿌린다.
        request.setAttribute("board",board);
        return "/board/detail";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateform(@PathVariable Integer id, HttpServletRequest request){

        Board board = boardRepository.findByIdJoinUser(id);
        request.setAttribute("board",board);

         return "/board/update-form";
    }

    @Transactional
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id,BoardRequest.UpdateDTO reqDTO){

        Board board = boardRepository.findByIdJoinUser(id);
        board.update(reqDTO);

        return "redirect:/board/"+id;
    }


    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id){

        return "redirect:/";
    }

}
