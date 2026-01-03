package command;

import calculator.CalculatorEngine;

public class MultiplyOperatorCommand implements Command {
    private CalculatorEngine engine;

    public MultiplyOperatorCommand(CalculatorEngine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.stageOperator("x");
    }
}
