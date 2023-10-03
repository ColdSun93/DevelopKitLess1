package messenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerMessage extends JFrame {
    private static final int WINDOW_HEIGHT = 200;
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_POSX = 300;
    private static final int WINDOW_POSY = 250;
    static boolean isServerWorking = false;
    private final JButton btnStart = new JButton("Start server");
    private final JButton btnStop = new JButton("Stop server");
    protected final JTextArea log = new JTextArea("Server is stopped");

    public ServerMessage() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setAlwaysOnTop(true);
        setTitle("Server");
        setResizable(false);

        add(log, BorderLayout.SOUTH);
        JPanel jPanel = new JPanel(new GridLayout(1,2));
        jPanel.add(btnStart);
        jPanel.add(btnStop);
        add(jPanel);

        log.setEditable(false);


        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    log.setText("Server is already running.");
                } else {
                    log.setText("Server is started.");;
                }
                isServerWorking = true;
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isServerWorking) {
                    log.setText("Server is already stopped.");
                } else {
                    log.setText("Server stopped.");
                }
                isServerWorking = false;
            }
        });

        setVisible(true);

    }


}
