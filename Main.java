package com.company;

public class Main {

    public static void main(String[] args) {

        //App mimics primitive text based music player. It allows you to create and update playlist from predefined music library,
        //and play the music with skipping and replaying songs.

        MusicPlayer myMusicPlayer = new MusicPlayer();
        myMusicPlayer.createMusicLibrary(3); //automatic music library generator
        myMusicPlayer.startMusicPlayer();
    }
}
