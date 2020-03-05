package io.github.thcollection;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CoronaCrawlerTests {
    CoronaCrawler crawler = new CoronaCrawler();

    @Test
    public void 데이터수집_정상작동() throws IOException {
        CoronaData coronaData = crawler.crawlingCoronaData();
        Assert.assertNotEquals(coronaData.getIncrease(), -1L);
        Assert.assertNotEquals(coronaData.getIncrease(), -1L);
        Assert.assertNotEquals(coronaData.getIncrease(), -1L);
    }
}
