package io.github.thcollection;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KakaoMessageSenderTests {
    @Autowired
    KakaoMessageSender messageSender;

    @Value("${kakao.access_token}")
    String accessToken;

    String title = "제목";
    String body = "본문";
    String url = "url";
    Message message;

    @Before
    public void setUp() {
        message = Message.builder()
                .buttonTitle(body)
                .text(title)
                .url(url)
                .build();
    }

    @Test
    public void messageTests() {
        messageSender.sendMessage(accessToken, message);
    }

    @Test
    public void 템플릿() throws IOException, JSONException {
        ObjectMapper objectMapper = new ObjectMapper();
        String messageJson = objectMapper.writeValueAsString(message);
        JSONAssert.assertEquals(messageJson, "{\"text\":\"제목\",\"link\":{\"web_url\":\"url\",\"mobile_web_url\":\"url\"},\"object_type\":\"text\",\"button_title\":\"본문\"}", false);
    }
}

