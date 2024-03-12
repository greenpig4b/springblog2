package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository

public class BoardPersistRepository {
    private final EntityManager em;

    // 보드 리스트 화면에뿌림
    public void findAll(){

    }

    // 보드 상세보기
    public void findById(Integer id){

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
    public void deleteById(Integer id){

    }

    //게시글 수정
    @Transactional
    public void updateById(String title,String content, String userName,Integer id){

    }
}
