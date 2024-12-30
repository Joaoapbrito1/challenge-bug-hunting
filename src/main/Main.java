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
            System.out.println("4. Editar vídeo");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = -1;
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, insira um número válido.");
            }

            switch (opcao) {
                case 1:
                    videoManager.addVideo();
                    break;
                case 2:
                    videoManager.listVideos().forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Digite o título para busca: ");
                    String query = scanner.nextLine();
                    searchStrategy.search(videoManager.listVideos(), query).forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Digite o título do vídeo a ser editado: ");
                    String titleToEdit = scanner.nextLine();
                    videoManager.updateVideo(titleToEdit);
                    break;
                case 5:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}