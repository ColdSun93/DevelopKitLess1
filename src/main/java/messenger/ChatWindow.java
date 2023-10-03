package messenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ChatWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_POSX = 500;
    private static final int WINDOW_POSY = 250;


    JButton btnConnect = new JButton("Connect");
    JButton btnSend = new JButton("Send");
    JTextField loginField = new JTextField("login: ");
    JTextField passwordField = new JTextField("password: ");
    JTextArea messageField = new JTextArea();
    JTextField textSendField = new JTextField();
    JPanel jPanelTop = new JPanel(new GridLayout(2, 3));
    JPanel jPanelBottom = new JPanel(new BorderLayout());


    //region constructors
    public ChatWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setAlwaysOnTop(true);
        setTitle("Client");
        setResizable(false);

        jPanelTop.add(loginField);
        jPanelTop.add(passwordField);
        jPanelTop.add(btnConnect);
        add(jPanelTop, BorderLayout.NORTH);

        add(messageField);


        jPanelBottom.add(textSendField, BorderLayout.CENTER);
        jPanelBottom.add(btnSend, BorderLayout.EAST);
        add(jPanelBottom, BorderLayout.SOUTH);


        messageField.setEditable(false);



        textSendField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    btnSend.doClick();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


        setVisible(true);
    }


}
