package image;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import utils.ConstantManager;

/**
 *
 * @author Ahmed Ghanem
 */
public class ImageCapture {

    private BufferedImage buffImage;

    public ImageCapture() {
    }

    public ImageIcon createScreenImage() {
        Robot robot = null;
        File temp = null;
        ImageIcon imgIcon = null;
        try {
            robot = new Robot();
            buffImage = robot.createScreenCapture(
                    new Rectangle((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                    (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
            temp = new File(ConstantManager.IMAGE_TEMP_STRING);
            ImageIO.write(buffImage, "jpg", temp);
            imgIcon = new ImageIcon(ConstantManager.IMAGE_TEMP_STRING);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (AWTException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return imgIcon;
    }
}
