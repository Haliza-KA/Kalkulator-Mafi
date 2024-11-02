import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField display;
    private JPanel panel;

    public CalculatorGUI() {
        // Setup frame
        setTitle("Kalkulator Sederhana");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(new Color(230, 230, 230));
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.NORTH);

        // Buttons panel
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        panel.setBackground(new Color(50, 50, 50));

        String[] buttons = {
            "C", "(", ")", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "!", "="
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setFocusPainted(false);
            button.setForeground(Color.WHITE);
            button.setBackground(getButtonColor(text));
            button.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50)));
            button.addActionListener(this);
            panel.add(button);
        }
        add(panel, BorderLayout.CENTER);

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setVisible(true);
    }

    private Color getButtonColor(String text) {
        if (text.equals("C")) {
            return new Color(220, 53, 69);
        } else if (text.equals("=")) {
            return new Color(40, 167, 69);
        } else if ("()+-*/!".contains(text)) {
            return new Color(255, 193, 7);
        } else {
            return new Color(33, 37, 41);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorGUI::new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("C")) {
            display.setText("");
        } else if (command.equals("=")) {
            try {
                String expression = display.getText().trim(); // Menghapus spasi di sekitar
                if (!expression.isEmpty() && isExpressionValid(expression)) {
                    double result = eval(expression);
                    display.setText(String.valueOf(result));
                } else {
                    display.setText("Error");
                }
            } catch (Exception ex) {
                display.setText("Error: " + ex.getMessage()); // Menampilkan pesan error yang lebih spesifik
            }
        } else if (command.equals("!")) {
            try {
                int num = Integer.parseInt(display.getText().trim());
                if (num < 0 || display.getText().contains(".")) throw new IllegalArgumentException();
                display.setText(String.valueOf(factorial(num)));
            } catch (Exception ex) {
                display.setText("Error");
            }
        } else {
            display.setText(display.getText() + command);
        }
    }

    private int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Angka harus non-negatif");
        }
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private double eval(String expression) {
        try {
            Object result = new javax.script.ScriptEngineManager()
                .getEngineByName("JavaScript")
                .eval(expression);
            if (result instanceof Number) {
                return ((Number) result).doubleValue();
            } else {
                throw new RuntimeException("Invalid result type");
            }
        } catch (Exception e) {
            throw new RuntimeException("Ekspresi tidak valid: " + e.getMessage());
        }
    }

    private boolean isExpressionValid(String expression) {
        // Cek apakah ekspresi hanya mengandung karakter yang valid
        if (!expression.matches("[0-9+\\-*/(). !]+")) return false;

        // Cek keseimbangan tanda kurung
        int balance = 0;
        for (char ch : expression.toCharArray()) {
            if (ch == '(') balance++;
            if (ch == ')') balance--;
            if (balance < 0) return false; // Jika ')' lebih banyak dari '('
        }
        return balance == 0; // Pastikan jumlah '(' dan ')' seimbang
    }
}
