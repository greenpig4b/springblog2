package shop.mtcoding.blog.board;


import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardPersistRepository.class)
@DataJpaTest
public class BoardPersistRepositoryTest {

    @Autowired
    private BoardPersistRepository boardPersistRepository;

    @Autowired
    private EntityManager em;


    @Test
    public void save_test(){

        //given
        Board board = new Board("제목1","유저1","ㅇㅇㅇ");

        //when
        boardPersistRepository.save(board);
        System.out.println(board);

        //then

    }

    @Test
    public void findAll_test(){

        //given

        //when
        List<Board> boardList = boardPersistRepository.findAll();

        //then
        System.out.println("findAll_test/size : "+ boardList.size());

        //Assertions  org.assertj.core.api
        Assertions.assertThat(boardList.size()).isEqualTo(4);

    }

    @Test
    public void findById(){
        //given
        Integer id = 1;
        //when
        Board board = boardPersistRepository.findById(id);
        //then
        Assertions.assertThat(board.getTitle()).isEqualTo("제목1");
        Assertions.assertThat(board.getContent()).isEqualTo("내용1");
    }

    @Test
    public void deleteByIdV2_test(){
        //given
        Integer id = 1;
        //when
        boardPersistRepository.deleteByIdV2(id);
        //then
        em.flush(); // 버퍼에 쥐고있는 쿼리 강제로 전송한다.  // Test 할때 사용

    }

    @Test
    public void deleteById_test(){
        //given
        Integer id  = 1;

        //when
        boardPersistRepository.deleteById(id);
        em.flush();  // 강제로 쿼리 날림 이시점에서

        //then
        List<Board> boardList = boardPersistRepository.findAll();

        //Assertions  org.assertj.core.api
        Assertions.assertThat(boardList.size()).isEqualTo(3);

    }

}
