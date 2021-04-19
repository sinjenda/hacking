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
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Scanner;

public class Terminal extends Program implements KeyListener {
    private JTextField textField1;
    private JLabel cs;
    private JPanel pan;
    private JTextPane textPane1;
    String currentPath;
    User logged;

    public Terminal(String name, User logged,Computer local) {
        super(name,system,local);
        this.logged=logged;
    }
    @SuppressWarnings("CopyConstructorMissesField")
    public Terminal(Terminal terminal){
        this("", terminal.logged, terminal.c);

    }

    public ProgressBar createProgressBar(String task){
        return new ProgressBar(task, 100, 1000, getWriter(), ProgressBarStyle.COLORFUL_UNICODE_BLOCK, "", 1L, false, null, ChronoUnit.SECONDS, 0L, Duration.ZERO);
    }

    public Console getWriter() {
        return Console.newInstance(textPane1);
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
        cs.setText(logged.getName()+"@kali");
        textField1.addKeyListener(this);
        currentPath="/home/"+logged.getName()+"/";
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
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            String comm=textField1.getText();
            Scanner scnr=new Scanner(comm);
            String name=scnr.next();
            Objects.requireNonNull(scanForFile(name)).exec(new String[]{currentPath,comm.replaceFirst(name+" ","")},this);

        }
    }
    private @Nullable Program scanForFile(String name) {
        try {
            return (Program) c.getRoot().getFile("/bin/"+name);

        } catch (IOException e) {
            try {
                return (Program) c.getRoot().getFile("/usr/bin/"+name);
            }
            catch (IOException e1){
                getWriter().println(e1.getMessage());
                return null;
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static class Console extends PrintStream {
        int i = 0;
        ConsoleWriter writer;


        private Console(ConsoleWriter stream) {
            super(stream);
            writer = stream;
        }


        @Override
        public final void println(String s) {
            super.println();
            if (i == 16) {
                String[] root = writer.area.getText().split(System.lineSeparator());
                i = 0;
                writer.area.setText("");
                for (int i1 = 1; i1 != root.length; i1++) {
                    println(root[i1]);
                }
            } else
                i++;
        }

        public static Console newInstance(JTextPane area) {
            return new Console(new ConsoleWriter(area));
        }

    }

    private static class ConsoleWriter extends OutputStream {
        JTextPane area;


        @Override
        public void write(int b) {

            append(String.valueOf((char) b));
        }

        public ConsoleWriter(JTextPane a) {
            area = a;
        }

        private void append(String msg) {
            StyleContext sc = StyleContext.getDefaultStyleContext();
            AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.GREEN);

            aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
            aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

            int len = area.getDocument().getLength();
            area.setCaretPosition(len);
            area.setCharacterAttributes(aset, false);
            area.setEditable(true);
            area.replaceSelection(msg);
            area.setEditable(false);
        }

    }





}
