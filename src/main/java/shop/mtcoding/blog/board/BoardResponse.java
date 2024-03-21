package shop.mtcoding.blog.board;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class BoardResponse {

    @Data
    public static class DetailDTO{
        private Integer id;
        private String title;
        private String content;
        private Integer userId;
        private Boolean isOwner;
        private List<ReplyDTO> replies = new ArrayList<>();

        @Data
        private class ReplyDTO{
            private Integer id;
            private String comment;
            private Integer userId;
            private String userName;
        }
    }
}
