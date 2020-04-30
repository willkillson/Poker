package Poker;

import ConsoleGUI.JConsole;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MyConsole extends JComponent {

    int MAXCURSORPOS_R = 40;
    int MAXCURSORPOS_C = 100;

    int cursor_r = 0;
    int cursor_c = 0;

    public JConsole jc;

    public Container contentPane;

    public MyConsole(){

        JTextField textField = new JTextField();

        jc = new JConsole(MAXCURSORPOS_C,MAXCURSORPOS_R);
        jc.setCursorVisible(true);
        jc.setCursorBlink(true);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

                System.out.println("hellworld");
                jc.setCursorPos(cursor_c, cursor_r);
                jc.write(""+keyEvent.getKeyChar(), Color.GREEN,Color.BLACK);
                cursor_c++;
                if(cursor_c==MAXCURSORPOS_C){
                    cursor_c=0;
                    cursor_r++;
                }
                refresh();

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });

        this.setVisible(true);

    }

    public void refresh(){
        jc.repaint();
    }
}
