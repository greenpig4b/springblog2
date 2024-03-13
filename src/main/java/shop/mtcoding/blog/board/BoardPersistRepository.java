package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository

public class BoardPersistRepository {
    private final EntityManager em;


    // 보드 리스트 화면에뿌림
    public List<Board> findAll(){
        Query query =em.createQuery(" select b from Board b order by b.id desc",Board.class);

        return query.getResultList();
    }


    // 보드 상세보기
    public Board findById(Integer id){

        Board board = em.find(Board.class,id);

        return board;

    }


    // 게시글 저장 퍼시스트 컨택트
    @Transactional
    public Board save(Board board){
        // persist Contect 는 entity객체 만 들어갈수있음 DTO 못넣음

        // 1. 비영속개체
        em.persist(board);

        // 2. board --> 영속 객체
        return board;
    }

    // 게시글 삭제
    @Transactional
    public void deleteByIdV2(Integer id){
        //삭제전에 id 값으로 로우 들고오기
        Board board = findById(id);
        //조회된 로우 삭제
        em.remove(board);  //PC 에 객체 지우고 , (트렌젝션)종료시에 삭제 쿼리가 날아간다
        // 비지니스 로직은 Service에서 처리하도록 하는게 좋다

    }

    // 게시글 삭제
    @Transactional
    public void deleteById(Integer id){
        //삭제전에 id 값으로 로우 들고오기
        Query query = em.createQuery("delete from Board b where b.id = :id");
        //조회된 로우 삭제
        query.setParameter("id",id);
        query.executeUpdate();
        // 비지니스 로직은 Service에서 처리하도록 하는게 좋다

    }






    //게시글 수정
    @Transactional
    public void updateById(String title,String content, String userName,Integer id){

    }

}
