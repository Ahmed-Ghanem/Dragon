/*
 * Author Mohammed Allaw
 * 
 * */
package Dragon;

import dragon.ClientServer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import netWork.LocalIp;
import ui.chat.DragonChat;
import utils.ConstantManager;

public class Client extends Thread implements ClientServer {

    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private Socket client;
    private String IP;
    private int remotePort;
    private DragonChat dChat;
    private LocalIp hostName; //chat user name
    private String userName;
    private JTextArea chatArea;
    private JTextField chatField;
    private JButton sendButton;

    public Client(String IP) {
        this(IP, ConstantManager.REMOTE_PORT);

    }

    public Client(String IP, int remotePort) {

        this.IP = IP;
        this.remotePort = remotePort;

    }

    @Override
    public void run() {
        establishConnection();
    }

    private void establishConnection() {


        try {
            client = new Socket(IP, remotePort);
            outStream = new ObjectOutputStream(client.getOutputStream());
            inStream = new ObjectInputStream(client.getInputStream());
            hostName = new LocalIp();
            userName = hostName.getHostName();
            chatInit();
            while (true) {
                chatArea.append(recieveData());
                // send(new Scanner(System.in).nextLine());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String recieveData() {
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
            client.close();
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

    private void messageData() {
        String message = null;
        message = userName + ": " + chatField.getText() + "\n";
        send(message);
        chatArea.append(message);
        chatField.setText("");
    }
}
