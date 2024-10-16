import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "ClauSecretaAES05@黒揚羽";

    public static byte[] xifraAES(String msg, String password) throws Exception {
        // Obté els bytes del String msg
        byte[] msgBytes = msg.getBytes();

        // Genera IVParameterSpec
        SecureRandom secureRandom = new secureRandom();
        secureRandom.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Genera hash
        SecretKey hash = new SecretKeySpec(password.getBytes(), ALGORISME_HASH);

        // Encripta
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, hash);
        byte[] encryptedPart = cipher.doFinal(msgBytes, hash, ivParameterSpec);

        // Combina IV amb part xifrada
        byte[] combination = new byte[encryptedPart.length + MIDA_IV];
        System.arraycopy(iv, 0, combination, 0, MIDA_IV);
        System.arraycopy(encryptedPart, 0, combination, MIDA_IV, encryptedPart.length);

        // Retorna iv + missatge xifrat
        return combination;
    }

    public static String desxifraAES(byte[] bMsgXifrat, String password) throws Exception {
        // Extreu IV

        // Extreu la part xifrada

        // Genera hash de la clau

        // Desencripta

        // Retorna String desxifrat
    }

    public static void main(String[] args) {
        String msgs[] = { "Lorem ipsum dicet",
                "Hola Andrés cómo está tu cuñado",
                "Àgora ïlla Ôtto" };
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];
            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);

            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                        + e.getLocalizedMessage());

            }
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }

}
