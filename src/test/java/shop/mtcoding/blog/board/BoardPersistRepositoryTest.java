package shop.mtcoding.blog.board;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(BoardPersistRepository.class)
@DataJpaTest
public class BoardPersistRepositoryTest {

    @Autowired
    private BoardPersistRepository boardPersistRepository;

    @Test
    public void save_test(){

        //given
        Board boards = new Board("제목5","내용5","유저5");

        //when
        Board board = boardPersistRepository.save(boards);
        System.out.println(board);

        //then

    }
}
