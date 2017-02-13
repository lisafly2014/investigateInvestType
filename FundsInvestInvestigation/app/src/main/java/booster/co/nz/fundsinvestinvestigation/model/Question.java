package booster.co.nz.fundsinvestinvestigation.model;

/**
 * Created by ximeiliu on 10/02/17.
 */

public class Question {
    private int id;
    private String question;
    private String optA;
    private String optB;
    private String optC;
    private String optD;
    private String optE;



    public Question(){
        id = 0;
        question ="";
        optA = "";
        optB = "";
        optC = "";
        optD = "";
    }

    public Question(String question, String optA, String optB, String optC, String optD, String optE) {
        this.question = question;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
        this.optD = optD;
        this.optE = optE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptA() {
        return optA;
    }

    public void setOptA(String optA) {
        this.optA = optA;
    }

    public String getOptB() {
        return optB;
    }

    public void setOptB(String optB) {
        this.optB = optB;
    }

    public String getOptC() {
        return optC;
    }

    public void setOptC(String optC) {
        this.optC = optC;
    }

    public String getOptD() {
        return optD;
    }

    public void setOptD(String optD) {
        this.optD = optD;
    }

    public String getOptE() {
        return optE;
    }

    public void setOptE(String optE) {
        this.optE = optE;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", optA='" + optA + '\'' +
                ", optB='" + optB + '\'' +
                ", optC='" + optC + '\'' +
                ", optD='" + optD + '\'' +
                ", optE='" + optE + '\'' +
                '}';
    }
}
