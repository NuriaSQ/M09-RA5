package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador{
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] dadesXifrades = xifraAES(msg, clau);
            return new TextXifrat(dadesXifrades);
        } catch (Exception e) {
            System.err.println("Error xifrant: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            return desxifraAES(xifrat.getBytes(), clau);
        } catch (Exception e) {
            System.err.println("Error desxifrant: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }

    public byte[] xifraAES(String msg, String clau) throws Exception {
        //Obtenir els bytes de l'String
        byte[] dades = msg.getBytes(StandardCharsets.UTF_8);

        //Genera IvParameterSpec
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        //Genera hash
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauHash = md.digest(clau.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec sk = new SecretKeySpec(clauHash, ALGORISME_XIFRAT);

        //Encrypt
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, sk, ivSpec);
        byte[] xifrat = cipher.doFinal(dades);

        //Combinar IV i part xifrada
        byte[] resultat = new byte[iv.length + xifrat.length];
        for (int i = 0; i < iv.length; i++) {
            resultat[i] = iv[i];
        }
        for (int i = 0; i < xifrat.length; i++) {
            resultat[iv.length + i] = xifrat[i];
        }

        //return iv+msgxifrat
        return resultat;
    }

    public String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        //Extreure l'IV
        byte[] ivExtret = Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);

        //Extreure la part xifrada
        byte[] xifrat = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);

        //Fer hash de la clau
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauHash = md.digest(clau.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec sk = new SecretKeySpec(clauHash, ALGORISME_XIFRAT);

        //Desxifrar
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, sk, new IvParameterSpec(ivExtret));
        byte[] resultat = cipher.doFinal(xifrat);

        //return String desxifrat
        return new String(resultat, StandardCharsets.UTF_8);
    }
}