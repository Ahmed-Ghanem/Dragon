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
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import netWork.LocalIp;
import ui.chat.DragonChat;
import utils.ConstantManager;

public class Client extends ClientServer implements Runnable {
    
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
                chatArea.append(recieveData(inStream));
                // send(new Scanner(System.in).nextLine());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
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
        send(message, outStream);
        chatArea.append(message);
        chatField.setText("");
    }
}
