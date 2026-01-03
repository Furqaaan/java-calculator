package command;

import calculator.CalculatorEngine;

public class EqualsCommand implements Command {
    private CalculatorEngine engine;

    public EqualsCommand(CalculatorEngine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.calculate();
    }
}
