package service;

import model.Video;
import repository.VideoRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VideoManager {
    private final VideoRepository repository;
    private final Scanner scanner = new Scanner(System.in);

    public VideoManager(VideoRepository repository) {
        this.repository = repository;
    }

    public void addVideo() {
        // Variáveis para armazenar os dados
        String titulo = "";
        String descricao = "";
        int duracao = -1;
        String categoria = "";
        String dataPublicacaoStr = "";

        // Solicita e valida o título
        while (titulo.isEmpty()) {
            System.out.print("Digite o título do vídeo: ");
            titulo = scanner.nextLine();
            if (titulo.isEmpty()) {
                System.out.println("Erro: O título não pode estar vazio.");
            }
        }

        // Solicita e valida a descrição
        while (descricao.isEmpty()) {
            System.out.print("Digite a descrição do vídeo: ");
            descricao = scanner.nextLine();
            if (descricao.isEmpty()) {
                System.out.println("Erro: A descrição não pode estar vazia.");
            }
        }

        // Solicita e valida a duração
        while (duracao <= 0) {
            System.out.print("Digite a duração do vídeo (em minutos): ");
            try {
                duracao = Integer.parseInt(scanner.nextLine());
                if (duracao <= 0) {
                    System.out.println("Erro: A duração deve ser um número positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: A duração deve ser um número.");
            }
        }

        // Solicita e valida a categoria
        while (categoria.isEmpty()) {
            System.out.print("Digite a categoria do vídeo: ");
            categoria = scanner.nextLine();
            if (categoria.isEmpty()) {
                System.out.println("Erro: A categoria não pode estar vazia.");
            }
        }

        // Solicita e valida a data de publicação
        while (dataPublicacaoStr.isEmpty()) {
            System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
            dataPublicacaoStr = scanner.nextLine();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false); // Impede datas inválidas como 32/13/2024
                Date dataPublicacao = sdf.parse(dataPublicacaoStr);
                // Data válida, continua o processo
                Video video = new Video(titulo, descricao, duracao, categoria, dataPublicacao);
                repository.save(video);
                System.out.println("Vídeo adicionado com sucesso!");
            } catch (Exception e) {
                System.out.println("Erro: A data fornecida é inválida.");
                dataPublicacaoStr = ""; // Força o loop a pedir a data novamente
            }
        }
    }

    public List<Video> listVideos() {
        return repository.findAll();
    }
}