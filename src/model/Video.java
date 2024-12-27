package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Video {
    private String titulo;
    private String descricao;
    private int duracao; // em minutos
    private String categoria;
    private Date dataPublicacao;

    public Video(String titulo, String descricao, int duracao, String categoria, Date dataPublicacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.categoria = categoria;
        this.dataPublicacao = dataPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public String getCategoria() {
        return categoria;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return titulo + ";" + descricao + ";" + duracao + ";" + categoria + ";" + sdf.format(dataPublicacao);
    }

    public static Video fromString(String linha) {
        try {
            String[] partes = linha.split(";");
            if (partes.length != 5) {
                throw new IllegalArgumentException("Linha de entrada inválida: " + linha);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String titulo = partes[0].trim();
            String descricao = partes[1].trim();
            int duracao = Integer.parseInt(partes[2].trim());
            String categoria = partes[3].trim();
            Date dataPublicacao = sdf.parse(partes[4].trim());

            // Validações adicionais
            if (titulo.isEmpty() || descricao.isEmpty() || duracao <= 0 || categoria.isEmpty()) {
                throw new IllegalArgumentException("Dados do vídeo inválidos: " + linha);
            }

            return new Video(titulo, descricao, duracao, categoria, dataPublicacao);
        } catch (Exception e) {
            System.err.println("Erro ao converter linha em vídeo: " + e.getMessage());
            return null;
        }
    }
}