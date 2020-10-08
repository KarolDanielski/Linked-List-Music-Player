package com.company;

import java.util.ArrayList;

public class AlbumsLibrary {

    private ArrayList<Album> albumArrayList;

    public AlbumsLibrary() {
        this.albumArrayList = new ArrayList<Album>();
    }

    public Album addAlbumToLibrary(String albumName) {
        Album newAlbum = new Album(albumName);
        albumArrayList.add(newAlbum);
        return newAlbum;
    }

    public void showAllAlbums() {
        for (int i = 0; i < albumArrayList.size(); i++) {
            System.out.println((i+1) + ": " + albumArrayList.get(i).getAlbumName());
        }
    }
    public ArrayList<Album> getAlbumArrayList(){
        return albumArrayList;
    }

}
