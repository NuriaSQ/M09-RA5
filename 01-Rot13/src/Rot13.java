public class Rot13 {

    public static char[] guardarAbecedariMinuscula = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toCharArray();
    public static char[] guardarAbecedariMajuscula = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();

    public static void main(String[] args) {
        String cadenaXifrada = "Hola, què tal?";
        String cadenaDesxifrada = "Óvug, abm ägu?";

        String resultatCadenaXifrada = xifraRot13(cadenaXifrada);
        String resultatCadenaDesxifrada = desxifraRot13(cadenaDesxifrada);

        System.out.println("Xifrat: " + cadenaXifrada + " => " + resultatCadenaXifrada);
        System.out.println("Desxifrat: " + cadenaDesxifrada + " => " + resultatCadenaDesxifrada);
    } 

    private static String xifraRot13(String cadena){
        String xifraRot13 = "";
        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            if (Character.isLowerCase(lletra)) {
                for (int index = 0; index < guardarAbecedariMinuscula.length; index++) {
                    if (lletra == guardarAbecedariMinuscula[index]){
                        if(index + 13 >= guardarAbecedariMinuscula.length) xifraRot13 += guardarAbecedariMinuscula[(index + 13) - guardarAbecedariMinuscula.length];
                        else xifraRot13 += guardarAbecedariMinuscula[index + 13];  
                    }
                }
            } else if (Character.isUpperCase(lletra)) {
                for (int index = 0; index < guardarAbecedariMajuscula.length; index++) {
                    if (lletra == guardarAbecedariMajuscula[index]){
                        if(index + 13 >= guardarAbecedariMajuscula.length) xifraRot13 += guardarAbecedariMajuscula[(index + 13) - guardarAbecedariMajuscula.length];
                        else xifraRot13 += guardarAbecedariMajuscula[index + 13];
                    }
                }
            } else xifraRot13 += lletra;
        } return xifraRot13;
    }

    private static String desxifraRot13(String cadena){
        String desxifraRot13 = "";
        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            if (Character.isLowerCase(lletra)) {
                for (int index = 0; index < guardarAbecedariMinuscula.length; index++) {
                    if (lletra == guardarAbecedariMinuscula[index]){
                        if(index - 13 < 0) desxifraRot13 += guardarAbecedariMinuscula[(index - 13) + guardarAbecedariMinuscula.length];
                        else desxifraRot13 += guardarAbecedariMinuscula[index - 13];  
                    }
                }
            } else if (Character.isUpperCase(lletra)) {
                for (int index = 0; index < guardarAbecedariMajuscula.length; index++) {
                    if (lletra == guardarAbecedariMajuscula[index]){
                        if(index - 13 < 0) desxifraRot13 += guardarAbecedariMajuscula[(index - 13) + guardarAbecedariMajuscula.length];
                        else desxifraRot13 += guardarAbecedariMajuscula[index - 13];
                    }
                }
            } else desxifraRot13 += lletra;
        } return desxifraRot13;
    }
}
