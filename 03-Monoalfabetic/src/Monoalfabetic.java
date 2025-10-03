import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {

    public static final char[] ABECEDARI_MAJUSCULA = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();
    public static final char[] ABECEDARI_PERMUTAT = permutaAlfabet(ABECEDARI_MAJUSCULA);

    public static void main(String[] args) {
        for (int i = 0; i < ABECEDARI_MAJUSCULA.length; i++) {
            System.out.print(ABECEDARI_MAJUSCULA[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < ABECEDARI_PERMUTAT.length; i++) {
            System.out.print(ABECEDARI_PERMUTAT[i] + " ");
        }
        System.out.println();
        String cadenaDesxifrada = "HOLA";
        String cadenaXifrada = xifraMonoAlfa(cadenaDesxifrada);

        System.out.println(cadenaDesxifrada);
        System.out.println(cadenaXifrada);

        String cadenaXifrada2 = xifraMonoAlfa(cadenaDesxifrada);
        String cadenaDesxifrada2 = desxifraMonoAlfa(cadenaXifrada2);

        System.out.println(cadenaXifrada2);
        System.out.println(cadenaDesxifrada2);
    }

    private static char[] permutaAlfabet(char[] alfabet){
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

    private static String xifraMonoAlfa(String cadena){
        StringBuilder xifratString = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            for (int j = 0; j < ABECEDARI_MAJUSCULA.length; j++) {
                if(lletra == ABECEDARI_MAJUSCULA[j]){
                    xifratString.append(ABECEDARI_PERMUTAT[j]);      
                } 
            }
        }
        String xifratMonoAlfa = xifratString.toString();
        return xifratMonoAlfa;
    }

    private static String desxifraMonoAlfa(String cadena){
        StringBuilder desxifratString = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            for (int j = 0; j < ABECEDARI_PERMUTAT.length; j++) {
                if(lletra == ABECEDARI_PERMUTAT[j]){
                    desxifratString.append(ABECEDARI_MAJUSCULA[j]);      
                } 
            }
        }
        String desxifratMonoAlfa = desxifratString.toString();
        return desxifratMonoAlfa;
    }
}
