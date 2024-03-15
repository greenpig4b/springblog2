package shop.mtcoding.blog.board;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import shop.mtcoding.blog.user.User;

public class BoardRequest {
    @Builder
    public static class SaveDTO{
        private String title;
        private String content;

        //DTO를 클라이언트로 부터 받아서, PC에 전달하기 위해서 사용
        //toEntity 는 insert할때만 사용한다.
        public Board toEntity(User user){
            return Board.builder()
                    .title(title)
                    .content(content)
                    .user(user)
                    .build();
        }

    }

    @Data
    public static class UpdateDTO{
        private String title;
        private String content;

    }

}
