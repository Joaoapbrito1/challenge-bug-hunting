package service;

import model.Video;
import repository.VideoRepository;

import java.util.List;

public class VideoManager {
    private final VideoRepository videoRepository;

    public VideoManager(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public void addVideo(Video video) {
        videoRepository.save(video);
    }

    public List<Video> listVideos() {
        return videoRepository.findAll();
    }
}