package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardResponse {

    @Data
    public static class MainDTO{
        private Integer id;
        private String title;

        public MainDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }

    }


    @Data
    public static class DetailDTO{
        private Integer id;
        private String title;
        private String content;
        private UserDTO user;
        private Boolean isOwner;

        public DetailDTO(Board board, Integer sessionUserId) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.isOwner = false;

            if (sessionUserId == id){
                isOwner = true;
            }
        }

        public static class UserDTO{
            private Integer id;
            private String userName;

            public UserDTO(Integer id, String userName) {
                this.id = id;
                this.userName = userName;
            }

        }
    }
}
