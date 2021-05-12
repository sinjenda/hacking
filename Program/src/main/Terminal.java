package main;

import computer.Computer;
import computer.program.Program;
import computer.program.logging.User;
import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarStyle;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Terminal extends Program implements KeyListener, Serializable {
    private JTextField textField1;
    private JLabel cs;
    private JPanel pan;
    private JTextPane textPane1;
    String currentPath;

    public User getLogged() {
        return logged;
    }

    final User logged;

    public Terminal(String name, User logged, Computer local) {
        super(name, system, local,false);
        this.logged = logged;
    }

    private Console newConsole(JTextPane area) {
        return new Console(new ConsoleWriter(area));
    }

    @SuppressWarnings("CopyConstructorMissesField")
    public Terminal(Terminal terminal) {
        this("", terminal.logged, terminal.c);

    }

    public static ProgressBar createProgressBar(String task, Console writer) {
        return new ProgressBar(task, 100, 1000, writer, ProgressBarStyle.COLORFUL_UNICODE_BLOCK, "", 1L, false, null, ChronoUnit.SECONDS, 0L, Duration.ZERO);
    }

    public Console getWriter() {
        return newConsole(textPane1);
    }

    private void start() {
        JFrame frame = new JFrame();
        frame.setContentPane(pan);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
        textPane1.setEditable(false);
        textPane1.setBackground(Color.black);
        pan.setBackground(Color.black);
        cs.setForeground(Color.GREEN);
        cs.setText(logged.getName() + "@kali");
        textField1.addKeyListener(this);
        currentPath = "/home/" + logged.getName() + "/";
    }

    @Override
    public void run() {
        start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String comm = textField1.getText();
            Scanner scnr = new Scanner(comm);
            String name = scnr.next();
            switch (name) {
                case "cd":
                    comm = comm.replaceFirst(name + " ", "");
                    scnr = new Scanner(comm);
                    String param = scnr.next();
                    if (param.startsWith("/")) {
                        currentPath = param;
                    } else {
                        currentPath += param + "/";
                    }
                case "pwd":
                    getWriter().println(currentPath);
                default:
                    Program f = scanForFile(name);
                    assert f != null;
                    if (f.isAllowed(getLogged())) {
                        f.exec(new String[]{currentPath, comm.replaceFirst(name + " ", "")}, this);
                    }
            }
        }
    }

    @SuppressWarnings({"unused", "UnusedAssignment"})
    public static String pathBuilder(String currentPath, String name) {
        if (name.startsWith("/")) {
            return name;
        } else {
            return currentPath += name;
        }
    }

    private @Nullable Program scanForFile(String name) {
        try {
            return (Program) c.getRoot().getFile("/bin/" + name);

        } catch (IOException e) {
            try {
                return (Program) c.getRoot().getFile("/usr/bin/" + name);
            } catch (IOException e1) {
                getWriter().println(e1.getMessage());
                return null;
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static class Console extends PrintStream {
        final ConsoleWriter writer;


        private Console(ConsoleWriter stream) {
            super(stream);
            writer = stream;
        }


    }

    public static class ConsoleWriter extends OutputStream {
        final JTextPane area;


        @Override
        public void write(int b) {
            System.out.println("written to console: " + area);
            append(String.valueOf((char) b));
        }

        public ConsoleWriter(JTextPane a) {
            area = a;
        }

        private void append(String msg) {
            StyleContext sc = StyleContext.getDefaultStyleContext();
            AttributeSet asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.GREEN);

            asset = sc.addAttribute(asset, StyleConstants.FontFamily, "Lucida Console");
            asset = sc.addAttribute(asset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

            int len = area.getDocument().getLength();
            area.setCaretPosition(len);
            area.setCharacterAttributes(asset, false);
            area.setEditable(true);
            area.replaceSelection(msg);
            area.setEditable(false);
        }

    }


}
