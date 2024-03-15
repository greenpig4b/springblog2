package shop.mtcoding.blog.user;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.board.BoardRequest;

import java.sql.Timestamp;

@Entity
@Table(name = "user_tb")
@Data
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String userName;
    private String password; // 비밀번호 만들때 hash 60자 넘어감
    private String email;
   // 유저 주인인지 아닌지 알아보기 위해서 넣어줌 주인이면 삭제 및 수정가능
    @CreationTimestamp  // pc --> db(알아서 날짜주입해줌)
    private Timestamp createdAt;


    @Builder
    public User(Integer id, String userName, String password, String email, Timestamp createdAt) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;

    }

}
