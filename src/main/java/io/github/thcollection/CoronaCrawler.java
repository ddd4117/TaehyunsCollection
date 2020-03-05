package io.github.thcollection;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Slf4j
@Component
public class CoronaCrawler {
    private static final String coronaUrl = "https://livecorona.co.kr/";
    WebClient webClient = null;

    @PostConstruct
    public void init() throws IOException {
        webClient = new WebClient(BrowserVersion.FIREFOX_60);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getCookieManager().setCookiesEnabled(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
    }

    @Scheduled(cron = "* * 5 * * * *")
    public void getCoronaInfo() throws IOException {
        this.crawlingCoronaData();
    }

    public CoronaData crawlingCoronaData() throws IOException {
        HtmlPage page = webClient.getPage(coronaUrl);
        DomElement infected = page.getFirstByXPath("//span[@class='koreanText yellow']");
        DomElement complete = page.getFirstByXPath("//span[@class='koreanText green']");
        DomElement dead = page.getFirstByXPath("//span[@class='koreanText red']");

        return CoronaData.builder()
                .today(infected.asText())
                .yesterday(complete.asText())
                .increase(dead.asText())
                .build();
    }
}
