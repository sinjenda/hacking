package computer.networking.Banking;

import javax.swing.*;

public class BankLogin {
    private JTextField ucet;
    private JButton potverzani;
    private JSpinner spinner1;
    private JPanel panel;

    public BankLogin() {
        JFrame frame=new JFrame();
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
