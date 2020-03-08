package io.github.thcollection.scheduler;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import io.github.thcollection.CoronaData;
import io.github.thcollection.KakaoMessageSender;
import io.github.thcollection.PageParsing;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CoronaCrawler {
    private final KakaoMessageSender kakaoMessageSender;
    private final PageParsing pageParsing;

    @PostConstruct
    public void init() throws IOException {
    }

    @Scheduled(cron = "* * 5 * * * *")
    public void getCoronaInfo() throws IOException {
        CoronaData coronaData = pageParsing.coronaPageParsing();
    }
}