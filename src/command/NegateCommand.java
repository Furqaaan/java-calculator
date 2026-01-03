package command;

import calculator.CalculatorEngine;

public class NegateCommand implements Command {
    private CalculatorEngine engine;
    
    public NegateCommand(CalculatorEngine engine) {
        this.engine = engine;
    }
    
    @Override
    public void execute() {
        engine.negate();
    }
}
