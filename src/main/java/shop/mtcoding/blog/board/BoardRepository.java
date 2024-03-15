package shop.mtcoding.blog.board;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.blog.user.User;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public Board findById(Integer id){

        Board board = em.find(Board.class,id);
        return board;
    }

    // 상세보기
    public Board findByIdJoinUser(Integer id){
        Query query = em.createQuery("select b from Board b join fetch b.user u where b.id = :id",Board.class);
        query.setParameter("id",id);
        Board board = (Board) query.getSingleResult();
        return board;
    }

    //목록보기
    public List<Board> findAll(){
        Query query = em.createQuery("select b from Board b order by b.id desc ",Board.class);
        List<Board> boardList = query.getResultList();

        return  boardList;
    }

    //글쓰기
    @Transactional
    public Board save(Board board){
        em.persist(board);

        return board;
    }
    //Test 안하는이유는 이미 만들어져있는 라이브러리 이므로 할 필요가 없다.


    //삭제하기
    @Transactional
    public void deleteById(Integer id){
        Query query = em.createQuery("delete from Board b where b.id = :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

    //수정하기
    @Transactional
    public void update(Integer id,BoardRequest.UpdateDTO updateDTO){
        Board board = findById(id);
        board.setTitle(updateDTO.getTitle());
        board.setTitle(updateDTO.getContent());

    }
}
