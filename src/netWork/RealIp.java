package netWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utils.ConstantManager;

/**
 * real ip
 * @author Ahmed Ghanem
 */
public class RealIp implements Ip {

    private String realIp;
    private boolean realFlag = true;

    public RealIp() {
        try {
            URL url = new java.net.URL(ConstantManager.REAL_IP_SITE);
            HttpURLConnection Conn = (HttpURLConnection) url.openConnection();
            InputStream InStream = Conn.getInputStream();
            InputStreamReader Isr = new InputStreamReader(InStream);
            BufferedReader br = new BufferedReader(Isr);
            realIp = br.readLine();
        } catch (IOException ex) {
            //there is no internet access
            realFlag = false;
        }

    }

    @Override
    public String getIp() {
        return realIp;
    }

    public boolean getFlag() {
        return realFlag;
    }
}
