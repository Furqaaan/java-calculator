package calculator;

public class Calculator {
    private CalculatorContext context;
    private CalculatorDisplay display;

    public Calculator() {
        context = new CalculatorContext("0", null, null);
        display = new CalculatorDisplay(context);
    }

    public void run() {
        display.render();
    }
}
