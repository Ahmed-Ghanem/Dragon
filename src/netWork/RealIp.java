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

/**
 *
 * @author Ahmed Ghanem
 */
public class RealIp implements Ip {

    private String realIp;

    public RealIp() {
        try {
            URL url = new java.net.URL("http://whatismyip.com/automation/n09230945.asp");
            HttpURLConnection Conn = (HttpURLConnection) url.openConnection();
            InputStream InStream = Conn.getInputStream();
            InputStreamReader Isr = new InputStreamReader(InStream);
            BufferedReader br = new BufferedReader(Isr);
            realIp = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(RealIp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getIp() {
        return realIp;
    }
}
