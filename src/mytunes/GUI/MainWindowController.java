/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Jesper
 */
public class MainWindowController implements Initializable
{
    
    private Label label;
    @FXML
    private Button btnPlay;
    @FXML
    private ListView<?> lstPlaylists;
    @FXML
    private Button btnNewPlaylist;
    @FXML
    private Button btnEditPlaylist;
    @FXML
    private Button btnDeletePlaylist;
    @FXML
    private ListView<?> lstSongsInPlaylist;
    @FXML
    private Button btnUp;
    @FXML
    private Button btnDown;
    @FXML
    private Button btnPlaylistDelete;
    @FXML
    private ListView<?> lstSongs;
    @FXML
    private Button btnNewSong;
    @FXML
    private Button btnEditSong;
    @FXML
    private Button btnDeleteSong;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnAddSong;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void clickPlay(ActionEvent event)
    {
        JFXPanel fxPanel = new JFXPanel();
        
        String path = "song1.wav";
        
        Media media = new Media(new File(path).toURI().toString());
        
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        
        MediaView mediaView = new MediaView(mediaPlayer);
    }

    @FXML
    private void clickNewPlaylist(ActionEvent event) throws IOException
    {
        Stage newWindow = new Stage();
        
        newWindow.initModality(Modality.APPLICATION_MODAL);
        
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("NewPlaylist.fxml"));
        
        Parent root = fxLoader.load();
        
        NewPlaylistController controller = fxLoader.getController();
        controller.setParentWindowController(this);
        
        Scene scene = new Scene(root);
        
        newWindow.setScene(scene);
        newWindow.showAndWait();
    }

    @FXML
    private void clickEditPlaylist(ActionEvent event)
    {
    }

    @FXML
    private void clickDeletePlaylist(ActionEvent event)
    {
    }

    @FXML
    private void clickUp(ActionEvent event)
    {
    }

    @FXML
    private void clickDown(ActionEvent event)
    {
    }

    @FXML
    private void clickPlaylistDelete(ActionEvent event)
    {
    }


    @FXML
    private void clickNewSong(ActionEvent event) throws IOException
    {
        Stage newWindow = new Stage();
        
        newWindow.initModality(Modality.APPLICATION_MODAL);
        
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AddWindow.fxml"));
        
        Parent root = fxLoader.load();
        
        AddWindowController controller = fxLoader.getController();
        controller.setParentWindowController(this);
        
        Scene scene = new Scene(root);
        
        newWindow.setScene(scene);
        newWindow.showAndWait();
    }

    @FXML
    private void clickEditSong(ActionEvent event)
    {
    }

    @FXML
    private void clickDeleteSong(ActionEvent event)
    {
    }

    @FXML
    private void clickSearch(ActionEvent event)
    {
    }

    @FXML
    private void clickAddSong(ActionEvent event)
    {
    }
    
}