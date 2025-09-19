public class Rot13 {
    public static void main(String[] args) {
        String abecedari = "abcdefghijklmnñopqrstuvwxyzàèìòùáéíóúäëïöü ";
        String xifraRot13 = "hola que";
        String desxifraRot13 = "";

        char[] guardarAbecedari = abecedari.toCharArray();

        for (int index = 0; index < guardarAbecedari.length; index++) {
                System.out.println(index + ": " + guardarAbecedari[index]);
            }

        for (int i = 0; i < xifraRot13.length(); i++) {
            char lletra = xifraRot13.charAt(i);

            if(lletra == ' '){
                desxifraRot13 = desxifraRot13 + " ";
            } else {
                 for (int index = 0; index < guardarAbecedari.length; index++) {
                    if (lletra == guardarAbecedari[index]){
                        desxifraRot13 = desxifraRot13 + guardarAbecedari[index + 13];
                    }
                }
            }    
        }
        System.out.println(desxifraRot13);
    } 
}
