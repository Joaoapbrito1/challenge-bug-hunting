package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private final File file;

    public FileHandler(String filePath) {
        this.file = new File(filePath);
    }

    public void writeLine(String line) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(line);
            bw.newLine();
        }
    }

    public List<String> readLines() throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}