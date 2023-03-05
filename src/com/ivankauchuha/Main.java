package com.ivankauchuha;

import com.ivankauchuha.model.Artist;
import com.ivankauchuha.model.DataSource;
import com.ivankauchuha.model.SongArtist;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        if (!dataSource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        List<Artist> artists = dataSource.queryArtists(DataSource.ORDER_BY_NONE);
        if (artists == null) {
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
        if (songArtists == null) {
            System.out.println("Couldn't find the artist for the song");
        }

        for (SongArtist artist: songArtists) {
            System.out.println("Artist name = " + artist.getArtistName() + " Album name = " + artist.getAlbumName() +
            " Track = " + artist.getTrack());
        }

        dataSource.querySongsMetadata();

        dataSource.close();
    }
}
