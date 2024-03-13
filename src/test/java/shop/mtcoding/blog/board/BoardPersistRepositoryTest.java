package shop.mtcoding.blog.board;


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

//    @Test
//    public void save_test(){
//
//        //given
//        Board boards = new Board("제목5","내용5","유저5");
//
//        //when
//        Board board = boardPersistRepository.save(boards);
//        System.out.println(board);
//
//        //then
//
//    }

    @Test
    public void findAll_test(){

        //given

        //when
        List<Board> boardList = boardPersistRepository.findAll();

        //then
        System.out.println("findAll_test/size --------------------------*******************--------------------------:"+ boardList.size());

        //Assertions  org.assertj.core.api
        Assertions.assertThat(boardList.size()).isEqualTo(4);

    }


}
