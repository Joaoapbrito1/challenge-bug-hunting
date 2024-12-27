package repository;

import model.Video;
import util.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileVideoRepository implements VideoRepository {
    private final FileHandler fileHandler;

    public FileVideoRepository(String filePath) {
        this.fileHandler = new FileHandler(filePath);
    }

    @Override
    public void save(Video video) {
        try {
            fileHandler.writeLine(video.toString());
        } catch (IOException e) {
            System.err.println("Erro ao salvar vídeo: " + e.getMessage());
        }
    }

    @Override
    public List<Video> findAll() {
        List<Video> videos = new ArrayList<>();
        try {
            List<String> lines = fileHandler.readLines();
            for (String line : lines) {
                Video video = Video.fromString(line);
                if (video != null) {
                    videos.add(video);
                } else {
                    System.err.println("Linha ignorada devido a erro de parsing: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler vídeos: " + e.getMessage());
        }
        return videos;
    }
}