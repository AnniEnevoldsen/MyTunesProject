/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Polygon;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mytunes.BE.Playlist;
import mytunes.BE.Playlists;

import mytunes.BE.Songs;
import mytunes.DAL.ConnectionManager;
import mytunes.DAL.DALManager;

/**
 *
 * @author Jesper
 */
public class MainWindowController implements Initializable
{

    private Label label;
    @FXML
    
    private ListView<Playlists> lstPlaylists;
    @FXML
    
    private Button btnNewPlaylist;
    @FXML
    private Button btnEditPlaylist;
    @FXML
    private Button btnDeletePlaylist;
    @FXML
    
    private ListView<Playlist> lstSongsInPlaylist;
    @FXML
    
    private Button btnUp;
    @FXML
    private Button btnDown;
    @FXML
    private Button btnPlaylistDelete;
    @FXML

    private ListView<Songs> lstSongs;
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
    @FXML
    private Slider volumeControl;
    @FXML
    private Polygon playPane;
    @FXML
    private Pane pausePane;

    JFXPanel fxPanel = new JFXPanel();
    String path = "song1.mp3";
    Media media = new Media(new File(path).toURI().toString());
    private MediaPlayer player = new MediaPlayer(media);
    MediaView mediaView = new MediaView(player);

    Model model = new Model();
    
    @FXML
    private Label currentlyPlaying;
    @FXML
    private Button btnLoadSongs;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        volumeControl();

        lstSongs.setItems(model.getSongsList());
        lstPlaylists.setItems(model.getPlaylistsList());
        lstSongsInPlaylist.setItems(model.getSongsInPlaylistList());

        lstSongs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
                {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue)
                    {
                        //fillTextFields();
                    }
                }
                );
        
        lstPlaylists.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
                {
                    int playlists_id = 1;
                    
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue)
                    {
                        model.loadAllSP(playlists_id);
                    }
                }
                );
    }

    @FXML
    public void clickPlay(ActionEvent event)
    {
        if (!player.isAutoPlay())
        {
            player.setAutoPlay(true);
            playPane.setOpacity(0);
            pausePane.setOpacity(1);
        } else
        {
            player.pause();
            player.setAutoPlay(false);
            playPane.setOpacity(1);
            pausePane.setOpacity(0);
        }
    }

    private void volumeControl()
    {
        volumeControl.setValue(player.getVolume() * 100);
        volumeControl.valueProperty().addListener(new InvalidationListener()
        {
            @Override
            public void invalidated(Observable observable)
            {
                player.setVolume(volumeControl.getValue() / 100);
            }
        });
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
        Playlists selectedPlaylists = lstPlaylists.getSelectionModel().getSelectedItem();

        model.removeP(selectedPlaylists);
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
        //make in dal
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
    private void clickEditSong(ActionEvent event) throws IOException
    {
        Stage newWindow = new Stage();

        //newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AddWindow.fxml"));

        Parent root = fxLoader.load();

        AddWindowController controller = fxLoader.getController();
        controller.setParentWindowController(this);

        Scene scene = new Scene(root);

        newWindow.setScene(scene);
        newWindow.showAndWait();
    }

    @FXML
    private void clickDeleteSong(ActionEvent event)
    {
        Songs selectedSongs = lstSongs.getSelectionModel().getSelectedItem();

        model.remove(selectedSongs);
    }

    @FXML
    private void clickSearch(ActionEvent event)
    {
        
    }

    
    private ConnectionManager cm = new ConnectionManager();
    
    @FXML
    private void clickAddSong(ActionEvent event)
    {
        System.out.println("Adding song to playlist.");
    
        Playlists selectedPlaylists = lstPlaylists.getSelectionModel().getSelectedItem();
        Songs selectedSongs = lstSongs.getSelectionModel().getSelectedItem();
        
        try (Connection con = cm.getConnection())
        {
            String sql = "INSERT INTO Playlist (Playlists_id, Songs_title, Songs_artist, Songs_genre, Songs_time, Songs_fileLocation) VALUES (?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, selectedPlaylists.getId());
            pstmt.setString(2, selectedSongs.getTitle());
            pstmt.setString(3, selectedSongs.getArtist());
            pstmt.setString(4, selectedSongs.getGenre());
            pstmt.setString(5, selectedSongs.getTime());
            pstmt.setString(6, selectedSongs.getFileLocation());

            pstmt.executeUpdate();
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    


    @FXML
    private void clickForw(ActionEvent event)
    {
    }

    @FXML
    private void clickBack(ActionEvent event)
    {
    }


    @FXML
    private void clickLoadSDB(ActionEvent event) {
        model.loadAll();
    }

    @FXML
    private void clickLoadPDB(ActionEvent event) {
        model.loadAllP();
    }

}
