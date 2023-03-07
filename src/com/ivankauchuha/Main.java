package com.ivankauchuha;

import com.ivankauchuha.model.Artist;
import com.ivankauchuha.model.DataSource;
import com.ivankauchuha.model.SongArtist;

import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        if (!dataSource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        List<Artist> artists = dataSource.queryArtists(DataSource.ORDER_BY_NONE);
        if (artists.isEmpty()) {
            System.out.println("No artists!");
            return;
        }

        for (Artist artist: artists) {
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }

        List<String> albumsForArtists = dataSource.queryAlbumsForArtists("Carole King", DataSource.ORDER_BY_ASC);

        for (String album : albumsForArtists) {
            System.out.println(album);
        }

        List<SongArtist> songArtists = dataSource.queryArtistForSong("Go Your Own Way", DataSource.ORDER_BY_ASC);
        if (songArtists.isEmpty()) {
            System.out.println("Couldn't find the artist for the song");
        }

        for (SongArtist artist: songArtists) {
            System.out.println("Artist name = " + artist.getArtistName() + " Album name = " + artist.getAlbumName() +
            " Track = " + artist.getTrack());
        }

        dataSource.querySongsMetadata();
        dataSource.queryAlbumsMetadata();
        dataSource.queryArtistsMetadata();

        int count = dataSource.getCount(DataSource.TABLE_SONGS);
        System.out.println("Number of songs is: " + count);

        dataSource.createViewForSongArtists();


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a songs title: ");
        String title = scanner.nextLine();

        songArtists = dataSource.querySongInfoView(title);
        if (songArtists.isEmpty()) {
            System.out.println("Couldn't find the artist for the song");
            return;
        }

        for (SongArtist artist: songArtists) {
            System.out.println("FROM VIEW - Artist name = " + artist.getArtistName() + " Album name = "
                    + artist.getAlbumName() + " Track number = " + artist.getTrack());
        }

        dataSource.insertSong("Bird Dog","Everly Brothers", "All-Time Greatest Hits", 7);

        dataSource.close();
    }
}
