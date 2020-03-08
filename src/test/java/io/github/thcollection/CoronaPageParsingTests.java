package io.github.thcollection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class CoronaPageParsingTests {
    PageParsing page = new PageParsing();

    @Before
    public void init() throws IOException {
        page.init();
    }

    @Test
    public void 데이터수집_정상작동() throws IOException {
        CoronaData coronaData = page.coronaPageParsing();
        Assert.assertNotEquals(coronaData.getIncrease(), -1L);
        Assert.assertNotEquals(coronaData.getIncrease(), -1L);
        Assert.assertNotEquals(coronaData.getIncrease(), -1L);
    }
}
