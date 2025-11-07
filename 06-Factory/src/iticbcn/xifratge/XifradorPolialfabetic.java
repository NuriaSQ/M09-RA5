package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {

    public static final char[] ABECEDARI_MAJUSCULA = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();
    public List<char[]> abecedariPermutat = new ArrayList<>();
    public Random random;
    public static final String clauSecreta = "123456";

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        initRandom(clau);
        String xifrat = xifraPoliAlfa(msg);
        return new TextXifrat(xifrat.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
        initRandom(clau);
        return desxifraPoliAlfa(new String(xifrat.getBytes()));
    }

    public void initRandom(String clauSecreta) {
    random = new Random(Long.parseLong(clauSecreta));
    abecedariPermutat.clear();
    }

    public void permutaAlfabet() {
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

    public String xifraPoliAlfa(String msg) {
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

    public String desxifraPoliAlfa(String msgXifrat) {
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