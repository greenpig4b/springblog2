package shop.mtcoding.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.board.BoardNativeRepository;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardNativeRepository boardNativeRepository;

    @GetMapping({ "/"})
    public String index() {

        return "index";
    }

    @PostMapping("/board/save")
    public String save(String userName,String title,String content){

        System.out.println("username : " + userName);
        System.out.println("title : " + title);
        System.out.println("content : " + content);

        boardNativeRepository.save(title,content,userName);

        return "redirect:/";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {

        return "/board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id) {

        return "board/detail";
    }
}
