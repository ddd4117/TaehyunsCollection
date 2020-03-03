package io.github.thcollection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KakaoMessageSenderTests {
    @Autowired
    KakaoMessageSender messageSender;

    @Value("${kakao.access_token}")
    String accessToken;

    @Test
    public void messageTests() {
        messageSender.message(accessToken, "hello");
    }
}
