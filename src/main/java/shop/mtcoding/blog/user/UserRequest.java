package shop.mtcoding.blog.user;

import lombok.Data;

public class UserRequest {


    @Data
    public static class LoginDTO{
        private String userName;
        private String password;
    }

    @Data
    public static class JoinDTO{
        private String userName;
        private String password;
        private String email;

        public User toEntity(){
            return User.builder()
                    .userName(userName)
                    .password(password)
                    .email(email)
                    .build();
        }
    }
}
