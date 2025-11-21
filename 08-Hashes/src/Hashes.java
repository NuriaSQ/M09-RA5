import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    int npass = 0;

    public String getSHA512AmbSalt(String pw, String salt){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());
            byte[] hashBytes = md.digest(pw.getBytes());
            return HexFormat.of().formatHex(hashBytes);
        } catch (Exception e) {
            return null;  
        }
    }

    public String getPBKDF2AmbSalt(String pw, String salt){
        try {
            PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(),salt.getBytes(StandardCharsets.UTF_8),1000,128);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hashBytes = skf.generateSecret(spec).getEncoded();
            return HexFormat.of().formatHex(hashBytes);
        } catch (Exception e) {
            return null;
        }
    }

    public String forcaBruta(String alg, String hashOriginal, String salt) {
        String chars = "abcdefABCDEF1234567890!";
        char[] charset = chars.toCharArray();
        npass = 0;
        char[] pass = new char[6];

        for (int i0 = 0; i0 < charset.length; i0++) {
            pass[0] = charset[i0];
            npass++;

            String intent = new String(pass, 0, 1);
            String h;
            if (alg.equals("SHA-512"))
                h = getSHA512AmbSalt(intent, salt);
            else
                h = getPBKDF2AmbSalt(intent, salt);

            if (hashOriginal.equals(h))
                return intent;

            for (int i1 = 0; i1 < charset.length; i1++) {
                pass[1] = charset[i1];
                npass++;

                intent = new String(pass, 0, 2);
                if (alg.equals("SHA-512"))
                    h = getSHA512AmbSalt(intent, salt);
                else
                    h = getPBKDF2AmbSalt(intent, salt);

                if (hashOriginal.equals(h))
                    return intent;

                for (int i2 = 0; i2 < charset.length; i2++) {
                    pass[2] = charset[i2];
                    npass++;

                    intent = new String(pass, 0, 3);
                    if (alg.equals("SHA-512"))
                        h = getSHA512AmbSalt(intent, salt);
                    else
                        h = getPBKDF2AmbSalt(intent, salt);

                    if (hashOriginal.equals(h))
                        return intent;

                    for (int i3 = 0; i3 < charset.length; i3++) {
                        pass[3] = charset[i3];
                        npass++;

                        intent = new String(pass, 0, 4);
                        if (alg.equals("SHA-512"))
                            h = getSHA512AmbSalt(intent, salt);
                        else
                            h = getPBKDF2AmbSalt(intent, salt);

                        if (hashOriginal.equals(h))
                            return intent;

                        for (int i4 = 0; i4 < charset.length; i4++) {
                            pass[4] = charset[i4];
                            npass++;

                            intent = new String(pass, 0, 5);
                            if (alg.equals("SHA-512"))
                                h = getSHA512AmbSalt(intent, salt);
                            else
                                h = getPBKDF2AmbSalt(intent, salt);

                            if (hashOriginal.equals(h))
                                return intent;

                            for (int i5 = 0; i5 < charset.length; i5++) {
                                pass[5] = charset[i5];
                                npass++;

                                intent = new String(pass, 0, 6);
                                if (alg.equals("SHA-512"))
                                    h = getSHA512AmbSalt(intent, salt);
                                else
                                    h = getPBKDF2AmbSalt(intent, salt);

                                if (hashOriginal.equals(h))
                                    return intent;
                            }
                        }
                    }
                }
            }
        }

        return null;
    }


    public String getInterval(long t1, long t2){
    long ms = t2 - t1;

    long dies = ms / (24L * 60 * 60 * 1000);
    ms %= 24L * 60 * 60 * 1000;

    long hores = ms / (60 * 60 * 1000);
    ms %= 60 * 60 * 1000;

    long minuts = ms / (60 * 1000);
    ms %= 60 * 1000;

    long segons = ms / 1000;
    ms %= 1000;

    return String.format("%d dies / %d hores / %d minuts/ %d segons / %d millis", dies, hores, minuts, segons, ms);
}


    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = {
            h.getSHA512AmbSalt(pw, salt),
            h.getPBKDF2AmbSalt(pw, salt)
        };

        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for (int i = 0; i < aHashes.length; i++) {
            System.out.print("=================================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.print("------------------------------\n");
            System.out.print("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.print("-------------------------------\n\n");
        }
    }
}
