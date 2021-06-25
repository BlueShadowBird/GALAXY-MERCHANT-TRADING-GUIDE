package id.web.dedekurniawan.model;

import id.web.dedekurniawan.exception.InformationException;
import id.web.dedekurniawan.exception.NumeralConvertionException;
import id.web.dedekurniawan.utility.NumeralConverter;

import java.util.Arrays;

public class HowManyCredits extends AnswererAbstract {

    public HowManyCredits(Aliases aliases, Entity entity, NumeralConverter converter) {
        super(aliases, entity, converter);
    }

    @Override
    public boolean askIfUnderstand(String question) {
        if(question.startsWith("how many Credits is")) {
            this.question=question;
            return true;
        }
        return false;
    }

    @Override
    public String askForAnswer() throws InformationException {
        try {
            String line = question.replace("how many Credits is", "").replace("?", "").trim();
            String[] splitedLine = line.split(" ");
            String entityName = splitedLine[splitedLine.length-1];
            splitedLine = Arrays.copyOf(splitedLine, splitedLine.length-1);

            Double price = (Double)entity.getValue(entityName);
            if(price == null)
                return "Unknown alias for " + entityName;

            double credits = (converter.convert(convertAliasSplited(aliases, splitedLine))*price);

            return line + " is " + format(credits) + " Credits";
        }catch (NumeralConvertionException e) {
            return e.getMessage();
        }catch (InformationException e){
            if("Unknown Alias".equals(e.getMessage()))
                return "Unknown alias for " + e.getErrorArgument();
            else if("Unknown Entity".equals(e.getMessage()))
                return "Unknown Entity for " + e.getErrorArgument();
            else
                throw e;
        }
    }
}
