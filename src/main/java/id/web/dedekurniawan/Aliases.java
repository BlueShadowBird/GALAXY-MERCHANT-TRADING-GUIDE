package id.web.dedekurniawan;

import java.util.StringJoiner;

/**
 * Class represent the alias of raman symbol, inherite from information class.
 * variable name is the alias. example: "prok is V", the alias is "Prok"
 * variable value is roman symbol. "prok is V", the value is "V"
 *
 *
 * @author  Dede Kurniawan
 * @version 1.0
 * @since   2021-06-10
 */
public class Aliases extends Informations {
    public Aliases() {
        super();
    }

    @Override
    public void addInformation(String expression) throws InformationException {
        String[] splittedExpression = expression.split(" ");
        if(splittedExpression.length<3 || !"is".equalsIgnoreCase(splittedExpression[1]))
            throw new InformationException("Invalid Expression");
        StringJoiner joiner = new StringJoiner(" ");
        for(int i=2;i<splittedExpression.length;i++)
            joiner.add(splittedExpression[i].trim());
        addInformation(splittedExpression[0], joiner.toString());
    }

    @Override
    public String getInformationType() {
        return InformationType.ALIAS.toString();
    }
}
