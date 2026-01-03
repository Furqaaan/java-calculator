package command;

import calculator.CalculatorEngine;

public class DivideOperatorCommand implements Command {
    private CalculatorEngine engine;

    public DivideOperatorCommand(CalculatorEngine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.stageOperator("/");
    }
}
