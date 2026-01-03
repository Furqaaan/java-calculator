package command;

import calculator.CalculatorEngine;

public class SqrtCommand implements Command {
    private CalculatorEngine engine;

    public SqrtCommand(CalculatorEngine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.sqrt();
    }
}
