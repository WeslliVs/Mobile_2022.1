package com.example.gamelist.transactions;

public class Game {

    private static int contadorId = 0;

    private int id;
    private String title;
    private String publi;
    private String platf;

    public Game(){

    }

    public Game(String title, String publi, String platf) {
        this.id = contadorId++;
        this.title = title;
        this.publi = publi;
        this.platf = platf;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubli() {
        return publi;
    }

    public void setPubli(String publi) {
        this.publi = publi;
    }

    public String getPlatf() {
        return platf;
    }

    public void setPlatf(String platf) {
        this.platf = platf;
    }

    @Override
    public String toString() {
        return title;
    }
}
