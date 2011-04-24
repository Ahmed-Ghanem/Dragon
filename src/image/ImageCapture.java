package image;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import utils.ConstantManager;

/**
 * used as imaging center component
 * @author Ahmed Ghanem
 */
public class ImageCapture {

    private BufferedImage buffImage;

    public ImageCapture() {
    }

    /**
     * 
     * @return screen shot as image icon object
     */
    public ImageIcon createScreenImage() {
        Robot robot = null;
        File temp = null;
        ImageIcon imgIcon = null;
        try {
            robot = new Robot();
            buffImage = robot.createScreenCapture(
                    // screen hight and width
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
