package id.web.dedekurniawan;

import id.web.dedekurniawan.exception.InformationException;
import id.web.dedekurniawan.model.Aliases;
import org.junit.Assert;
import org.junit.Test;

public class AliasesTest {
    @Test
    public void testSuccess() throws InformationException {
        Aliases aliases = new Aliases();
        aliases.addInformation("glob is I");
        aliases.addInformation("prok is V");
        aliases.addInformation("pish is X");
        aliases.addInformation("tegj is L");


        Assert.assertEquals("I", aliases.getValue("glob"));
        Assert.assertEquals("V", aliases.getValue("prok"));
        Assert.assertEquals("X", aliases.getValue("pish"));
        Assert.assertEquals("L", aliases.getValue("tegj"));
    }

    @Test
    public void testFail(){
        Aliases aliases = new Aliases();

        Assert.assertThrows(InformationException.class, () -> aliases.addInformation("glob I"));
        Assert.assertThrows(InformationException.class, () -> aliases.addInformation("pish were X"));
        Assert.assertThrows(InformationException.class, () -> aliases.addInformation("L"));
    }
}
