package main;

import repository.FileVideoRepository;
import repository.VideoRepository;
import service.VideoManager;
import strategy.SearchStrategy;
import strategy.TitleSearchStrategy;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        VideoRepository videoRepository = new FileVideoRepository("videos.txt");
        VideoManager videoManager = new VideoManager(videoRepository);
        SearchStrategy searchStrategy = new TitleSearchStrategy();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
            System.out.println("1. Adicionar vídeo");
            System.out.println("2. Listar vídeos");
            System.out.println("3. Pesquisar vídeo por título");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1 -> videoManager.addVideo();
                case 2 -> videoManager.listVideos();
                case 3 -> {
                    System.out.print("Digite o título para busca: ");
                    String query = scanner.nextLine();
                    searchStrategy.search(videoRepository.findAll(), query)
                            .forEach(System.out::println);
                }
                case 4 -> {
                    System.out.println("Saindo do sistema...");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}