import javax.swing.*;

public class Terminal {
    private JTextField textField1;
    private JLabel cs;
    private JPanel pan;
    private JTextPane textPane1;

    public static void main(String[] args) {
        JFrame frame=new JFrame();
        Terminal t= new Terminal();
        frame.setContentPane(t.pan);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }


}
