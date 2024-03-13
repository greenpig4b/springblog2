package shop.mtcoding.blog.board;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


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
    private String userName;
    private Integer me;

    public Board(String title, String content, String userName, Integer me) {
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.me = me;
    }

    @CreationTimestamp  // pc --> db(알아서 날짜주입해줌)
    private Timestamp createdAt;

}