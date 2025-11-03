import java.awt.*;
import javax.swing.*;

public class JavaCalculatorGUI {

    private JFrame frame;
    private JTextField display;
    private int num1 = 0, num2 = 0, result = 0;
    private char operator;

    public JavaCalculatorGUI() {

        // THE FRAME
        frame = new JFrame("My First Calculator!");
        frame.setSize(350, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // CALCULATOR DISPLAY FIELD
        display = new JTextField("");
        display.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        frame.add(display, BorderLayout.NORTH);

        // BUTTON PANEL STYLES
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));
        panel.setBackground(Color.BLACK);

        // STRING NUMBERS AND OPERATORS
        String[] buttons = {
            "AC", "+/-", "%", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", "=", "" 
        };
        
        for (String text : buttons) {
            if (text.equals(""))
            continue; 

            // JBUTTON STYLES
            JButton button = new JButton(text);
            button.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
            button.setFocusPainted(false);
            button.setOpaque(true);
            button.setBorderPainted(false);

            // ASSIGN COLORS BASED ON BUTTON TYPE
            if (text.equals("=")) {
                button.setBackground(new Color(0, 120, 255));
                button.setForeground(Color.WHITE);
            } else if (text.equals("/") || text.equals("*") || text.equals("-") || text.equals("+")) {
                button.setBackground(new Color(255, 105, 180));
                button.setForeground(Color.WHITE);
            } else if (text.equals("AC") || text.equals("+/-") || text.equals("%")) {
                button.setBackground(new Color(230, 230, 230)); 
                button.setForeground(Color.BLACK);
            } else {
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
            }

            // ACTION LISTENER FOR BUTTONS FUNCTIONS
            button.addActionListener(e -> {
                String cmd = e.getActionCommand();

                if (cmd.matches("[0-9]")) { 
                    if (display.getText().equals("0") || display.getText().equals(String.valueOf(result))) {
                        display.setText(cmd);

                    } else {
                        display.setText(display.getText() + cmd);
                    }

                } else if (cmd.equals("AC")) {
                    display.setText("0");
                    num1 = num2 = result = 0;

                } else if (cmd.equals("+/-")) {
                    int val = Integer.parseInt(display.getText());
                    val = -val;
                    display.setText(String.valueOf(val));

                } else if (cmd.equals("%")) {
                    int val = Integer.parseInt(display.getText()) % 100;
                    display.setText(String.valueOf(val));

                } else if (cmd.equals("=")) {
                    num2 = Integer.parseInt(display.getText());
                    switch (operator) {

                        case '+': result = num1 + num2; break;
                        case '-': result = num1 - num2; break;
                        case '*': result = num1 * num2; break;
                        case '/':

                            if (num2 != 0)
                                result = num1 / num2;
                            else {
                                display.setText("Error");
                                return;
                            }
                            break;
                    }
                    display.setText(String.valueOf(result));
                } else { // operator buttons
                    operator = cmd.charAt(0);
                    num1 = Integer.parseInt(display.getText());
                    display.setText("0");
                }
            });

            panel.add(button);
        }
        // FRAME VISIBILITY
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    // MAIN METHOD OF THE PROGRAM
    public static void main(String[] args) {
        new JavaCalculatorGUI();
    }
}
