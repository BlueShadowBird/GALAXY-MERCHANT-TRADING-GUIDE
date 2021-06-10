package id.web.dedekurniawan;

import id.web.dedekurniawan.exception.InformationException;
import id.web.dedekurniawan.exception.RomanConvertException;
import id.web.dedekurniawan.utility.RomanNumeralConverter;
import org.junit.Assert;
import org.junit.Test;

public class RomanNumeralConverterTest {
    @Test
    public void testConvertSuccess() throws RomanConvertException {
        Assert.assertEquals(1903, RomanNumeralConverter.convert("MCMIII"));
        Assert.assertEquals(1944, RomanNumeralConverter.convert("MCMXLIV"));
        Assert.assertEquals(2006, RomanNumeralConverter.convert("MMVI"));
    }

    @Test
    public void testConvertRepetationMoreThan3(){
        Assert.assertThrows(RomanConvertException.class, () -> RomanNumeralConverter.convert("MMMM"));
        Assert.assertThrows(RomanConvertException.class, () -> RomanNumeralConverter.convert("MLLLL"));
    }
}
