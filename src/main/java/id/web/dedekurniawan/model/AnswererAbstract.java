package id.web.dedekurniawan.model;

import id.web.dedekurniawan.exception.InformationException;
import id.web.dedekurniawan.utility.NumeralConverter;

/**
 * Class represent the question, if need new format/pattern of question, just extend this class and add it to AnswererOfficer.
 *
 * @author  Dede Kurniawan
 * @version 1.0
 * @since   2021-06-10
 */
abstract class AnswererAbstract {
    NumeralConverter converter;
    Aliases aliases;
    Entity entity;

    public AnswererAbstract(Aliases aliases, Entity entity, NumeralConverter converter) {
        this.aliases = aliases;
        this.entity = entity;
        this.converter = converter;
    }

    protected String question;
    /** Ask to the class, is the question suitable to this implementation
     * @param question question that will be answered.
     * @return True if the question is suitable to this implementation.
     */
    abstract boolean askIfUnderstand(String question);

    /** Ask the answer to class.
     * @return The Answer of the question.
     */
    abstract String askForAnswer() throws InformationException;


    protected static String convertAliasSplited(Aliases aliases, String[] expressionSplited) throws InformationException {
        StringBuilder symbolBuilder = new StringBuilder();
        for (String s : expressionSplited) {
            String symbol = (String) aliases.getValue(s);
            if (symbol == null)
                throw new InformationException("Unknown Alias", s);
            symbolBuilder.append(symbol);
        }
        return symbolBuilder.toString();
    }

    protected static String convertAlias(Aliases aliases, String expression) throws InformationException {
        return convertAliasSplited(aliases, expression.split(" "));
    }

    protected static String format(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }
}
