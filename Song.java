package com.company;

public class Song {
    private String songTitle;
    private int songDurationSeconds;

    public Song(String songTitle, int songDurationSeconds) {
        this.songTitle = songTitle;
        this.songDurationSeconds = songDurationSeconds;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public int getSongDurationSeconds() {
        return songDurationSeconds;
    }
}
