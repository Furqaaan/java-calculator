package calculator;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.util.Arrays;
import javax.swing.border.LineBorder;
import command.Command;
import command.CommandFactory;

public class CalculatorDisplay {
    private static final int BOARD_WIDTH = 360;
    private static final int BOARD_HEIGHT = 540;

    private static final Color LIGHT_GRAY = new Color(212, 212, 210);
    private static final Color DARK_GRAY = new Color(80, 80, 80);
    private static final Color BLACK = new Color(28, 28, 28);
    private static final Color ORANGE = new Color(255, 149, 0);

    private static final String[] BUTTONS = {
            "AC", "±", "%", "/",
            "7", "8", "9", "x",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "√", "="
    };

    private static final String[] TOP_SYMBOLS = { "AC", "±", "%" };
    private static final String[] RIGHT_SYMBOLS = { "/", "x", "-", "+", "=" };

    private JFrame frame;
    private JLabel displayLabel;
    private JPanel displayPanel;
    private JPanel buttonsPanel;
    private CalculatorContext context;

    public CalculatorDisplay(CalculatorContext context) {
        setContext(context);
        initializeComponents();
    }

    public void setContext(CalculatorContext context) {
        this.context = context;
    }

    public CalculatorContext getContext() {
        return context;
    }

    private void initializeComponents() {
        frame = new JFrame("Calculator");
        displayLabel = new JLabel();
        displayPanel = new JPanel();
        buttonsPanel = new JPanel();
    }

    public void render() {
        setupFrame();
        renderPanels();
        frame.setVisible(true);
    }

    private void setupFrame() {
        frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    }

    private void renderPanels() {
        setupDisplayPanel();
        renderButtons();
    }

    private void setupDisplayPanel() {
        displayLabel.setBackground(BLACK);
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);
    }

    private void renderButtons() {
        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(BLACK);
        frame.add(buttonsPanel, BorderLayout.CENTER);

        for (String buttonText : BUTTONS) {
            renderButton(buttonText);
        }
    }

    private void renderButton(String text) {
        JButton button = new JButton();
        button.setText(text);
        button.setFont(new Font("Arial", Font.PLAIN, 30));
        button.setFocusable(false);
        button.setBorder(new LineBorder(Color.BLACK));

        applyButtonStyle(button, text);
        button.addActionListener(e -> handleButtonClick(button));

        buttonsPanel.add(button);
    }

    private void applyButtonStyle(JButton button, String text) {
        if (Arrays.asList(TOP_SYMBOLS).contains(text)) {
            button.setBackground(LIGHT_GRAY);
            button.setForeground(BLACK);
        } else if (Arrays.asList(RIGHT_SYMBOLS).contains(text)) {
            button.setBackground(ORANGE);
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(DARK_GRAY);
            button.setForeground(Color.WHITE);
        }
    }

    public JLabel getDisplayLabel() {
        return displayLabel;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setDisplayText(String text) {
        displayLabel.setText(text);
    }

    public void setDisplayText(double value) {
        displayLabel.setText(formatNumber(value));
    }

    public String getDisplayText() {
        return displayLabel.getText();
    }

    public double getDisplayValue() {
        return parseDisplayValue(displayLabel.getText());
    }

    public void clearOperatorAndSetDisplayText(double value) {
        context.clearOperator();
        setDisplayText(value);
    }

    private String formatNumber(double value) {
        if (value % 1 == 0) {
            return Integer.toString((int) value);
        } else {
            return Double.toString(value);
        }
    }

    private double parseDisplayValue(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private void handleButtonClick(JButton button) {
        String text = button.getText();
        Command command = CommandFactory.createCommand(text, this);
        command.execute();
    }
}
