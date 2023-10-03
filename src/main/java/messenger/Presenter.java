package messenger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Presenter {
    private ChatWindow Client;
    private ServerMessage Server;
    private static final String FILENAME = "LOG.TXT";

    public void Presenter(){

        Server = new ServerMessage();
        Client = new ChatWindow();
        Client.btnSend.setEnabled(false);


        /**
         * Кнопка connect
         * */
        Client.btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((Server.isServerWorking) && (Client.loginField.getText().length() > 7)){
                    Client.messageField.setText(String.valueOf(readLogTFromFile()));
                    Client.btnSend.setEnabled(true);
                } else if (!Server.isServerWorking) {
                    throw new RuntimeException("The server is not running. Please start the server.");
                } else if (Client.loginField.getText().length() == 7) {
                    throw new RuntimeException("The the user name is missing");
                } else throw new RuntimeException("Unexpected error");

            }
        });


        /**
         * Ввод сообщения
         * */
        Client.btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + ": " + Client.loginField.getText().substring(7) + ": " + Client.textSendField.getText() + "\n";
                Client.messageField.append(result);
                if (Server.isServerWorking) {
                    writeLogToFile(result);
                }
                else throw new RuntimeException("The server is not running. There is no recording.");
                Client.textSendField.setText("");
            }
        });


    }

    /**
     * Чтение данных из файла
     * */
    private StringBuffer readLogTFromFile() {
        StringBuffer stringBuffer = new StringBuffer();
        try (FileReader reader = new FileReader(FILENAME); BufferedReader buffer = new BufferedReader(reader)) {

            String line = buffer.readLine();
            if (line == null || line.isBlank()) {
                System.out.println("Log is empty.");
                return stringBuffer.append("Log is empty.\n");
            }

            while (line != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
                line = buffer.readLine();
            }

            return stringBuffer;

        } catch (IOException ioe) {
            System.out.println("Log file is not found: " + FILENAME);
        }
        return stringBuffer.append("Log file is not found: " + FILENAME + "\n");
    }

    /**
     * Запись истории чата в файл
     * */
    private void writeLogToFile(String data) {
        try (FileWriter writer = new FileWriter(FILENAME, true); BufferedWriter buffer = new BufferedWriter(writer)) {
            buffer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
