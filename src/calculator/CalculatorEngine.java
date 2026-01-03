package calculator;

import command.Command;
import command.CommandFactory;

public class CalculatorEngine {
    private static CalculatorEngine instance;

    private CalculatorDisplay display;
    private CalculatorContext context;

    private CalculatorEngine(CalculatorDisplay display) {
        this.display = display;
        this.context = display.getContext();
    }

    public static CalculatorEngine getInstance(CalculatorDisplay display) {
        if (instance == null || instance.display != display) {
            instance = new CalculatorEngine(display);
        }
        return instance;
    }

    public CalculatorContext getContext() {
        return display.getContext();
    }

    public CalculatorDisplay getDisplay() {
        return display;
    }

    public void add() {
        double result = context.getA() + context.getB();
        display.clearOperatorAndSetDisplayText(result);
    }

    public void subtract() {
        double result = context.getA() - context.getB();
        display.clearOperatorAndSetDisplayText(result);
    }

    public void multiply() {
        double result = context.getA() * context.getB();
        display.clearOperatorAndSetDisplayText(result);
    }

    public void divide() {
        double result = context.getA() / context.getB();
        display.clearOperatorAndSetDisplayText(result);
    }


    public void stageOperator(String operator) {
        if (context.getOperator() == null) {
            context.setA(display.getDisplayText());
            context.setB("0");
        }

        context.setOperator(operator);
        display.setDisplayText("0");
    }

    public void sqrt() {
        double result = Math.sqrt(display.getDisplayValue());
        display.setDisplayText(result);
    }

    public void negate() {
        double result = display.getDisplayValue();
        display.setDisplayText(-result);
    }

    public void percentage() {
        double result = display.getDisplayValue();
        display.setDisplayText(result / 100);
    }

    public void clear() {
        context.setA("0");
        context.setB(null);
        context.setOperator(null);

        display.setDisplayText("0");
    }

    public void appendNumber(String digit) {
        if (display.getDisplayText().equals("0")) {
            display.setDisplayText(digit);
        } else {
            display.setDisplayText(display.getDisplayText() + digit);
        }
    }

    public void appendDecimal() {
        if (!display.getDisplayText().contains(".")) {
            display.setDisplayText(display.getDisplayText() + ".");
        }
    }

    public void calculate() {
        context.setB(display.getDisplayText());

        if (!Double.isNaN(context.getA())) {
            Command command = CommandFactory.getCommand(context.getOperator(), this);
            command.execute();
        }
    }
}
