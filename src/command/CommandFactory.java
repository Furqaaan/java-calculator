package command;

import calculator.CalculatorDisplay;
import calculator.CalculatorEngine;

public class CommandFactory {
    private CommandFactory() {
        //
    }

    public static Command createCommand(String buttonText, CalculatorDisplay display) {
        CalculatorEngine engine = CalculatorEngine.getInstance(display);

        return switch (buttonText) {
            case "AC" -> new ClearCommand(engine);
            case "±" -> new NegateCommand(engine);
            case "%" -> new PercentageCommand(engine);
            case "=" -> new EqualsCommand(engine);
            case "." -> new DecimalCommand(engine);
            case "+" -> new AddOperatorCommand(engine);
            case "-" -> new SubtractOperatorCommand(engine);
            case "x" -> new MultiplyOperatorCommand(engine);
            case "/" -> new DivideOperatorCommand(engine);
            case "√" -> new SqrtCommand(engine);
            default -> {
                if ("0123456789".contains(buttonText)) {
                    yield new NumberCommand(engine, buttonText);
                }
                
                throw new IllegalArgumentException("Unknown button: " + buttonText);
            }
        };
    }

    public static Command getCommand(String operator, CalculatorEngine engine) {
        return switch (operator) {
            case "+" -> new AddCommand(engine);
            case "-" -> new SubtractCommand(engine);
            case "x" -> new MultiplyCommand(engine);
            case "/" -> new DivideCommand(engine);
            default -> throw new IllegalArgumentException("Invalid operator: " + operator);
        };
    }
}
