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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Polygon;
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
    @FXML
    private Button forwButton;
    @FXML
    private Button backButton;
    @FXML
    private Button ppButton;
    
    JFXPanel fxPanel = new JFXPanel();
    String path = "C:\\Users\\Samuel\\Documents\\GitHub\\MyTunesProject\\song1.mp3";
    Media media = new Media(new File(path).toURI().toString());
    private MediaPlayer player = new MediaPlayer(media);
    MediaView mediaView = new MediaView(player);
    @FXML
    private Pane pausePane;
    @FXML
    private Polygon playPane;
     
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        
    }   
    @FXML
    public void clickPlay(ActionEvent event)
    {     
        if(!player.isAutoPlay()){
            player.setAutoPlay(true);
            playPane.setOpacity(0);
            pausePane.setOpacity(1);
            }
        else{            
            player.pause();
            player.setAutoPlay(false);        
            playPane.setOpacity(1);
            pausePane.setOpacity(0);}
        
    }
    
    private void clickPause(ActionEvent event) {
        
    }

    @FXML
    private void clickForw(ActionEvent event) {
    }

    @FXML
    private void clickBack(ActionEvent event) {
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
