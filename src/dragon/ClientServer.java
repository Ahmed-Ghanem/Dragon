package dragon;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Ahmed Ghanem
 */
public abstract class ClientServer implements ClientServerView {

    public String recieveData(ObjectInputStream inStream) {
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

    public void send(String data, ObjectOutputStream outStream) {
        try {
            outStream.writeObject(data);
            outStream.flush();
            outStream.reset();
        } catch (IOException ex) {
        }
    }
}
