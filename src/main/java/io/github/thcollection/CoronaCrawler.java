package io.github.thcollection;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Component
public class CoronaCrawler {
    private static final String coronaUrl = "https://livecorona.co.kr/";

    @PostConstruct
    public void init() throws IOException {
        this.getCoronaInfo();
    }

    @Scheduled(cron = "* * 5 * * * *")
    public void getCoronaInfo() throws IOException {
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
        }
    }


    public String getHtml3(String url) {

        try {

            URL targetUrl = new URL(url);

            BufferedReader reader = new BufferedReader(new InputStreamReader(targetUrl.openStream(), "UTF-8"));

            StringBuilder html = new StringBuilder();

            String current = "";

            while ((current = reader.readLine()) != null) {

                html.append(current);

            }

            reader.close();

            return html.toString();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return null;
    }
}
