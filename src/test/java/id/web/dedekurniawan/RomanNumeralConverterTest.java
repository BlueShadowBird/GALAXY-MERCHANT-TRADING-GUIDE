package id.web.dedekurniawan;

import org.junit.Assert;
import org.junit.Test;

public class RomanNumeralConverterTest {
    @Test
    public void test(){
        Assert.assertEquals(1903, RomanNumeralConverter.convert("MCMIII"));
        Assert.assertEquals(1944, RomanNumeralConverter.convert("MCMXLIV"));
        Assert.assertEquals(2006, RomanNumeralConverter.convert("MMVI"));
    }
}
