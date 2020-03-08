package io.github.thcollection;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.IOException;

public class PageParsing {
    private WebClient webClient = null;
    private @Value("${corona.url}") String coronaUrl = "https://livecorona.co.kr";

    @PostConstruct
    public void init() throws IOException {
        webClient = new WebClient(BrowserVersion.FIREFOX_60);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getCookieManager().setCookiesEnabled(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
    }

    public CoronaData coronaPageParsing() throws IOException {
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
