package calculator;

public class CalculatorContext {
    private String A;
    private String B;
    private String operator;

    public CalculatorContext(String A, String B, String operator) {
        this.A = A;
        this.B = B;
        this.operator = operator;
    }

    public double getA() {
        return Double.parseDouble(A);
    }

    public void setA(String A) {
        this.A = A;
    }

    public double getB() {
        return Double.parseDouble(B);
    }

    public void setB(String B) {
        this.B = B;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void clearOperator() {
        this.operator = null;
    }
}
