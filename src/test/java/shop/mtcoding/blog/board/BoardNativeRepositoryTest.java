package shop.mtcoding.blog.board;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardNativeRepository.class)
@DataJpaTest
public class BoardNativeRepositoryTest {

    @Autowired
    private BoardNativeRepository boardNativeRepository;

    @Test
    public void findAll_test(){

        //given

        //when
        List<Board> boardList = boardNativeRepository.findAll();

        //then
        System.out.println("findAll_test/size --------------------------*******************--------------------------:"+ boardList.size());

        //Assertions  org.assertj.core.api
        Assertions.assertThat(boardList.size()).isEqualTo(4);

    }

    @Test
    public void findById_test(){

        //given

        //when
        Board board = boardNativeRepository.findById(1);

        //then
        System.out.println(board);

        Assertions.assertThat(board.getTitle()).isEqualTo("제목1");
        Assertions.assertThat(board.getContent()).isEqualTo("내용1");

    }


    @Test
    public void deleteById_test(){
        //given
        Integer id = 1;
        //when
        boardNativeRepository.deleteById(id);

        //then

        List<Board> boardList = boardNativeRepository.findAll();


        Assertions.assertThat(boardList.size()).isEqualTo(3);
    }

    @Test
    public void updateById_test(){
        //given
        Integer id =1;
        String title = "제목수정";
        String cotent = "컨텐츠수정";
        String userName = "이름수정";
        //when
        boardNativeRepository.updateById(title,cotent,userName,id);
        //then

        Board board = boardNativeRepository.findById(1);

        System.out.println(board);

    }



}
