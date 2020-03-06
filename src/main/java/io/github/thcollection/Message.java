package io.github.thcollection;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@ToString
public class Message {
    private String objectType = "text";
    private String text;
    private String buttonTitle;

    @Getter
    private Map<String, String> link = new HashMap<>();

    @JsonGetter("object_type")
    public String getObjectType() {
        return objectType;
    }

    public String getText() {
        return text;
    }

    @JsonGetter("button_title")
    public String getButtonTitle() {
        return buttonTitle;
    }

    @Builder
    public Message(String text, String buttonTitle, String url) {
        this.text = text;
        this.buttonTitle = buttonTitle;
        link.put("web_url", url);
        link.put("mobile_web_url", url);
    }
}
