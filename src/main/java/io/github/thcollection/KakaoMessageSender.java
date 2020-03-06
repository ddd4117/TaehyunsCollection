package io.github.thcollection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
@Slf4j
public class KakaoMessageSender {
    private final RestTemplate restTemplate;
    private ObjectMapper objectMapper = new ObjectMapper();
    public void message(String accessToken, Message message) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> formdata = new LinkedMultiValueMap<>();
        try {
            formdata.add("template_object", objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formdata, headers);

        ResponseEntity<KakaoResult> result = restTemplate.exchange("https://kapi.kakao.com/v2/api/talk/memo/default/send",
                HttpMethod.POST,
                entity,
                KakaoResult.class
        );

        log.info("result : {}", result);
    }
}
