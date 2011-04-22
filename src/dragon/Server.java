/*
 * Author Mohammed Allaw
 * 
 * */
package Dragon;

import dragon.listener.DataListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import netWork.LocalIp;
import ui.chat.DragonChat;
import utils.ConstantManager;

public class Server extends Thread {

    private int remotePort;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private ServerSocket server;
    private Socket client;
    private DataListener dListener;
    private DragonChat dChat; //appear chat frame
    private LocalIp hostName; //chat user name
    private String userName;
    private JTextArea chatArea;
    private JTextField chatField;
    private JButton sendButton;

    public Server(DataListener dListener) {

        this(ConstantManager.REMOTE_PORT, dListener);
    }

    public Server(int remotePort, DataListener dListener) {
        this.remotePort = remotePort;
        this.dListener = dListener;

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
                chatArea.append(recieveData());
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String recieveData() {
        String msg = null;
        try {
            msg = (String) inStream.readObject();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return msg;
    }

    public void send(String data) {
        try {
            outStream.writeObject(data);
            outStream.flush();
            outStream.reset();
        } catch (IOException ex) {
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
        /*chatField.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    messageData();
                }
            }
        });*/
        sendButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                messageData();
            }
        });

    }

    public void messageData() {
        String message = null;
        message = userName + ": " + chatField.getText() + "\n";
        send(message);
        chatArea.append(message);
        chatField.setText("");
    }
}
