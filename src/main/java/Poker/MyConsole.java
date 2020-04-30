package Poker;

import ConsoleGUI.JConsole;
import Util.Constants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MyConsole extends JConsole {

    public MyConsole(){

        super(Constants.MAXCURSORPOS_C,Constants.MAXCURSORPOS_R);

        this.setVisible(true);

    }

}
