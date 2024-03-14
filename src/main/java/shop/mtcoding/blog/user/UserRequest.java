package shop.mtcoding.blog.user;

import lombok.Data;

public class UserRequest {


    @Data
    public static class LoginDTO{
        private String userName;
        private String password;
    }

}
