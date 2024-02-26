package org.example.util;

public class FuncoesUtil {

    public static String formatarColuna(String nomeColuna, int tamanhoColuna) {
        if (nomeColuna.length() > tamanhoColuna) {
            nomeColuna = nomeColuna.substring(0, tamanhoColuna);
        }

        return nomeColuna + " ".repeat(tamanhoColuna - nomeColuna.length()) + "|";
    }
}
