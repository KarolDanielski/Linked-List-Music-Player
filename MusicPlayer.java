package com.company;

import java.util.*;

public class MusicPlayer {
    private AlbumsLibrary newLibrary;
    private LinkedList<Song> playList;

    public MusicPlayer() {
        this.newLibrary = new AlbumsLibrary();
        this.playList = new LinkedList<Song>();
    }

    Scanner userInput = new Scanner(System.in);

    public void createMusicLibrary(int numberOfLevels) { //creates specific number of albums and songs in library

        for (int i = 0; i < numberOfLevels; i++) {
            Album nextAlbum = newLibrary.addAlbumToLibrary("AlbumName " + (i + 1));

            for (int j = 0; j < numberOfLevels; j++) {
                nextAlbum.addSongToAlbum("'" + nextAlbum.getAlbumName() + " Track" + (j+1) +"'",(i+10)*(j+5));
            }
        }
    }

    private void printMainMenu() {
        System.out.println("--MAIN MENU--");
        System.out.println("1 - CREATE PLAYLIST");
        System.out.println("2 - PLAY PLAYLIST");
        System.out.println("3 - QUIT");
    }
    public void startMusicPlayer() {
        boolean quit = false;
        printMainMenu();

        while(!quit) {

            int userChoice = userInput.nextInt();
            userInput.nextLine();

            switch (userChoice) {
                case 1:
                    createPlayList();
                    break;
                case 2:
                    playPlayList();
                   break;
                case 3:
                    quit = true;
                    System.out.println("Music player turned off.");
            }
        }
    }

    private void printCreatePlayListMenu() {
        System.out.println("---CREATE PLAYLIST--");
        System.out.println("1 - Add songs to playlist");
        System.out.println("2 - Remove songs from playlist");
        System.out.println("3 - Show albums library");
        System.out.println("4 - Show songs on album");
        System.out.println("5 - Print playlist");
        System.out.println("6 - Main Menu");
    }
    public void createPlayList() { //set of tools to create a playlist
        boolean quit = false;
        printCreatePlayListMenu();

        while(!quit) {
            int userChoice = userInput.nextInt();
            userInput.nextLine();

            switch(userChoice) {
                case 1:
                    addSongsToPlayList();
                    break;
                case 2:
                    removeSongFromPlayList();
                    break;
                case 3:
                    showAlbumsLibrary();
                    break;
                case 4:
                    System.out.println("Choose album number / outside of range to quit: ");
                    int userAlbumChoice = userInput.nextInt();
                    userInput.nextLine();
                    showSongsOnAlbum(userAlbumChoice - 1);
                    break;
                case 5:
                    printPlayList();
                    break;
                case 6:
                    quit = true;
                    printMainMenu();
            }
        }


    }

    private void printPlayPlayListMenu() {
        System.out.println("--PLAY--");
        System.out.println("1 - Skip forward");
        System.out.println("2 - Skip Backwards");
        System.out.println("3 - Replay current song");
        System.out.println("4 - Quit");
    }
    public void playPlayList() {

        if (!playList.isEmpty()) {
            printPlayPlayListMenu();
            boolean quit= false;

            ListIterator<Song> songIterator = playList.listIterator();
            Song currentlyPlaying = songIterator.next();
            System.out.println("Now playing: " + currentlyPlaying.getSongTitle());
            boolean goingForward = true; //flaga umożliwiająca śledzenie kierunku link listy, bez niej dublowałyby się odtwarzane piosenki, wynika z właściwości link listy przechodząc z next() na previous() i odwrotnie

            while (!quit) {

                int userChoice = userInput.nextInt();
                userInput.nextLine();

                switch (userChoice) {
                    case 1://można przesuwać utwory do przodu w nieskończonej pętli
                        if (songIterator.hasNext()) {
                            if (goingForward) {
                                currentlyPlaying = songIterator.next();
                            } else {
                                currentlyPlaying = songIterator.next();//podwójna iteracja na zmianę kierunku tu i poniżej
                                if(songIterator.hasNext()) { //poprawka na wypadek tylko jednej pozycji na playlist, wtedy nie ma dwa razy next(), które jest wymagane przy zmianie kierunku iteracji
                                    currentlyPlaying = songIterator.next();
                                }
                                goingForward = true;
                            }
                        } else {
                            while (songIterator.hasPrevious()) { //przewijanie iteratora do początku
                                currentlyPlaying = songIterator.previous();
                                goingForward = false;
                            }
                        }
                        System.out.println("Now playing: " + currentlyPlaying.getSongTitle());
                        break;
                    case 2://można cofać utwory w nieskończonej pętli
                        if (songIterator.hasPrevious()) {
                            if (goingForward) { //podwójna iteracja po zmianie kierynku
                                currentlyPlaying = songIterator.previous();
                                if (songIterator.hasPrevious()) { //poprawka na wypadek tylko jednej pozycji na playlist (wtedy podwójne egzekwowanie next() prowadziłoby do out of bonds)
                                    currentlyPlaying = songIterator.previous();
                                }
                                goingForward = false;
                            } else {
                                currentlyPlaying = songIterator.previous();
                            }
                        } else {
                            while (songIterator.hasNext()) { //przewijanie iteratora na koniec
                                currentlyPlaying = songIterator.next();
                                goingForward = true;
                            }
                        }
                        System.out.println("Now playing: " + currentlyPlaying.getSongTitle());
                        break;
                    case 3: //replay the same song
                        System.out.println("Now playing: " + currentlyPlaying.getSongTitle());
                        break;
                    case 4:
                        quit = true;
                        printMainMenu();
                }
            }
        } else {
            System.out.println("Playlist is empty.");
        }
    }

    public void showAlbumsLibrary() {
        System.out.println("---ALBUMS LIBRARY---");
        this.newLibrary.showAllAlbums();
    }

    public void showSongsOnAlbum(int albumNumber) {
        if (albumNumber < newLibrary.getAlbumArrayList().size() && albumNumber >=0 ) {
            newLibrary.getAlbumArrayList().get(albumNumber).showSongsOnAlbum();
        }
        else {
            printCreatePlayListMenu();
        }
    }

    public void addSongsToPlayList() {
        boolean quitChoosingAlbum = false;

        while(!quitChoosingAlbum) { //wybór albumy
            boolean quitAddingSong = false;

            showAlbumsLibrary();
            System.out.println("Choose album number: (Out of range to go back)");
            int albumNumber = userInput.nextInt() - 1;
            userInput.nextLine();
            if (albumNumber < 0 || albumNumber >= newLibrary.getAlbumArrayList().size()) {
                quitChoosingAlbum = true;
            }
            else {
                while (!quitAddingSong) { //wybór piosenek
                    showSongsOnAlbum(albumNumber);
                    System.out.println("Choose song number: (Out of range to go back)");
                    int songNumber = userInput.nextInt() - 1;
                    userInput.nextLine();
                    if (songNumber < 0 || songNumber >= newLibrary.getAlbumArrayList().get(albumNumber).getSongsOnAlbumArrayList().size()) {
                        quitAddingSong = true;
                    } else {
                        Song songToPlayList = newLibrary.getAlbumArrayList().get(albumNumber).getSong(songNumber);
                        this.playList.add(songToPlayList);
                        System.out.println("Added " + songToPlayList.getSongTitle() + " to the playlist!");
                    }
                }
            }
        }
        printCreatePlayListMenu();
    }

    public void removeSongFromPlayList() {
        printPlayList();
        if (!playList.isEmpty()) {
            System.out.println("Which song to remove? Choose number of a song: (Out of range to go back");
            int userChoice = userInput.nextInt() - 1;
            userInput.nextLine();
            if (userChoice >= 0 && userChoice < (playList.size())) {
                System.out.println("Successfully removed " + (userChoice+1) + " " + playList.get(userChoice).getSongTitle());
                playList.remove(userChoice);
                printCreatePlayListMenu();
            } else {
                printCreatePlayListMenu();
            }
        }
    }

    public void printPlayList() {
        if (!playList.isEmpty()) {
            System.out.println("---PLAYLIST---");
            Iterator<Song> iterator = playList.listIterator();
            int counter = 1;
            while (iterator.hasNext()) {
                System.out.println(counter + ": " + iterator.next().getSongTitle());
                counter += 1;
            }
//        for (int i = 0; i < playList.size(); i++) {
//            System.out.println((i+1) + ": " + playList.get(i).getSongTitle());
//        }
        } else {
            System.out.println("Playlist is empty.");
        }
    }
}
