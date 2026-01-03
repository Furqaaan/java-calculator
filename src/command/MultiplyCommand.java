package command;

import calculator.CalculatorEngine;

public class MultiplyCommand implements Command {
    private CalculatorEngine engine;
    
    public MultiplyCommand(CalculatorEngine engine) {
        this.engine = engine;
    }
    
    @Override
    public void execute() {
        engine.multiply();
    }
}
