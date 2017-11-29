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
public class Songs
{
   private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty artist = new SimpleStringProperty();
    private final StringProperty genre = new SimpleStringProperty();
    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty fileLocation = new SimpleStringProperty(); 
    
    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }
    
    
    public String getTitle() {
        return title.get();
    }

    public void setTitle(String value) {
        title.set(value);
    }

    public StringProperty titleProperty() {
        return title;
    }
    
    
    public String getArtist() {
        return artist.get();
    }

    public void setArtist(String value) {
        artist.set(value);
    }

    public StringProperty artistProperty() {
        return artist;
    }
    
    
    public String getGenre() {
        return genre.get();
    }

    public void setGenre(String value) {
        genre.set(value);
    }

    public StringProperty genreProperty() {
        return genre;
    }
    
    
    public String getTime() {
        return time.get();
    }

    public void setTime(String value) {
        time.set(value);
    }

    public StringProperty timeProperty() {
        return time;
    }
    
    
    public String getFileLocation() {
        return fileLocation.get();
    }

    public void setFileLocation(String value) {
        fileLocation.set(value);
    }

    public StringProperty fileLocationProperty() {
        return fileLocation;
    }

    @Override
    public String toString() {
        return "" + title.getValue() + "\t" + artist.getValue() + "\t" + genre.getValue() + "\t" + time.getValue() + "\t" + fileLocation.getValue();
    }
}
