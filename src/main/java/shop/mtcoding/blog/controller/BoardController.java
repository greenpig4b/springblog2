package shop.mtcoding.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardNativeRepository;
import shop.mtcoding.blog.board.BoardPersistRepository;
import shop.mtcoding.blog.board.BoardRequest;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardNativeRepository boardNativeRepository;
    private final BoardPersistRepository boardPersistRepository;


    //Model : 안에 리퀘스트 포함하고있음
    @GetMapping({ "/"})
    public String index(HttpServletRequest request) {

        List<Board> boardList = boardPersistRepository.findAll();

        request.setAttribute("boardList",boardList);
        //리퀘스트 디스패처
        return "index";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO requestDTO){

    boardPersistRepository.save(requestDTO.toEntity());

        return "redirect:/";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {

        return "/board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {

        Board board = boardPersistRepository.findById(id);

        request.setAttribute("board",board);

        return "/board/detail";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateform(@PathVariable Integer id , HttpServletRequest request){

        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board",board);

         return "/board/update-form";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id,String title,String content,String userName){

        boardNativeRepository.updateById(title,content,userName,id);
        return "redirect:/board/"+id;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id){

        boardPersistRepository.deleteById(id);
        return "redirect:/";
    }


}
