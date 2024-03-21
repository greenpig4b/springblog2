package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.user.User;

import java.util.ArrayList;
import java.util.List;

public class BoardResponse {

    @Data
    public static class MainDTO {
        private Integer id;
        private String title;

        public MainDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }

    }


    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Integer userId;
        private String userName;
        private boolean isOwner;
        private List<ReplyDTO> replies = new ArrayList<>();

        public DetailDTO(Board board, User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
            this.userName = board.getUser().getUserName();
            this.isOwner = false;
            if (sessionUser != null) {
                if (sessionUser.getId() == userId ) {
                    isOwner = true;
                }
            }
            this.replies = board.getReplyList().stream().map(reply -> new ReplyDTO(reply, sessionUser)).toList();

        }

        // 안에서만 쓸꺼라서 내부에서 생성 밖에서 안만드는 이유~~~~~~~~~
        @Data
        private class ReplyDTO {
            private Integer id;
            private String comment;
            private Integer userId;
            private String userName;
            private boolean isOwner;

            public ReplyDTO(Reply reply, User sessionUser) {
                this.id = reply.getId(); // 여기서 LAZY로딩
                this.comment = reply.getComment();
                this.userId = reply.getUser().getId();
                this.userName = reply.getUser().getUserName();
                this.isOwner = false;
                if (sessionUser != null){
                    if (userId == sessionUser.getId()) {
                        isOwner = true;
                    }
                }
            }
        }
    }
}
