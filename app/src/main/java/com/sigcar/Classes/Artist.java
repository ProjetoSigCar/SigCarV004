package com.sigcar.Classes;

public class Artist {
    private String artistId;
    private String artistName;
    private String artistGenre;
    private String artistIdade;

    public Artist() {
        // É preciso um construtor vazio. Veremos porque mais a frente.
    }

    public Artist(String artistId, String artistName, String artistGenre, String artistIdade) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistGenre = artistGenre;
        this.artistIdade = artistIdade;
    }

    // também precisamos de getters, exigência do Firebase.

    public String getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistGenre() {
        return artistGenre;
    }

    public String getArtistIdade() {
        return artistIdade;
    }
}