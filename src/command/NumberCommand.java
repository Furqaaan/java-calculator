package command;

import calculator.CalculatorEngine;

public class NumberCommand implements Command {
    private CalculatorEngine engine;
    private String digit;
    
    public NumberCommand(CalculatorEngine engine, String digit) {
        this.engine = engine;
        this.digit = digit;
    }
    
    @Override
    public void execute() {
        engine.appendNumber(digit);
    }
}
