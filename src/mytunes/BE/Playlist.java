/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jesper
 */
public class Playlist {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final IntegerProperty playlists_id = new SimpleIntegerProperty();
    private final StringProperty songsTitle = new SimpleStringProperty();
    private final StringProperty songs_artist = new SimpleStringProperty();
    private final StringProperty songs_genre = new SimpleStringProperty();
    private final StringProperty songs_time = new SimpleStringProperty();
    private final StringProperty songs_fileLocation = new SimpleStringProperty();
    private final IntegerProperty playlistOrder = new SimpleIntegerProperty();
    /*
    * id methods
    */
    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }
    /*
    * playlist_order methods
    */
    public int getPlaylistOrder() {
        return playlistOrder.get();
    }

    public void setPlaylistOrder(int value) {
        playlistOrder.set(value);
    }

    public IntegerProperty playlistOrderProperty() {
        return playlistOrder;
    }
    /*
    * playlists_id methods
    */
    public int getPlaylistsId() {
        return playlists_id.get();
    }

    public void setPlaylistsId(int value) {
        playlists_id.set(value);
    }

    public IntegerProperty PlaylistsIdProperty() {
        return playlists_id;
    }
    /*
    * songs methods
    */
    public String getSongsTitle() {
        return songsTitle.get();
    }

    public void setSongsTitle(String value) {
        songsTitle.set(value);
    }

    public StringProperty songsTitleProperty() {
        return songsTitle;
    }

    public String getSongsArtist() {
        return songs_artist.get();
    }

    public void setSongsArtist(String value) {
        songs_artist.set(value);
    }

    public StringProperty songsArtistProperty() {
        return songs_artist;
    }

    public String getSongsGenre() {
        return songs_genre.get();
    }

    public void setSongsGenre(String value) {
        songs_genre.set(value);
    }

    public StringProperty songsGenreProperty() {
        return songs_genre;
    }

    public String getSongsTime() {
        return songs_time.get();
    }

    public void setSongsTime(String value) {
        songs_time.set(value);
    }

    public StringProperty songsTimeProperty() {
        return songs_time;
    }

    public String getSongsFileLocation() {
        return songs_fileLocation.get();
    }

    public void setSongsFileLocation(String value) {
        songs_fileLocation.set(value);
    }

    public StringProperty songsFileLocationProperty() {
        return songs_fileLocation;
    }

    @Override
    public String toString() {
        return "" + songsTitle.getValue() + "\t" + songs_artist.getValue() + "\t" + songs_genre.getValue() + "\t" + songs_time.getValue() + "\t" + songs_fileLocation.getValue();
    }
}
