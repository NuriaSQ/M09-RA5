public class Rot13 {
    public static void main(String[] args) {
        String xifraRot13 = "Hola qué Tal!";

        String desxifraRot13 = desxifraRot13(xifraRot13);
        System.out.println(desxifraRot13);
    } 

    private static String xifrarLletresMinuscules (String desxifraRot13, char lletra){
        String abecedariMinuscula = "abcdefghijklmnñopqrstuvwxyzàèìòùáéíóúäëïöüç";
        char[] guardarAbecedariMinuscula = abecedariMinuscula.toCharArray();
        for (int index = 0; index < guardarAbecedariMinuscula.length; index++) {
            if (lletra == guardarAbecedariMinuscula[index]){
                if(index + 13 >= guardarAbecedariMinuscula.length){
                    desxifraRot13 +=guardarAbecedariMinuscula[(index + 13) - guardarAbecedariMinuscula.length];
                } else {
                    desxifraRot13 += guardarAbecedariMinuscula[index + 13];
                }
            }
        }
        return desxifraRot13;
    }

    private static String xifrarLletresMajuscules (String desxifraRot13, char lletra){
        String abecedariMajuscula = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZÀÈÌÒÙÁÉÍÓÚÄËÏÖÜÇ";
        char[] guardarAbecedariMajuscula = abecedariMajuscula.toCharArray();
        for (int index = 0; index < guardarAbecedariMajuscula.length; index++) {
            if (lletra == guardarAbecedariMajuscula[index]){
                if(index + 13 >= guardarAbecedariMajuscula.length){
                    desxifraRot13 += guardarAbecedariMajuscula[(index + 13) - guardarAbecedariMajuscula.length];
                } else {
                    desxifraRot13 += guardarAbecedariMajuscula[index + 13];
                }
            }
        }
        return desxifraRot13;
    }

    private static String xifrarCaractersEspecials (String desxifraRot13, char lletra){
        String caractersEspecials = "!¡.:,;_-?¿ ";
        char[] guardarCaractersEspecials = caractersEspecials.toCharArray();
        for (int j = 0; j < guardarCaractersEspecials.length; j++) {
            if(lletra == guardarCaractersEspecials[j]){
                desxifraRot13 += guardarCaractersEspecials[j];
            }   
        }
        return desxifraRot13;
    }

    private static String desxifraRot13(String xifraRot13){
        String desxifraRot13 = "";
        for (int i = 0; i < xifraRot13.length(); i++) {
            char lletra = xifraRot13.charAt(i);

            if (Character.isLowerCase(lletra)) {
                desxifraRot13 = xifrarLletresMinuscules(desxifraRot13, lletra);
            } else if (Character.isUpperCase(lletra)) {
                desxifraRot13 = xifrarLletresMajuscules(desxifraRot13, lletra);
            } else {
                desxifraRot13 = xifrarCaractersEspecials(desxifraRot13, lletra);
            }
        }
        return desxifraRot13;
    }
}
