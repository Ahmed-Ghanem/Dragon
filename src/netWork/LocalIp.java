/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netWork;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ahmed
 */
public class LocalIp implements Ip {

    private InetAddress address;

    public LocalIp() {
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, "Network problem occurred");
        }
    }

    /***
     * get host name
     * @return host name
     */
    public String getHostName() {
        return address.getCanonicalHostName();
    }

    /***
     * to get ip address
     * @return IP address
     */
    @Override
    public String getIp() {
        return address.getHostAddress();
    }
}
