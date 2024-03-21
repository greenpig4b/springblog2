package shop.mtcoding.blog.user;

import lombok.Data;

public class UserResponse {

    //응답 : 생성자
    @Data
    public static class DTO{
        private Integer id;
        private String username;
        private String email;

        public DTO(User user) {
            this.id = user.getId();
            this.username = user.getUserName();
            this.email = user.getEmail();
        }
    }

}
