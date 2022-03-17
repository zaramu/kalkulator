package no.oving5.kalkulator5backend.model;

public class Expression {
    private long id;
    private String mathExpression;
    private double answer;
    private long userId;

    public Expression(){

    }

    public Expression(long id, String mathExpression, double answer, long userId){
        this.id = id;
        this.mathExpression = mathExpression;
        this.answer = answer;
        this.userId = userId;
    }

    public Expression(String mathExpression, double answer, long userId){
        this.mathExpression = mathExpression;
        this.answer = answer;
        this.userId = userId;
    }


    public long getId() {
        return id;
    }

    public String getMathExpression() {
        return mathExpression;
    }

    public double getAnswer() {
        return answer;
    }

    public long getUserId() {
        return userId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMathExpression(String mathExpression) {
        this.mathExpression = mathExpression;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "id=" + id +
                ", mathExpression='" + mathExpression + '\'' +
                ", answer=" + answer +
                ", userId=" + userId +
                '}';
    }
}
