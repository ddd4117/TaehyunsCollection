package io.github.thcollection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
@Slf4j
public class KakaoMessageSender {
    private static final String AUTHORIZATION = "Authorization";
    private static final String HEADER_BEARER = "Bearer ";
    private static final String TEMPLATE_OBJECT = "template_object";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private @Value("${url.kakaoSend}") String kakaoUrl = "https://kapi.kakao.com/v2/api/talk/memo/default/send";

    public void sendMessage(String accessToken, Message message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(AUTHORIZATION, HEADER_BEARER + accessToken);

        MultiValueMap<String, String> formdata = new LinkedMultiValueMap<>();
        try {
            formdata.add(TEMPLATE_OBJECT, objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formdata, headers);

        ResponseEntity<KakaoResult> result = restTemplate.exchange(kakaoUrl,
                HttpMethod.POST,
                entity,
                KakaoResult.class
        );

        log.info("result : {}", result);
    }
}
