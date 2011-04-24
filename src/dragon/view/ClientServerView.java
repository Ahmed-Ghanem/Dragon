package dragon.view;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Ahmed Ghanem
 */
public interface ClientServerView {
    public String recieveData(ObjectInputStream inStream);
    public void send(String data, ObjectOutputStream outStream);
}
