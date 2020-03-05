package io.github.thcollection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class CoronaCrawlerTests {
    CoronaCrawler crawler = new CoronaCrawler();

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
