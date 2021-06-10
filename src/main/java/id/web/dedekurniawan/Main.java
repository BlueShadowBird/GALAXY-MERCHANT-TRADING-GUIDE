package id.web.dedekurniawan;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, InformationException {
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
            if(line.startsWith("how much is")){
                line = line.replace("how much is", "").replace("?", "").trim();
                System.out.println(line + " is " + RomanNumeralConverter.convert(convertRomanAlias(aliases, line)));
            }else if(line.startsWith("how many Credits is")){
                line = line.replace("how many Credits is", "").replace("?", "").trim();
                String[] splitedLine = line.split(" ");
                String entityName = splitedLine[splitedLine.length-1];
                splitedLine = Arrays.copyOf(splitedLine, splitedLine.length-1);

                double credits = (RomanNumeralConverter.convert(convertRomanAliasSplited(aliases, splitedLine))*(double)entity.getValue(entityName));
                System.out.println(line + " is " + credits + " Credits");
            }else{
                System.out.println("I have no idea what you are talking about");
            }

            if(lines.isEmpty())
                break;

            line = lines.remove(0);
        }
    }

    private static String convertRomanAliasSplited(Aliases aliases, String[] expressionSplited) throws InformationException {
        StringBuffer romanSymbol = new StringBuffer();
        for (String s : expressionSplited) {
            String symbol = (String) aliases.getValue(s);
            if (symbol == null)
                throw new InformationException("Unknown Alias: " + s);
            romanSymbol.append(symbol);
        }
        return romanSymbol.toString();
    }

    private static String convertRomanAlias(Aliases aliases, String expression) throws InformationException {
        return convertRomanAliasSplited(aliases, expression.split(" "));
    }
}
