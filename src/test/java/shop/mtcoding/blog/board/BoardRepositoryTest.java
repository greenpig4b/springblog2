package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findById_test(){
        Integer id = 1;
        Board board = boardRepository.findById(id);
        System.out.println(board);
    }

    // 연관테이블 pk 값이 아닌걸 LAZY 로딩하면 셀렉트 한번 더 찾은다음 값 채워 넣는다.
    // ex)  userName 같은 값 (지연로딩)

}
