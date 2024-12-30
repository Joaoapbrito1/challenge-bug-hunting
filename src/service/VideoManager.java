package service;

import model.Video;
import repository.VideoRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VideoManager {
    private final VideoRepository repository;
    private final VideoValidator validator = new VideoValidator();
    private final Scanner scanner = new Scanner(System.in);

    public VideoManager(VideoRepository repository) {
        this.repository = repository;
    }

    public void addVideo() {
        List<Video> existingVideos = repository.findAll();

        String titulo, descricao, categoria, dataPublicacaoStr;
        int duracao;

        while (true) {
            System.out.print("Digite o título do vídeo: ");
            titulo = scanner.nextLine();
            if (validator.validateTitle(titulo, existingVideos)) {
                break;
            }
        }

        while (true) {
            System.out.print("Digite a descrição do vídeo: ");
            descricao = scanner.nextLine();
            if (validator.validateDescription(descricao)) {
                break;
            }
        }

        while (true) {
            System.out.print("Digite a duração do vídeo (em minutos): ");
            try {
                duracao = Integer.parseInt(scanner.nextLine());
                if (validator.validateDuration(duracao)) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: A duração deve ser um número válido.");
            }
        }

        categoria = validator.chooseCategory();

        while (true) {
            System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
            dataPublicacaoStr = scanner.nextLine();
            if (validator.validateDate(dataPublicacaoStr)) {
                break;
            }
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataPublicacao = sdf.parse(dataPublicacaoStr);

            Video video = new Video(titulo, descricao, duracao, categoria, dataPublicacao);
            repository.save(video);
            System.out.println("Vídeo adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar o vídeo: " + e.getMessage());
        }
    }

    public void listVideos() {
        List<Video> videos = repository.findAll();
        if (videos.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado.");
        } else {
            videos.forEach(System.out::println);
        }
    }
}