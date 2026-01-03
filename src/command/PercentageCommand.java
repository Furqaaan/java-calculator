package command;

import calculator.CalculatorEngine;

public class PercentageCommand implements Command {
    private CalculatorEngine engine;
    
    public PercentageCommand(CalculatorEngine engine) {
        this.engine = engine;
    }
    
    @Override
    public void execute() {
        engine.percentage();
    }
}
