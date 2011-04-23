/*
 * Author Mohammed Allaw
 * 
 * */
package Dragon;

import dragon.chat.ClientServer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import netWork.LocalIp;
import ui.chat.DragonChat;
import utils.ConstantManager;

public class Server extends ClientServer implements Runnable  {

    private int remotePort;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private ServerSocket server;
    private Socket client;
    private DragonChat dChat; //appear chat frame
    private LocalIp hostName; //chat user name
    private String userName;
    private JTextArea chatArea;
    private JTextField chatField;
    private JButton sendButton;

    public Server() {
        this(ConstantManager.REMOTE_PORT);
    }

    public Server(int remotePort) {
        this.remotePort = remotePort;

    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(remotePort);
            client = server.accept();
            inStream = new ObjectInputStream(client.getInputStream());
            outStream = new ObjectOutputStream(client.getOutputStream());
            hostName = new LocalIp();
            userName = hostName.getHostName();
            chatInit();

            while (true) {
                chatArea.append(recieveData(inStream));
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObjectInputStream getInputStream() {
        return inStream;
    }

    public ObjectOutputStream getOutputStream() {
        return outStream;
    }

    public void closeConnection() {
        try {
            outStream.close();
            inStream.close();
            server.close();
        } catch (IOException ex) {

            ex.printStackTrace();

        }
    }

    public void chatInit() {
        dChat = new DragonChat();
        dChat.show();
        initRefComponents(dChat.getChatArea(), dChat.getChatField(), dChat.getSendButton());
    }

    public void initRefComponents(JTextArea a, JTextField f, JButton b) {
        this.chatArea = a;
        this.chatField = f;
        this.sendButton = b;
        sendButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                messageData();
            }
        });
    }
    public void messageData() {
        String message = null;
        message = userName + ": " + chatField.getText() + "\n";
        send(message, outStream);
        chatArea.append(message);
        chatField.setText("");
    }
}
