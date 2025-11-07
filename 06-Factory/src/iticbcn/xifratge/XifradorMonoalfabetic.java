package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador {

    public static final char[] ABECEDARI_MAJUSCULA = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();
    private char[] ABECEDARI_PERMUTAT;

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) 
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        String textXifrat = xifraMonoAlfa(msg);
        return new TextXifrat(textXifrat.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) 
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        return desxifraMonoAlfa(new String(xifrat.getBytes()));
    }

    public XifradorMonoalfabetic() {
        ABECEDARI_PERMUTAT = permutaAlfabet(ABECEDARI_MAJUSCULA);
    }

    private char[] permutaAlfabet(char[] alfabet){
        List<Character> llistaCharacters = new ArrayList<Character>();
        for (char lletra : alfabet) {
            llistaCharacters.add(lletra);
        }
        Collections.shuffle(llistaCharacters);
        char[] alfabetPermutat = new char[llistaCharacters.size()];
        for (int i = 0; i < llistaCharacters.size(); i++) {
            alfabetPermutat[i] = llistaCharacters.get(i);
        }
        return alfabetPermutat;
    }

    private String xifraMonoAlfa(String cadena){
        StringBuilder xifratString = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            if (Character.isLowerCase(lletra)) {
                lletra = Character.toUpperCase(lletra);
                for (int j = 0; j < ABECEDARI_MAJUSCULA.length; j++) {
                    if(lletra == ABECEDARI_MAJUSCULA[j]) xifratString.append(Character.toLowerCase(ABECEDARI_PERMUTAT[j]));      
                }
            } else if (Character.isUpperCase(lletra)) {
                for (int j = 0; j < ABECEDARI_MAJUSCULA.length; j++) {
                    if(lletra == ABECEDARI_MAJUSCULA[j]) xifratString.append(ABECEDARI_PERMUTAT[j]);      
                }
            } else xifratString.append(lletra);
        }
        return xifratString.toString();
    }

    private String desxifraMonoAlfa(String cadena){
        StringBuilder desxifratString = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            if (Character.isLowerCase(lletra)) {
                lletra = Character.toUpperCase(lletra);
                for (int j = 0; j < ABECEDARI_PERMUTAT.length; j++) {
                    if(lletra == ABECEDARI_PERMUTAT[j]) desxifratString.append(Character.toLowerCase(ABECEDARI_MAJUSCULA[j]));      
                }
            } else if (Character.isUpperCase(lletra)) {
                for (int j = 0; j < ABECEDARI_PERMUTAT.length; j++) {
                    if(lletra == ABECEDARI_PERMUTAT[j]) desxifratString.append(ABECEDARI_MAJUSCULA[j]);      
                }
            } else desxifratString.append(lletra);
        }
        return desxifratString.toString();
    }
}