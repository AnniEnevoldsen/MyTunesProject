/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    @FXML
    protected Label lblName;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void clickPlay(ActionEvent event)
    {
        System.out.println("play music");
    }
    
    /*
    * This opens up the NewPlaylist when add playlist is clicked in the MainWindow.
    */
    @FXML
    private void clickNewPlaylist(ActionEvent event) throws IOException
    {
                Stage myNewStage = new Stage(); // new window
        myNewStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(
            getClass().getResource("NewPlaylist.fxml"));
        
        Parent root = fxLoader.load();
        
        NewPlaylistController owc = fxLoader.getController();
        owc.setParentWindowController(this);
        
        Scene scene = new Scene(root);
        
        myNewStage.setScene(scene);
        myNewStage.showAndWait();
       
    }

    
    @FXML
    private void clickEditPlaylist(ActionEvent event)
    {
        System.out.println("edit playlist by opening the playlist name filled in");
    }

    @FXML
    private void clickDeletePlaylist(ActionEvent event)
    {
        System.out.println("delete playlist");
    }

    @FXML
    private void clickUp(ActionEvent event)
    {
        System.out.println("move song up");
    }

    @FXML
    private void clickDown(ActionEvent event)
    {
        System.out.println("move song down");
    }

    @FXML
    private void clickPlaylistDelete(ActionEvent event)
    {
        System.out.println("delete from playlist");
    }

    /*
    * This opens up the AddWindow when add song is clicked in the MainWindow
    */
    @FXML
    private void clickNewSong(ActionEvent event) throws IOException
    {
        Stage myNewStage = new Stage(); // new window
        myNewStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(
            getClass().getResource("AddWindow.fxml"));
        
        Parent root = fxLoader.load();
        
        AddWindowController owc = fxLoader.getController();
        owc.setParentWindowController(this);
        
        Scene scene = new Scene(root);
        
        myNewStage.setScene(scene);
        myNewStage.showAndWait();
        
    }

    @FXML
    private void clickEditSong(ActionEvent event)
    {
        System.out.println("edit song, open up song editor with this info filled in");
    }

    @FXML
    private void clickDeleteSong(ActionEvent event)
    {
        System.out.println("delete song from everywhere except database");
    }

    @FXML
    private void clickSearch(ActionEvent event)
    {
        System.out.println("search the title/artist for this song");
    }

    @FXML
    private void clickAddSong(ActionEvent event)
    {
        System.out.println("add the selected song to a playlist");
    }
    
  //        public String getName()
//    {
//        return txtName.getText();
//    }
    
     //txtName.setText(parent.getName()); want to add a playlist (Array?) with a name of the playlist
}
