package id.web.dedekurniawan;

import id.web.dedekurniawan.exception.InformationException;
import id.web.dedekurniawan.model.Aliases;
import id.web.dedekurniawan.model.Entity;
import id.web.dedekurniawan.utility.NumeralConverter;
import id.web.dedekurniawan.utility.RomanNumeralConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EntityTest {
    Aliases aliases;
    NumeralConverter converter;

    @Before
    public void init() throws InformationException {
        converter = new RomanNumeralConverter();
        aliases = new Aliases();
        aliases.addInformation("glob is I");
        aliases.addInformation("prok is V");
        aliases.addInformation("pish is X");
        aliases.addInformation("tegj is L");
    }
    @Test
    public void testAddEntity() throws InformationException {
        Entity entity = new Entity(aliases, converter);
        entity.addInformation("glob glob Silver is 34 Credits");
        entity.addInformation("glob prok Gold is 57800 Credits");
        entity.addInformation("pish pish Iron is 3910 Credits");


        Assert.assertEquals(17.0, entity.getValue("Silver"));
        Assert.assertEquals(14450.0, entity.getValue("Gold"));
        Assert.assertEquals(195.5, entity.getValue("Iron"));
    }

    @Test
    public void testAddEntityFail() throws InformationException {
        Entity entity = new Entity(aliases, converter);
        Assert.assertThrows(InformationException.class, () -> entity.addInformation("glob glo Silver is 34 Credits"));
    }
}
