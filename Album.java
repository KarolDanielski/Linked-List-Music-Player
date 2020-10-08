package com.company;

import java.util.ArrayList;

public class Album {

    private String albumName;
    private ArrayList<Song> songsOnAlbumArrayList;

    public Album(String albumName) {
        this.albumName = albumName;
        this.songsOnAlbumArrayList = new ArrayList<Song>();
    }

    public Song addSongToAlbum(String newSongName, int duration) {
        Song newSong = new Song(newSongName, duration);
        songsOnAlbumArrayList.add(newSong);
        return newSong;
    }
    public void showSongsOnAlbum() {
        for (int i =0; i < songsOnAlbumArrayList.size(); i++) {
            System.out.println((i+1) + ": " + songsOnAlbumArrayList.get(i).getSongTitle() + " -- Duration: " + songsOnAlbumArrayList.get(i).getSongDurationSeconds() );
        }
    }
    public Song getSong(int number) {
        return songsOnAlbumArrayList.get(number);
    }

    public ArrayList<Song> getSongsOnAlbumArrayList() {
        return songsOnAlbumArrayList;
    }

    public String getAlbumName() {
        return this.albumName;
    }



}
