package service;

import model.Video;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class VideoValidator {

    public boolean validateTitle(String titulo, List<Video> existingVideos) {
        if (titulo.isEmpty()) {
            System.out.println("Erro: O título não pode estar vazio.");
            return false;
        }
        if (existingVideos.stream().anyMatch(video -> video.getTitulo().equalsIgnoreCase(titulo))) {
            System.out.println("Erro: Já existe um vídeo com este título.");
            return false;
        }
        return true;
    }

    public boolean validateDescription(String descricao) {
        if (descricao.isEmpty()) {
            System.out.println("Erro: A descrição não pode estar vazia.");
            return false;
        }
        return true;
    }

    public boolean validateDuration(int duracao) {
        if (duracao <= 0) {
            System.out.println("Erro: A duração deve ser um número positivo.");
            return false;
        }
        return true;
    }

    public String chooseCategory() {
        Scanner scanner = new Scanner(System.in);
        String[] categorias = {"Filme", "Serie", "Podcast", "Video", "Videoclipe", "Dvd"};
        String categoria = "";

        System.out.println("Escolha a categoria do vídeo:");
        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i + 1) + ". " + categorias[i]);
        }

        while (categoria.isEmpty()) {
            System.out.print("Digite o número da categoria: ");
            try {
                int escolha = Integer.parseInt(scanner.nextLine());
                if (escolha < 1 || escolha > categorias.length) {
                    System.out.println("Erro: Categoria inválida.");
                } else {
                    categoria = categorias[escolha - 1];
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido.");
            }
        }
        return categoria;
    }

    public boolean validateDate(String dataPublicacaoStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            sdf.parse(dataPublicacaoStr);
            return true;
        } catch (Exception e) {
            System.out.println("Erro: A data fornecida é inválida.");
            return false;
        }
    }
}
