package id.web.dedekurniawan;

import id.web.dedekurniawan.exception.InformationException;
import id.web.dedekurniawan.exception.RomanConvertException;
import id.web.dedekurniawan.model.Aliases;
import id.web.dedekurniawan.model.Entity;
import id.web.dedekurniawan.utility.RomanNumeralConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, InformationException, RomanConvertException {
        Aliases aliases = new Aliases();

        List<String> lines = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

//	    process alias
        line = lines.remove(0);
	    do{
            aliases.addInformation(line);
            line = lines.remove(0);
        }while(!line.endsWith("Credits"));

	    //process entity information, do while because at least
        Entity entity = new Entity(aliases);
        do{
            entity.addInformation(line);
            line = lines.remove(0);
        }while(!line.endsWith("?"));

        while(true){
            try {
                if(line.startsWith("how much is")){
                    line = line.replace("how much is", "").replace("?", "").trim();
                    System.out.println(line + " is " + RomanNumeralConverter.convert(convertRomanAlias(aliases, line)));
                }else if(line.startsWith("how many Credits is")){
                    line = line.replace("how many Credits is", "").replace("?", "").trim();
                    String[] splitedLine = line.split(" ");
                    String entityName = splitedLine[splitedLine.length-1];
                    splitedLine = Arrays.copyOf(splitedLine, splitedLine.length-1);

                    Double price = (Double)entity.getValue(entityName);
                    if(price == null)
                        throw new InformationException("Unknown Entity", entityName);

                    double credits = (RomanNumeralConverter.convert(convertRomanAliasSplited(aliases, splitedLine))*price);
                    System.out.println(line + " is " + format(credits) + " Credits");
                }else{
                    System.out.println("I have no idea what you are talking about");
                }
            }catch (RomanConvertException e){
                if("Max Symbol Repetation exceeded".equals(e.getMessage()))
                    System.out.println("Requested number is in invalid format");
                else
                    throw e;
            }catch (InformationException e){
                if("Unknown Alias".equals(e.getMessage()))
                    System.out.println("Unknown alias for " + e.getErrorArgument());
                else if("Unknown Entity".equals(e.getMessage()))
                    System.out.println("Unknown Entity for " + e.getErrorArgument());
                else
                    throw e;
            }

            if(lines.isEmpty())
                break;

            line = lines.remove(0);
        }
    }

    private static String convertRomanAliasSplited(Aliases aliases, String[] expressionSplited) throws InformationException {
        StringBuilder romanSymbol = new StringBuilder();
        for (String s : expressionSplited) {
            String symbol = (String) aliases.getValue(s);
            if (symbol == null)
                throw new InformationException("Unknown Alias", s);
            romanSymbol.append(symbol);
        }
        return romanSymbol.toString();
    }

    private static String convertRomanAlias(Aliases aliases, String expression) throws InformationException {
        return convertRomanAliasSplited(aliases, expression.split(" "));
    }

    private static String format(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }
}
