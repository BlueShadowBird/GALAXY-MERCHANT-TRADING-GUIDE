package id.web.dedekurniawan;

import id.web.dedekurniawan.exception.NumeralConvertionException;
import id.web.dedekurniawan.utility.NumeralConverter;
import id.web.dedekurniawan.utility.RomanNumeralConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RomanNumeralConverterTest {
    NumeralConverter converter;

    @Before
    public void init(){
        converter = new RomanNumeralConverter();
    }

    @Test
    public void testConvertSuccess() throws NumeralConvertionException {
        Assert.assertEquals(1903, converter.convert("MCMIII"));
        Assert.assertEquals(1944, converter.convert("MCMXLIV"));
        Assert.assertEquals(2006, converter.convert("MMVI"));
    }

    @Test
    public void testConvertRepetationMoreThan3(){
        Assert.assertThrows(NumeralConvertionException.class, () -> converter.convert("MMMM"));
        Assert.assertThrows(NumeralConvertionException.class, () -> converter.convert("MLLLL"));
    }
}
