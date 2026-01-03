package command;

import calculator.CalculatorEngine;

public class DecimalCommand implements Command {
    private CalculatorEngine engine;
    
    public DecimalCommand(CalculatorEngine engine) {
        this.engine = engine;
    }
    
    @Override
    public void execute() {
        engine.appendDecimal();
    }
}
