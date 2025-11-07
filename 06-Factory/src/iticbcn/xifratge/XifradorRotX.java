package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {

    public static final char[] ABECEDARI_MINUSCULA = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toCharArray();
    public static final char[] ABECEDARI_MAJUSCULA = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();
    public static final int ABECEDARI = 43;

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplacament;
        try {
            desplacament = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        
        if (desplacament < 1 || desplacament > ABECEDARI) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }

        String xifrat = xifraRotX(msg, desplacament);
        return new TextXifrat(xifrat.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplacament;
        try {
            desplacament = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }

        if (desplacament < 1 || desplacament > ABECEDARI) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }

        return desxifraRotX(new String(xifrat.getBytes()), desplacament);
    }

    private String xifraRotX(String cadena, int desplacament ){
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
        return xifrarString.toString();
    }

    private String desxifraRotX(String cadena, int desplacament){
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
        return desxifrarString.toString();
    }
}
