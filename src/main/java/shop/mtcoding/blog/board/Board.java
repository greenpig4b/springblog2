package shop.mtcoding.blog.board;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.user.User;


import java.sql.Timestamp;

@Data
@Table(name = "board_tb")
@Entity
@NoArgsConstructor // 디폴트 생성자가 있어야한다.

public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    // @JoinColumn(name = "user_id") 직접변경할 수 있음
    @ManyToOne(fetch = FetchType.LAZY)  //LAZY 전략 join 필요한건 직접적어서 결과값 가져오기
    private User user;

    @CreationTimestamp  // pc --> db(알아서 날짜주입해줌)
    private Timestamp createdAt;

    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }


    public void update(BoardRequest.UpdateDTO requestDTO){
        this.title = requestDTO.getTitle();
        this.content = requestDTO.getContent();

    }

}