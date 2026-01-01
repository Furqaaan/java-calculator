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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class Calculator {
    int boardWidth = 360;
    int boardHeight = 540;

    Color lightGray = new Color(212, 212, 210);
    Color darkGray = new Color(80, 80, 80);
    Color black = new Color(28, 28, 28);
    Color orange = new Color(255, 149, 0);

    String[] buttons = {
            "AC", "±", "%", "/",
            "7", "8", "9", "x",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "√", "="
    };

    String[] topSymbols = {
            "AC", "±", "%"
    };

    String[] rightSymbols = {
            "/", "x", "-", "+", "="
    };

    JFrame frame = new JFrame("Calculator");

    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();

    JPanel buttonsPanel = new JPanel();

    String A = "0";
    String B = null;
    String operator = null;

    void run() {
        renderFrame();
    }

    void renderFrame() {
        frame.setSize(boardWidth, boardHeight);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        renderPanels();

        frame.setVisible(true);
    }

    void renderPanels() {
        displayLabel.setBackground(black);
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);

        renderButtons();
    }

    void renderButtons() {
        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(black);
        frame.add(buttonsPanel, BorderLayout.CENTER);

        for (String button : buttons) {
            renderButton(button);
        }
    }

    void renderButton(String text) {
        JButton button = new JButton();
        button.setText(text);
        button.setFont(new Font("Arial", Font.PLAIN, 30));
        button.setFocusable(false);
        button.setBorder(new LineBorder(Color.BLACK));

        if (Arrays.asList(topSymbols).contains(text)) {
            button.setBackground(lightGray);
            button.setForeground(black);
        } else if (Arrays.asList(rightSymbols).contains(text)) {
            button.setBackground(orange);
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(darkGray);
            button.setForeground(Color.WHITE);
        }

        initButtonListeners(button);

        buttonsPanel.add(button);
    }

    void initButtonListeners(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                String text = button.getText();

                System.out.println(text);

                if (Arrays.asList(topSymbols).contains(text)) {
                    if(text.equals("AC")) {
                        clearAll();
                    } else if(text.equals("±")) {
                        double displayNum = Double.parseDouble(displayLabel.getText());
                        displayNum = -displayNum;

                        displayLabel.setText(removeZeroDecimal(displayNum));
                    } else if(text.equals("%")) {
                        double displayNum = Double.parseDouble(displayLabel.getText());
                        displayNum = displayNum / 100;

                        setDisplayText(removeZeroDecimal(displayNum));
                    }
                } else if (Arrays.asList(rightSymbols).contains(text)) {
                    if("+-x/".contains(text)) {
                        if(operator == null) {
                            operator = text;
                            A = displayLabel.getText();
                            B = "0";
                        }

                        operator = text;
                        displayLabel.setText("0");
                    }else if(text.equals("=")) {
                        B = displayLabel.getText();

                        if(A != null) {
                            setDisplayText(calculate(A, B, operator));
                        }
                    }
                } else {
                    if(text.equals(".")) {
                        if(displayLabel.getText().contains(".")) {
                            return;
                        } else {
                            setDisplayText(displayLabel.getText() + text);
                        }
                    } else if("0123456789".contains(text)) {
                        if(displayLabel.getText().equals("0")) {
                            setDisplayText(text);
                        } else {
                            setDisplayText(displayLabel.getText() + text);
                        }
                    }
                }

            }
        });
    }

    void clearAll() {
        A = "0";
        B = null;
        operator = null;
        displayLabel.setText("0");
    }

    String calculate(String A, String B, String operator) {
        double a = Double.parseDouble(A);
        double b = Double.parseDouble(B);
        double result = 0;

        if(operator.equals("+")) {
            result = a + b;
        } else if(operator.equals("-")) {
            result = a - b;
        } else if(operator.equals("x")) {
            result = a * b;
        } else if(operator.equals("/")) {
            result = a / b;
        }

        return removeZeroDecimal(result);
    }

    String removeZeroDecimal(double displayNum) {
        if(displayNum % 1 == 0) {
            return Integer.toString((int) displayNum);
        } else {
            return Double.toString(displayNum);
        }
    }

    void setDisplayText(String text) {
        displayLabel.setText(text);
    }
}