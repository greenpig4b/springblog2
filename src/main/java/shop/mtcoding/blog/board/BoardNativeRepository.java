package shop.mtcoding.blog.board;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository

public class BoardNativeRepository {
    private final EntityManager em;

    // 보드 리스트 화면에뿌림
    public List<Board> findAll(){
        Query query = em.createNativeQuery("select * from board_tb order by id desc",Board.class);

        List<Board> boardList = query.getResultList();

        return boardList;
    }

    // 보드 상세보기
    public Board findById(Integer id){
        Query query = em.createNativeQuery("select * from board_tb where id = ?",Board.class);
        query.setParameter(1,id);

        Board board = (Board) query.getSingleResult();

        return board ;
    }

    // 게시글 저장
    @Transactional
    public void save(String title,String content, String username){
        Query query = em.createNativeQuery("insert into board_tb(title,content,user_name,created_at) values(?,?,?,now())");

        query.setParameter(1,title);
        query.setParameter(2,content);
        query.setParameter(3,username);

        query.executeUpdate();
    }

    // 게시글 삭제
    @Transactional
    public void deleteById(Integer id){
        Query query = em.createNativeQuery("delete from board_tb where id =?");

        query.setParameter(1,id);

        query.executeUpdate();
    }


    //게시글 수정
    @Transactional
    public void updateById(String title,String content, String userName,Integer id){
        Query query = em.createNativeQuery("update board_tb set  title = ?, content = ? ,user_name = ? where id =?");

        query.setParameter(1,title);
        query.setParameter(2,content);
        query.setParameter(3,userName);
        query.setParameter(4,id);

        query.executeUpdate();
    }
}
