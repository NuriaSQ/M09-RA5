import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {

    public static final char[] ABECEDARI_MAJUSCULA = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();
    public static List<char[]> abecedariPermutat = new ArrayList<>();
    public static Random random;
    public static final String clauSecreta = "1234";

    public static void main(String[] args) {
        String msgs[] = {
            "Test 01 àrbritre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila",};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n---------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("\nDesxifratge:\n---------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

    public static void initRandom(String clauSecreta) {
        random = new Random(clauSecreta.hashCode());
        abecedariPermutat.clear();
    }

    public static void permutaAlfabet() {
        List<Character> llistaCharacters = new ArrayList<>();
        for (char lletra : ABECEDARI_MAJUSCULA) {
            llistaCharacters.add(lletra);
        }
        Collections.shuffle(llistaCharacters, random);
        char[] alfabetPermutat = new char[llistaCharacters.size()];
        for (int i = 0; i < llistaCharacters.size(); i++) {
            alfabetPermutat[i] = llistaCharacters.get(i);
        }
        abecedariPermutat.add(alfabetPermutat);
    }

    public static String xifraPoliAlfa(String msg) {
        StringBuilder xifratString = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char lletra = msg.charAt(i);
            permutaAlfabet();
            char[] alfabetActual = abecedariPermutat.get(abecedariPermutat.size() - 1);
            if (Character.isLowerCase(lletra)) {
                lletra = Character.toUpperCase(lletra);
                for (int j = 0; j < ABECEDARI_MAJUSCULA.length; j++) {
                    if (lletra == ABECEDARI_MAJUSCULA[j]) xifratString.append(Character.toLowerCase(alfabetActual[j]));
                }
            } else if (Character.isUpperCase(lletra)) {
                for (int j = 0; j < ABECEDARI_MAJUSCULA.length; j++) {
                    if (lletra == ABECEDARI_MAJUSCULA[j]) xifratString.append(alfabetActual[j]);
                }
            } else xifratString.append(lletra);
        }
        return xifratString.toString();
    }

    public static String desxifraPoliAlfa(String msgXifrat) {
        StringBuilder desxifratString = new StringBuilder();
        for (int i = 0; i < msgXifrat.length(); i++) {
            permutaAlfabet();
            char lletra = msgXifrat.charAt(i);
            char[] alfabetActual = abecedariPermutat.get(abecedariPermutat.size() - 1);
            if (Character.isLowerCase(lletra)) {
                lletra = Character.toUpperCase(lletra);
                for (int j = 0; j < alfabetActual.length; j++) {
                    if (lletra == alfabetActual[j]) desxifratString.append(Character.toLowerCase(ABECEDARI_MAJUSCULA[j]));
                }
            } else if (Character.isUpperCase(lletra)) {
                for (int j = 0; j < alfabetActual.length; j++) {
                    if (lletra == alfabetActual[j]) desxifratString.append(ABECEDARI_MAJUSCULA[j]);
                }
            } else desxifratString.append(lletra);
        }
        return desxifratString.toString();
    }
}
