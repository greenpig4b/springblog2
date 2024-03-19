package shop.mtcoding.blog.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;




@DataJpaTest
public class ReplyJPARepositoryTest {

    //목록보기 -> One 관계 1 - 1 join , Many 관계 1 - N 조회 -> DTO 생성
    //목록보기 -> Many 관계를 양방향 매핑하기
    @Autowired
    private ReplyJPARepository replyJPARepository;



}
