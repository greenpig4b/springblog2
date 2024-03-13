package shop.mtcoding.blog.board;

import jakarta.persistence.Entity;
import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO{
        private String title;
        private String content;
        private String userName;
        private Integer me;

        public Board toEntity(){
            return  new Board(title,content,userName,me);
        }

    }

}
