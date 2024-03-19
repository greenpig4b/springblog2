package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog.user.User;

import javax.swing.plaf.SpinnerUI;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BoardJPARepositoryTest {
    @Autowired
    BoardJPARepository boardJPARepository;

    @Autowired
    private EntityManager em;

    @Test
    public void save_test(){
        //given
        User sessionUser = User.builder()
                .id(1).build();

        Board board= Board.builder()
                .title("제목5")
                .content("내용5")
                .user(sessionUser)
                .build();

        //when
        boardJPARepository.save(board);
        //then
        System.out.println("결과~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+board.getId());
    }

    @Test
    public void findById(){
        //given
        Integer id = 1;
        //when
        Optional<Board> boardOP = boardJPARepository.findById(1);
        if (boardOP.isPresent()){
            Board board =boardOP.get();
            System.out.println(board);
        }
        //then
    }

    @Test
    public void findByJoinUser(){
        //given
        Integer id = 1;
        //when
        Optional<Board> boardOP = boardJPARepository.findByJoinUser(id);

        System.out.println(boardOP);
        //then
    }

    @Test
    public void findAll(){
        //given
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        //when
        List<Board> boardList = boardJPARepository.findAll(sort);
        //then
        System.out.println(boardList);
    }

    @Test
    public void deleteById(){
        //given
        Integer id = 1;
        //when
        boardJPARepository.deleteById(id);
        em.flush();
        List<Board> boardList = boardJPARepository.findAll();
        //then
        System.out.println(boardList);
    }

    @Test
    public void fondByJoinUserAndReplys_test(){
        //given
        Integer id = 4;

        //when
        Optional<Board> board = boardJPARepository.findByJoinUser(id);

        //then
        System.out.println("사이즈~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"+board.get().getReplyList().size());

    }
}
