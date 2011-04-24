package tasks;

import javax.swing.JLabel;
import javax.swing.JTextField;
import netWork.IpFactory;
import security.RandomPassword;

/**
 * run this thread to get real or local IP during UI processing
 * @author Ahmed Ghanem
 */
public class IdPasswordInitialTask implements Runnable {

    private JTextField id;
    private JTextField password;
    private JLabel waitingStatus;
    private IpFactory ip;
    private RandomPassword randomPassword;
    // use chat references <components>
    public void componentsRef(JTextField f1, JTextField f2, JLabel l) {
        this.id = f1;
        this.password = f2;
        this.waitingStatus = l;
    }

    public void run() {
        initObj();
        // get IP according to Ip interface
        id.setText(ip.getSuitableIp().getIp());
        //generate a rendom password
        password.setText(randomPassword.getPassword());

    }

    public void initObj() {
        ip = new IpFactory();
        randomPassword = new RandomPassword(15);
        waitingStatus.setText("Ready to connect ...");
        waitingStatus.setIcon(null);
    }
}
