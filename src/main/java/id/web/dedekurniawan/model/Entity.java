package id.web.dedekurniawan.model;


import id.web.dedekurniawan.exception.InformationException;
import id.web.dedekurniawan.exception.NumeralConvertionException;
import id.web.dedekurniawan.utility.NumeralConverter;

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
    NumeralConverter converter;

    public Entity(Aliases aliases, NumeralConverter converter) {
        super();
        this.aliases = aliases;
        this.converter = converter;
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
        double totalCredits = Double.parseDouble(splitedExpression[1].replace("Credits", "").trim());

        StringBuilder RomanSymbol = new StringBuilder();

        if(splitedExpression.length<2)
            throw new InformationException("Invalid Expression");

        //String before is, contain Entity and romans symbols(alias)
        String[] leftExpressionSplited = splitedExpression[0].trim().split(" ");
        String entityName = leftExpressionSplited[leftExpressionSplited.length-1];
        leftExpressionSplited = Arrays.copyOf(leftExpressionSplited, leftExpressionSplited.length-1);
        for (String s : leftExpressionSplited) {
            String symbol = (String) aliases.getValue(s);
            if (symbol == null)
                throw new InformationException("Unknown Alias", s);
            RomanSymbol.append(symbol);
        }
        double totalEntities;
        try {
            totalEntities = converter.convert(RomanSymbol.toString());
        } catch (NumeralConvertionException e) {
            throw new InformationException(e.getCause());
        }
        addInformation(entityName, totalCredits/totalEntities);
    }

    @Override
    public String getInformationType() {
        return InformationType.ENTITY.toString();
    }
}
