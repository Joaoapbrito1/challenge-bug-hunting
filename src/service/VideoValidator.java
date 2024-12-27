package service;

public class VideoValidator {

    public static boolean validarTituloDescricao(String titulo, String descricao) {
        if (titulo == null || titulo.trim().isEmpty()) {
            System.out.println("Erro: O título não pode estar vazio.");
            return false;
        }
        if (descricao == null || descricao.trim().isEmpty()) {
            System.out.println("Erro: A descrição não pode estar vazia.");
            return false;
        }
        return true;
    }

    public static boolean validarDuracao(int duracao) {
        if (duracao <= 0) {
            System.out.println("Erro: A duração deve ser um número positivo.");
            return false;
        }
        return true;
    }
}
