package command;

import calculator.CalculatorEngine;

public class SubtractOperatorCommand implements Command {
    private CalculatorEngine engine;

    public SubtractOperatorCommand(CalculatorEngine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.stageOperator("-");
    }
}
