package command;

import calculator.CalculatorEngine;

public class AddOperatorCommand implements Command {
    private CalculatorEngine engine;

    public AddOperatorCommand(CalculatorEngine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.stageOperator("+");
    }
}
