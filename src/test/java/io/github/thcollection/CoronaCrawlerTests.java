package io.github.thcollection;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.thcollection.scheduler.CoronaCrawler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class CoronaCrawlerTests {
    KakaoMessageSender kakaoMessageSender = new KakaoMessageSender(new RestTemplate(), new ObjectMapper());
    CoronaCrawler crawler = new CoronaCrawler(kakaoMessageSender);

    @Before
    public void init() throws IOException {
        crawler.init();
    }

    @Test
    public void 데이터수집_정상작동() throws IOException {
        CoronaData coronaData = crawler.crawlingCoronaData();
        Assert.assertNotEquals(coronaData.getIncrease(), -1L);
        Assert.assertNotEquals(coronaData.getIncrease(), -1L);
        Assert.assertNotEquals(coronaData.getIncrease(), -1L);
    }
}
