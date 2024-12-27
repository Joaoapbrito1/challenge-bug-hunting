package service;

import model.Video;
import repository.VideoRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VideoManager {
    private final VideoRepository videoRepository;
    private final VideoValidator videoValidator;

    public VideoManager(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
        this.videoValidator = new VideoValidator();
    }

    public void addVideo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o título do vídeo: ");
        String titulo = scanner.nextLine();

        System.out.print("Digite a descrição do vídeo: ");
        String descricao = scanner.nextLine();

        // Validar título e descrição
        if (!videoValidator.isValidTituloDescricao(titulo, descricao)) {
            System.out.println("Título e descrição não podem estar vazios.");
            return;
        }

        System.out.print("Digite a duração do vídeo (em minutos): ");
        int duracao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        // Validar duração
        if (!videoValidator.isValidDuracao(duracao)) {
            System.out.println("A duração deve ser um número positivo.");
            return;
        }

        // Apresentar lista de categorias
        String categoria = escolherCategoria(scanner);

        if (categoria == null) {
            System.out.println("Categoria inválida selecionada.");
            return;
        }

        System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();

        // Validar data
        if (!videoValidator.isValidData(dataStr)) {
            System.out.println("Data inválida.");
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataPublicacao = sdf.parse(dataStr);
            Video video = new Video(titulo, descricao, duracao, categoria, dataPublicacao);
            videoRepository.save(video);
            System.out.println("Vídeo adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar vídeo.");
        }
    }

    private String escolherCategoria(Scanner scanner) {
        // Exibe as opções de categoria para o usuário
        System.out.println("Escolha a categoria do vídeo:");
        System.out.println("1. Filme");
        System.out.println("2. Série");
        System.out.println("3. Podcast");
        System.out.println("4. Vídeo");
        System.out.println("5. Videoclipe");
        System.out.println("6. DVD");
        System.out.print("Escolha uma opção (1-6): ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        // Retorna a categoria baseada na escolha do usuário
        switch (opcao) {
            case 1: return "Filme";
            case 2: return "Série";
            case 3: return "Podcast";
            case 4: return "Vídeo";
            case 5: return "Videoclipe";
            case 6: return "DVD";
            default: return null;
        }
    }

    public List<Video> listVideos() {
        return videoRepository.findAll();
    }
}