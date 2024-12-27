package service;

import java.text.SimpleDateFormat;

public class VideoValidator {

    public boolean isValidTituloDescricao(String titulo, String descricao) {
        return titulo != null && !titulo.trim().isEmpty() && descricao != null && !descricao.trim().isEmpty();
    }

    public boolean isValidDuracao(int duracao) {
        return duracao > 0;
    }

    public boolean isValidData(String dataStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            sdf.parse(dataStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}