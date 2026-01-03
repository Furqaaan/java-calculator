package command;

import calculator.CalculatorEngine;

public class AddCommand implements Command {
    private CalculatorEngine engine;

    public AddCommand(CalculatorEngine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.add();
    }
}
