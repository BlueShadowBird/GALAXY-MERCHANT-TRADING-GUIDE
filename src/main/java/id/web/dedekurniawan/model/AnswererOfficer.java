package id.web.dedekurniawan.model;

import id.web.dedekurniawan.exception.InformationException;

import java.util.ArrayList;
import java.util.List;


public class AnswererOfficer {
    List<AnswererAbstract> answererList;
    private static String NO_ANSWER = "I have no idea what you are talking about";

    public AnswererOfficer() {
        answererList = new ArrayList<AnswererAbstract>();
    }

    public AnswererOfficer(List<AnswererAbstract> answererList) {
        answererList = answererList;
    }

    public void setAnswererList(List<AnswererAbstract> answererList) {
        answererList = answererList;
    }

    public void addAnswerer(AnswererAbstract answerer){
        answererList.add(answerer);
    }

    public String askQuestion(String question) throws InformationException {
        for (AnswererAbstract answerer:answererList) {
            if(answerer.askIfUnderstand(question))
                return answerer.askForAnswer();
        }
        return NO_ANSWER;
    }
}
