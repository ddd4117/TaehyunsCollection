package io.github.thcollection;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Component
public class CoronaCrawler {
    private static final String coronaUrl = "https://wuhanvirus.kr/";

    @PostConstruct
    public void init() throws IOException {
        this.crawlingCoronaData();
    }

    @Scheduled(cron = "* * 5 * * * *")
    public void getCoronaInfo() throws IOException {
        this.crawlingCoronaData();
    }

    public CoronaData crawlingCoronaData() throws IOException{
//        WebDriver driver = new ChromeDriver();
//        driver.get(coronaUrl);
//        Document doc = Jsoup.parse(driver.getPageSource());

        Document doc = null;
        var response = Jsoup.connect(coronaUrl).timeout(5000).execute();
//        WebClient webClient = new WebClient();
//        HtmlPage myPage = webClient.getPage(new File("page.html").toURI().toURL());
        if (response.statusCode() == HttpStatus.OK.value()) {
            doc = response.parse();
            String yellow = doc.select("span.koreanText.yellow").text();
            String green = doc.select("span.koreanText.green").text();
            String red = doc.select("span.koreanText.red").text();

            log.info("yellow{}, green{}, red{}", yellow, green, red);

            CoronaData coronaData = CoronaData.builder()
                    .today(yellow)
                    .yesterday(green)
                    .increase(red)
                    .build();
            return coronaData;
        }
        return new CoronaData();
    }
}
