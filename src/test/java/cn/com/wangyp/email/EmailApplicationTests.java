package cn.com.wangyp.email;

import cn.com.wangyp.email.entity.EmailElement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailApplicationTests {


    @Autowired
    EmailElement emailElement;
    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
        System.out.println(emailElement.getUrlCommon());
    }

}
