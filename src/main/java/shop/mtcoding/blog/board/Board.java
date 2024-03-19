package shop.mtcoding.blog.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    // EAGER 디폴트
    //@JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // db -> user_id

    @CreationTimestamp // pc -> db (날짜주입)
    private Timestamp createdAt;

    @Transient // 테이블 생성이 안됨
    private boolean BoardOwner;

    // LAZY 디폴트
    @OrderBy("id desc ")
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    // 필드생성하지않고 담는용도로만 쓰임 Entity객체 필드명으로 mapperdBy 설정
    private List<Reply> replyList = new ArrayList<>(); // 초기화 시키는이유 null 들어가면 터질 수 있으므로

    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }
}