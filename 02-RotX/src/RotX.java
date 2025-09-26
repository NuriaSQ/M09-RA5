public class RotX {

    public static char[] abecedariMinuscula = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toCharArray();
    public static char[] abecesadirMajuscula = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();
    public static final int ABECEDARI = 43;
    public static void main(String[] args) {
        String cadenaDesxifrada = "XYZ";
        String cadenaXifrada = "ZAÁ";
        int desplacament = 2;

        String resultatCadenaXifrada = xifraRotX(cadenaDesxifrada, desplacament);
        String resultatCadenaDesxifrada = desxifraRotX(cadenaXifrada, desplacament);

        System.out.println("Xifrat: " + cadenaDesxifrada + " => " + resultatCadenaXifrada);
        System.out.println("Desxifrat: " + cadenaXifrada + " => " + resultatCadenaDesxifrada);
    }

    private static String xifraRotX(String cadena, int desplacament ){
        StringBuilder xifrarString = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            if (Character.isLowerCase(lletra)) {
                for (int index = 0; index < abecedariMinuscula.length; index++) {
                    if (lletra == abecedariMinuscula[index]){
                        if(index + desplacament >= abecedariMinuscula.length) xifrarString.append(abecedariMinuscula[(index + desplacament) - abecedariMinuscula.length]);
                        else xifrarString.append(abecedariMinuscula[index + desplacament]);  
                    }
                }
            } else if (Character.isUpperCase(lletra)) {
                for (int index = 0; index < abecesadirMajuscula.length; index++) {
                    if (lletra == abecesadirMajuscula[index]){
                        if(index + desplacament >= abecesadirMajuscula.length) xifrarString.append(abecesadirMajuscula[(index + desplacament) - abecesadirMajuscula.length]);
                        else xifrarString.append(abecesadirMajuscula[index + desplacament]);
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
                for (int index = 0; index < abecedariMinuscula.length; index++) {
                    if (lletra == abecedariMinuscula[index]){
                        if(index - desplacament < 0) desxifrarString.append(abecedariMinuscula[(index - desplacament) + abecedariMinuscula.length]);
                        else desxifrarString.append(abecedariMinuscula[index - desplacament]);  
                    }
                }
            } else if (Character.isUpperCase(lletra)) {
                for (int index = 0; index < abecesadirMajuscula.length; index++) {
                    if (lletra == abecesadirMajuscula[index]){
                        if(index - desplacament < 0) desxifrarString.append(abecesadirMajuscula[(index - desplacament) + abecesadirMajuscula.length]);
                        else desxifrarString.append(abecesadirMajuscula[index - desplacament]);
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
            System.out.println("(" + i + ")" + desxifraRotX(cadenaXifrada, i));
        }
        return null;
    }
}
