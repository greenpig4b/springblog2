package shop.mtcoding.blog.board;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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

    // =================================================================




}
