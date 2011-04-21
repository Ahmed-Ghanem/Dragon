package utils;

/**
 *
 * @author Ahmed ghanem
 */
import java.util.Random;

public class RandomPassword {

    private String randomPassword;

    public RandomPassword(int passwordLength) {
        Random randomNumber = new Random();
        char password[] = new char[15];
        for (int i = 0; i < 15; i++) {
            int value = randomNumber.nextInt(200);
            password[i] = (char) value;
        }
        randomPassword = new String(password);
    }

    public String getPassword() {
        return randomPassword;
    }
}
