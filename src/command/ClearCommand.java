package command;

import calculator.CalculatorEngine;

public class ClearCommand implements Command {
    private CalculatorEngine engine;

    public ClearCommand(CalculatorEngine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.clear();
    }
}
