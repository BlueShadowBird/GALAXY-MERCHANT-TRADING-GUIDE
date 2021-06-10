package id.web.dedekurniawan;


import java.util.Arrays;

/**
 * Class represent the entity of common metals and dirt, inherite from information class.
 * variable name is type of entity. example: Gold, Silver, Iron, etc
 * variable value is the price(in Credit) the Entity
 *
 *
 * @author  Dede Kurniawan
 * @version 1.0
 * @since   2021-06-10
 */

public class Entity extends Informations{
    Aliases aliases;

    public Entity() {
        super();
    }

    public Entity(Aliases aliases) {
        super();
        this.aliases = aliases;
    }

    public void setAliases(Aliases aliases) {
        this.aliases = aliases;
    }

    @Override
    public void addInformation(String expression) throws InformationException {
        if(aliases == null)
            throw new InformationException("No Aliases given");

        String[] splitedExpression = expression.split(" is ");

        //get Total Credits
        double totalCredits = Double.valueOf(splitedExpression[1].replace("Credits", "").trim());

        StringBuilder RomanSymbol = new StringBuilder();

        if(splitedExpression.length<2)
            throw new InformationException("No Aliases given");

        //String before is, contain Entity and romans symbols(alias)
        String[] leftExpressionSplited = splitedExpression[0].trim().split(" ");
        String entityName = leftExpressionSplited[leftExpressionSplited.length-1];
        leftExpressionSplited = Arrays.copyOf(leftExpressionSplited, leftExpressionSplited.length-1);
        for (String s : leftExpressionSplited) {
            String symbol = (String) aliases.getValue(s);
            if (symbol == null)
                throw new InformationException("Unknown Alias: " + s);
            RomanSymbol.append(symbol);
        }
        double totalEntities = RomanNumeralConverter.convert(RomanSymbol.toString());
        addInformation(entityName, totalCredits/totalEntities);
    }

    @Override
    public String getInformationType() {
        return InformationType.ENTITY.toString();
    }
}
