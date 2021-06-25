package id.web.dedekurniawan.model;

import id.web.dedekurniawan.exception.InformationException;
import id.web.dedekurniawan.exception.NumeralConvertionException;
import id.web.dedekurniawan.utility.NumeralConverter;

public class HowMuchIs extends AnswererAbstract {

    public HowMuchIs(Aliases aliases, Entity entity, NumeralConverter converter) {
        super(aliases, entity, converter);
    }

    @Override
    public boolean askIfUnderstand(String question) {
        if(question.startsWith("how much is")) {
            this.question=question;
            return true;
        }
        return false;
    }

    @Override
    public String askForAnswer() throws InformationException {
        try {
            String line = question.replace("how much is", "").replace("?", "").trim();
            return line + " is " + converter.convert(convertAlias(aliases, line));
        } catch (NumeralConvertionException e) {
            return e.getMessage();
        } catch (InformationException e) {
            if("Unknown Alias".equals(e.getMessage()))
                return "Unknown alias for " + e.getErrorArgument();
            else if("Unknown Entity".equals(e.getMessage()))
                return "Unknown Entity for " + e.getErrorArgument();
            else
                throw e;
        }
    }
}
