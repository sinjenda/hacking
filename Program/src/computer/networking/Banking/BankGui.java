package computer.networking.Banking;

import computer.Computer;
import computer.program.Program;

import javax.swing.*;

public class BankGui{
    private JTextField usernameTextField;
    private JPasswordField passwordPasswordField;
    private JButton loginButton;
    private JTextField usernameTextField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton registerButton;
    private JPanel panel;

    public BankGui() {
        JFrame frame = new JFrame();
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }


}
