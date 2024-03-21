package shop.mtcoding.blog.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.utils.ApiUtil;
import shop.mtcoding.blog.board.*;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final HttpSession session;
    private final BoardService boardService;

    //Model : 안에 리퀘스트 포함하고있음

    //TODO -- 글목록조회 API 필요 @GetMapping("/") 메인페이지
    // DTO 안만들었을때 문제점   1. 커넥션의 시간이 길어진다.(LAZY로딩때문에)  2. 필요없는 필드를 응답하게 된다. 3. MessageConberter JSON
    // 만들떄 빈객체를 LAZY LOADING하고 기다렸다가 JSON을 만들어야하는데  안기다리고 터트림(기다리게하는 방법있음) -> DTO만들면 필요없음
    // 해결방법 : 1.open in view : false 2. service종료직전에 lazy loading 발동  3. DTO 생성
    @GetMapping("/")
    public ResponseEntity<?> main(){
        List<BoardResponse.MainDTO> respDTO = boardService.boardList();
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    //TODO -- 글상세보기 API 필요 @GetMapping("/api/boards/{id}/detail")
    @GetMapping("/api/boards/{id}/detail")
    public ResponseEntity<?> detail(@PathVariable Integer id){
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DetailDTO respDTO = boardService.boardDetail(id,sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    //TODO -- 글조회 API 필요 @GetMapping("/api/boards/{id}")
    @GetMapping("/api/boards/{id}")
    public ResponseEntity<?> findOne(@PathVariable Integer id){
        Board board = boardService.updateForm(id);
        return ResponseEntity.ok(new ApiUtil(board));
    }

    @PostMapping("/api/boards")
    public ResponseEntity<?> save(@RequestBody BoardRequest.SaveDTO reqDTO) {
        User user = (User) session.getAttribute("sessionUser");
        Board board = boardService.write(reqDTO, user);

        return ResponseEntity.ok(new ApiUtil(board));
    }

    @PutMapping("/api/boards/{id}")
    public ResponseEntity<?> update(@RequestBody @PathVariable Integer id, BoardRequest.UpdateDTO reqDTO) {
        User user = (User) session.getAttribute("sessionUser");
        Board board = boardService.update(id, user.getId(), reqDTO);

        return ResponseEntity.ok(new ApiUtil(board));
    }

    // 딜리트는 바디없음
    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        User user = (User) session.getAttribute("sessionUser");
        boardService.delete(id, user.getId());

        return ResponseEntity.ok(new ApiUtil(null));
    }

}
