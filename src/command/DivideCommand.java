package command;

import calculator.CalculatorEngine;

public class DivideCommand implements Command {
    private CalculatorEngine engine;
    
    public DivideCommand(CalculatorEngine engine) {
        this.engine = engine;
    }
    
    @Override
    public void execute() {
        engine.divide();
    }
}
