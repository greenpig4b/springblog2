package shop.mtcoding.blog.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardJPARepository extends JpaRepository<Board,Integer> {

    @Query("select b from Board b join fetch b.user u where b.id = :id")
    Optional<Board> findByJoinUser(@Param("id") Integer id);

//    @Query("select b from Board b join fetch b.user u left join fetch b.replyList r where b.id = :id")
//    Optional<Board> findByJoinUserAndReplys(@Param("id") Integer id);
}
