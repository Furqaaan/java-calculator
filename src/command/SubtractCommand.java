package command;

import calculator.CalculatorEngine;

public class SubtractCommand implements Command {
    private CalculatorEngine engine;
    
    public SubtractCommand(CalculatorEngine engine) {
        this.engine = engine;
    }
    
    @Override
    public void execute() {
        engine.subtract();
    }
}
