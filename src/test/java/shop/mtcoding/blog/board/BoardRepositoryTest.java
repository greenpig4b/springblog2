package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.user.User;

import java.util.Arrays;
import java.util.List;

@Import(BoardRepository.class) // IOC 등록코드 -> 내가만든 Repository
@DataJpaTest  // 데이터 소스, 엔티티 매니저 ->
public class BoardRepositoryTest {
    // Test에서는 new가 안된다

    @Autowired //
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findById_test(){
        Integer id = 1;
        Board board = boardRepository.findById(id);
        System.out.println(board);
    }

    // 연관테이블 pk 값이 아닌걸 LAZY 로딩하면 셀렉트 한번 더 찾은다음 값 채워 넣는다.
    // ex) userName 같은 값 (지연로딩)


    @Test
    public void findAll_test(){
        //given

        //when

        List<Board> boardList = boardRepository.findAll();
        System.out.println(boardList);

        //then

    }
    @Test
    public void deleteById_test(){
        //given
        Integer id =2;

        //when
        boardRepository.deleteById(id);
        //then
        Board board = (Board) boardRepository.findAll();

        System.out.println(board);
    }



    @Test
    public void findAll_lazyLoding_test(){
        List<Board> boardList = boardRepository.findAll();
        boardList.forEach(board -> {
            System.out.println(board.getUser().getUserName());
        });
    }

    @Test
    public void ssss(){
        List<Board> boardList = boardRepository.findAll();
        System.out.println(boardList);
    }
    @Test
    public void findUserId_test(){

        String q1 = "select b from Board b order by b.id desc";
        List<Board> boardList = em.createQuery(q1,Board.class).getResultList();

        int [] users =  boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();


        String q2 = "select u from User u where u.id in(";
        for (int i = 0; i < users.length; i++) { //123
            if (i == users.length - 1){   // users : // 1,2,3
                q2 = q2 + users[i] +")";
            }else{
                q2 = q2 + users[i] + ",";
            }
        }

        //-----------------------------------------------

        List<User> userList = em.createQuery(q2,User.class).getResultList();

        for (Board board : boardList){
            for(User user : userList){
                if (user.getId() == board.getUser().getId()){
                    board.setUser(user);
                }
            }
        }

    }

}
