
/**
 *
 * @author BAD SECTOR
 */
import java.lang.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static String encryptip(String ip) {
        String newip = ip.replace(".", " ");

        char[] cArray = new char[50];
        int[] ascii = new int[50];

        for (int i = 0; i < newip.length(); ++i) {

            cArray[i] = newip.charAt(i);
            ascii[i] = ((int) cArray[i]) - (700);
            cArray[i] = ((char) ascii[i]);
        }
        String encip = new String(cArray);
        return encip;


    }

    public static String decryptip(String encip) {
        char[] decArray = new char[50];
        int[] ascii = new int[50];
        char[] cArray = new char[50];
        decArray = encip.toCharArray();
        for (int i = 0; i < encip.length(); ++i) {
            ascii[i] = ((int) decArray[i]) + 700;
            cArray[i] = ((char) ascii[i]);
        }
        String decip = new String(cArray);
        decip = decip.replace(" ", ".");
        return decip;




    }

    public static void main(String[] args) {
        System.out.println("encrypted ip : " + encryptip("292.122.2.0"));
        System.out.println("decrypted ip : " + decryptip("ﵹﵹﵤﵶﵹﵹﵤﵶﵹﵹﵤﵶﵹﵹﵶ"));




    }
}
