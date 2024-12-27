package repository;

import model.Video;
import utils.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileVideoRepository implements VideoRepository {
    private final String filePath;

    public FileVideoRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(Video video) {
        try {
            FileHandler.writeLine(filePath, video.toString(), true);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o vídeo: " + e.getMessage());
        }
    }

    @Override
    public List<Video> findAll() {
        List<Video> videos = new ArrayList<>();
        try {
            List<String> lines = FileHandler.readLines(filePath);
            for (String line : lines) {
                Video video = Video.fromString(line);
                if (video != null) {
                    videos.add(video);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler os vídeos: " + e.getMessage());
        }
        return videos;
    }
}