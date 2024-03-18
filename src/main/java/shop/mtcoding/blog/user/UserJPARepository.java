package shop.mtcoding.blog.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//인터페이스는 인터페이스 상속가능 - 문법 extends 인터페이스 new 못함
// 상속받는 타입과 primary key타입
// 자동 컴퍼넌프 스캔이 된다.
// 인터페이스 정리필요!!
public interface UserJPARepository extends JpaRepository<User,Integer>{

    //@Query("select u from User u where u.userName = :userName and u.password = :password")
    User findByUserNameAndPassword(@Param("userName")String userName,@Param("password")String password);
    // 간단한쿼리 JPA Repository  복잡한쿼리 User Repository
}

