package id.web.dedekurniawan;

import id.web.dedekurniawan.exception.InformationException;
import id.web.dedekurniawan.model.*;
import id.web.dedekurniawan.utility.NumeralConverter;
import id.web.dedekurniawan.utility.RomanNumeralConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InformationException{
        String line;
        NumeralConverter converter = new RomanNumeralConverter();

//      read input
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

//	    extract alias
        Aliases aliases = new Aliases();
        line = lines.remove(0);
	    do{
            aliases.addInformation(line);//add alias
            line = lines.remove(0);
        }while(!line.endsWith("Credits"));

	    //extract entity information
        Entity entities = new Entity(aliases, converter);
        do{
            entities.addInformation(line);//add entity
            line = lines.remove(0);
        }while(!line.endsWith("?"));

//      process question
        AnswererOfficer officer = new AnswererOfficer();
        officer.addAnswerer(new HowMuchIs(aliases, entities, converter));//add How Much Question
        officer.addAnswerer(new HowManyCredits(aliases, entities, converter));//add How Many Question
        while(true){
            System.out.println(officer.askQuestion(line));

            if(lines.isEmpty())
                break;
            line = lines.remove(0);
        }
    }
}
