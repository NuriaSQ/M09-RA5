public class RotX {

    public static final char[] ABECEDARI_MINUSCULA = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toCharArray();
    public static final char[] ABECEDARI_MAJUSCULA = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();
    public static final int ABECEDARI = 43;
    public static void main(String[] args) {
        String cadenaDesxifrada = "Visca Mass Effect!";
        String cadenaDesxifrada2 = "XYZ";
        String cadenaXifrada = "Ámwfd Pdww Ijjifx!";
        String cadenaXifrada2 = "ZAÀ";
        int desplacament = 7;
        int desplacament2 = 2;

        String resultatCadenaXifrada = xifraRotX(cadenaDesxifrada, desplacament);
        String resultatCadenaDesxifrada = desxifraRotX(cadenaXifrada, desplacament);
        String resultatCadenaXifrada2 = xifraRotX(cadenaDesxifrada2, desplacament2);
        String resultatCadenaDesxifrada2 = desxifraRotX(cadenaXifrada2, desplacament2);
        String[] missatges = forcaBrutaRotX(cadenaXifrada);

        System.out.println("Xifrat");
        System.out.println("(" + desplacament + ")" + cadenaDesxifrada + " => " + resultatCadenaXifrada);
        System.out.println("(" + desplacament2 + ")" + cadenaDesxifrada2 + " => " + resultatCadenaXifrada2);
        System.out.println("\nDesxifrat");
        System.out.println("(" + desplacament + ")" + cadenaXifrada + " => " + resultatCadenaDesxifrada);
        System.out.println("(" + desplacament2 + ")" + cadenaXifrada2 + " => " + resultatCadenaDesxifrada2);
        System.out.println("\nMissatge xifrat: " + cadenaXifrada);
        System.out.println("===================================");
        for (int i = 0; i < missatges.length; i++) {
            System.out.println("(" + i + ")" + missatges[i]);
        }
    }

    private static String xifraRotX(String cadena, int desplacament ){
        StringBuilder xifrarString = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            if (Character.isLowerCase(lletra)) {
                for (int index = 0; index < ABECEDARI_MINUSCULA.length; index++) {
                    if (lletra == ABECEDARI_MINUSCULA[index]){
                        if(index + desplacament >= ABECEDARI_MINUSCULA.length) xifrarString.append(ABECEDARI_MINUSCULA[(index + desplacament) - ABECEDARI_MINUSCULA.length]);
                        else xifrarString.append(ABECEDARI_MINUSCULA[index + desplacament]);  
                    }
                }
            } else if (Character.isUpperCase(lletra)) {
                for (int index = 0; index < ABECEDARI_MAJUSCULA.length; index++) {
                    if (lletra == ABECEDARI_MAJUSCULA[index]){
                        if(index + desplacament >= ABECEDARI_MAJUSCULA.length) xifrarString.append(ABECEDARI_MAJUSCULA[(index + desplacament) - ABECEDARI_MAJUSCULA.length]);
                        else xifrarString.append(ABECEDARI_MAJUSCULA[index + desplacament]);
                    }
                }
            } else xifrarString.append(lletra);
        } 
        String xifraRotX = xifrarString.toString();
        return xifraRotX;
    }

    private static String desxifraRotX(String cadena, int desplacament){
        StringBuilder desxifrarString = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            if (Character.isLowerCase(lletra)) {
                for (int index = 0; index < ABECEDARI_MINUSCULA.length; index++) {
                    if (lletra == ABECEDARI_MINUSCULA[index]){
                        if(index - desplacament < 0) desxifrarString.append(ABECEDARI_MINUSCULA[(index - desplacament) + ABECEDARI_MINUSCULA.length]);
                        else desxifrarString.append(ABECEDARI_MINUSCULA[index - desplacament]);  
                    }
                }
            } else if (Character.isUpperCase(lletra)) {
                for (int index = 0; index < ABECEDARI_MAJUSCULA.length; index++) {
                    if (lletra == ABECEDARI_MAJUSCULA[index]){
                        if(index - desplacament < 0) desxifrarString.append(ABECEDARI_MAJUSCULA[(index - desplacament) + ABECEDARI_MAJUSCULA.length]);
                        else desxifrarString.append(ABECEDARI_MAJUSCULA[index - desplacament]);
                    }
                }
            } else desxifrarString.append(lletra);
        } 
        String desxifraRotX = desxifrarString.toString();
        return desxifraRotX;
    }

    private static String[] forcaBrutaRotX(String cadenaXifrada){
        String[] missatges = new String[ABECEDARI];
        for (int i = 0; i < ABECEDARI; i++) {
            missatges[i] = desxifraRotX(cadenaXifrada, i);
        }
        return missatges;
    }
}
