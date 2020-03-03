package io.github.thcollection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Slf4j
public class KakaoMessageSender {
    private final RestTemplate restTemplate;

    public void message(String accessToken, String title, String message) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> formdata = new LinkedMultiValueMap<>();
        formdata.add("template_object", this.messageTemplate(title, message));
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formdata, headers);

        ResponseEntity<KakaoResult> result = restTemplate.exchange("https://kapi.kakao.com/v2/api/talk/memo/default/send",
                HttpMethod.POST,
                entity,
                KakaoResult.class
        );

        log.info("result : {}", result);
    }

    private String messageTemplate(String title, String message) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n" +
                "   \"object_type\":\"text\",\n" +
                "   \"text\":\"")
                .append(title)
                .append("\",\n" +
                        "   \"link\":{\n" +
                        "      \"web_url\":\"http://yourwebsite.for.pc\",\n" +
                        "      \"mobile_web_url\":\"http://yourwebsite.for.mobile\"\n" +
                        "   },\n" +
                        "   \"button_title\":\"")
                .append(message)
                .append("\"\n}");

        return builder.toString();
    }
}
