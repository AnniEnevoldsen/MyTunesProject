/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

/**
 *
 * @author Jesper
 */
public class MainWindowController implements Initializable {

    JFXPanel fxPanel = new JFXPanel();
    private Media media;
    private MediaPlayer player;
    private MediaView mediaView;
    private Model model = new Model();
    private ConnectionManager cm = new ConnectionManager();
    private Playlists playlists = new Playlists();
   

    @FXML
    private Button btnNewPlaylist;
    @FXML
    private Button btnEditPlaylist;
    @FXML
    private Button btnDeletePlaylist;
    @FXML
    private Button btnUp;
    @FXML
    private Button btnDown;
    @FXML
    private Button btnPlaylistDelete;
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
    @FXML
    private Button btnLoadSongs;
    @FXML
    private TableView<Playlists> lstPlaylists;
    @FXML
    private TableColumn<Playlists, String> PlaylistView;
    @FXML
    private TableView<Playlist> lstSongsInPlaylist;
    @FXML
    private TableColumn<Playlist, String> titleView;
    @FXML
    private TableView<Songs> lstSongs;
    @FXML
    private TableColumn<Songs, String> columnTitle;
    @FXML
    private TableColumn<Songs, String> columnArtist;
    @FXML
    private TableColumn<Songs, String> columnGenre;
    @FXML
    private TableColumn<Songs, String> columnTime;
    @FXML
    private TableColumn<Songs, String> columnFileLocation;
    private Songs songPlaying;
    private Playlist playlistSongPlaying;
    private String lastFocus = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PlaylistView.setCellValueFactory(
                new PropertyValueFactory("name"));

        titleView.setCellValueFactory(
                new PropertyValueFactory("songsTitle"));

        columnTitle.setCellValueFactory(
                new PropertyValueFactory("title"));
        columnArtist.setCellValueFactory(
                new PropertyValueFactory("artist"));
        columnGenre.setCellValueFactory(
                new PropertyValueFactory("genre"));
        columnTime.setCellValueFactory(
                new PropertyValueFactory("time"));
        columnFileLocation.setCellValueFactory(
                new PropertyValueFactory("fileLocation"));
        
        model.loadAll();
        model.loadAllP();

        lstSongs.setItems(model.getSongsList());
        lstPlaylists.setItems(model.getPlaylistsList());
        lstSongsInPlaylist.setItems(model.getSongsInPlaylistList());

        lstPlaylists.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Playlists>() {
            @Override
            public void changed(ObservableValue<? extends Playlists> observable, Playlists oldValue, Playlists newValue) {
                model.loadAllSP(newValue.getId());
            }
        }
        );

        lstSongs.focusedProperty().addListener(
                new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                lastFocus = "Songs";
            }

        }
        );

        lstSongsInPlaylist.focusedProperty().addListener(
                new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                lastFocus = "Playlist";
            }

        }
        );

    }


    /**
     * Return the information from the song you selected from the song list (Or right table).
     * @return 
     * 
     */
    protected Songs getSelectedSong() {
        return lstSongs.getSelectionModel().getSelectedItem();
    }

    /**
     * Return the information from the song inside a playlist you 
     * selected previously.
     * @return 
     */
    protected Playlist getSelectedSongInPlaylist() {
        return lstSongsInPlaylist.getSelectionModel().getSelectedItem();
    }

    /**
     * Return the information of the playlist you selected.
     * @return 
     */
    protected Playlists getSelectedPlaylist() {
        return lstPlaylists.getSelectionModel().getSelectedItem();
    }
    
    /**
     * This method runs a lot of methods which make possible
     * listen music from the table you prefer.
     * @param event 
     */
    @FXML
    private void clickPlay(ActionEvent event) {
        if (lastFocus.equals("Songs")) {
            playSongFromSongList();

        } else if (lastFocus.equals("Playlist")) {
            playSongFromPlaylist();
        } else {
            System.out.println("You are not able to play nothing yet.");
        }
        volumeControl();
    }

    /**
     * Will check if the player exists or not and compare if the song which is
     * playing is the same as the song you currently selected. In case the 
     * player does not exist, will ask to "stopOrPlayNewMusic" to play song.
     */
    private void playSongFromSongList() {

        if (player != null && getSelectedSong().equals(songPlaying)) {
            playOrPause();
        } else {
            stopOrPlayNewMusicB();
        }
        songPlaying = getSelectedSong();
    }

    /**
     * The same as "playSongFromSongList". The unique difference is this method
     * runs when the song is choosen from inside a playlist, if not, will run
     * the previous one.
     */
    private void playSongFromPlaylist() {
        if (player != null && getSelectedSongInPlaylist().equals(playlistSongPlaying)) {
            playOrPause();
        } else {
            stopOrPlayNewMusic();
        }
        playlistSongPlaying = getSelectedSongInPlaylist();
    }

    /**
     * Will check if the player is currently playing.
     *
     */
    private void playOrPause() {
        if (player.isAutoPlay()) {

            setThePauseButton();
        } else {

            setThePlayButton();
        }

    }

    /**
     * Will set the play button with the player on. 
     * It modifies a bit of the layout.
     */
    private void setThePlayButton() {
        player.setAutoPlay(true);
        playPane.setOpacity(0);
        pausePane.setOpacity(1);
    }

    /**
     * Will set the pause button with the player paused.
     * It modifies a bit of the layout.
     */
    private void setThePauseButton() {
        player.pause();
        player.setAutoPlay(false);
        playPane.setOpacity(1);
        pausePane.setOpacity(0);
    }

    /**
     *  This method will stop the currently music or will play a new one.
     *  Here also is where the magic occurs, everytime the song reachs the end,
     *  the following song will be called automatically.
     */
    private void stopOrPlayNewMusic() {
        if (player != null) {
            player.stop();
        }
        playerMediaPlayer(lstSongsInPlaylist.getSelectionModel().getSelectedItem().getSongsFileLocation());
        setThePlayButton();

        int selectedSongFromPlaylistIndex = lstSongsInPlaylist.getSelectionModel().getSelectedIndex();
        int songPlaylistSize = lstSongsInPlaylist.getItems().size();
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                for (int i = selectedSongFromPlaylistIndex; i < songPlaylistSize; i++) {
                    lstSongsInPlaylist.getSelectionModel().select(i);
                    playSongFromPlaylist();
                }
            }
        });
    }
    /**
     * Exactly the same as "stopOrPlayNewMusic". The unique difference is this 
     * method runs the songs from the songlist(Or right table).
     */
    private void stopOrPlayNewMusicB() {
        if (player != null) {
            player.stop();

        }
        playerMediaPlayer(lstSongs.getSelectionModel().getSelectedItem().getFileLocation());

        setThePlayButton();

        int selectedSongIndex = lstSongs.getSelectionModel().getSelectedIndex();
        int songListSize = lstSongs.getItems().size();
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                for (int i = selectedSongIndex; i < songListSize; i++) {
                    lstSongs.getSelectionModel().select(i);
                    playSongFromSongList();
                }
            }
        });
    }

    /**
     *???
     * @param songLocation
     */
    private void playerMediaPlayer(String songLocation) {

        media = new Media(new File(songLocation).toURI().toString());

        player = new MediaPlayer(media);
        mediaView = new MediaView(player);

    }

    
    /** 
     * It set the volume control.
     */
    private void volumeControl() {
        volumeControl.setValue(player.getVolume() * 100);
        volumeControl.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                player.setVolume(volumeControl.getValue() / 100);
            }
        });
    }

    
    /** 
     * Will create a new playlist.
     * @param event 
     * @throws IOException 
     */
    @FXML
    private void clickNewPlaylist(ActionEvent event) throws IOException {
        Stage newWindow = new Stage();

        newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("NewPlaylist.fxml"));

        Parent root = fxLoader.load();

        NewPlaylistController controller = fxLoader.getController();
        controller.setParentWindowController(this, null);

        Scene scene = new Scene(root);
        newWindow.setTitle("Add a Playlist");
        newWindow.setScene(scene);
        newWindow.showAndWait();
        
        model.loadAllP();
    }

    /**
     * Will show you the option to edit a playlist.
     * @param event 
     * @throws IOException
     */
    @FXML
    private void clickEditPlaylist(ActionEvent event) throws IOException {
        getSelectedPlaylist();
        Stage newWindow = new Stage();

        newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("NewPlaylist.fxml"));

        Parent root = fxLoader.load();

        NewPlaylistController controller = fxLoader.getController();
        controller.setParentWindowController(this, getSelectedPlaylist());

        Scene scene = new Scene(root);
        newWindow.setTitle("Edit Playlist");
        newWindow.setScene(scene);
        newWindow.showAndWait();
        
        model.loadAllP();
    }

    /**
     * Delete method for deleting a playlist.
     * @param event 
     * @throws IOException
     */
    @FXML
    private void clickDeletePlaylist(ActionEvent event) throws IOException {
        Stage newWindow = new Stage();

        newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("DeletePlaylistWindow.fxml"));

        Parent root = fxLoader.load();

        DeletePlaylistWindowController controller = fxLoader.getController();
        controller.setParentWindowController(this);

        Scene scene = new Scene(root);
        newWindow.setTitle("Delete");
        newWindow.setScene(scene);
        newWindow.showAndWait();
        
        model.loadAllP();
    }


    /**
     * moves a song up, due to the - 1 behind
     * @param event
     * @throws SQLException 
     * selectedSongIndex.
     */
    @FXML
    private void clickUp(ActionEvent event) throws SQLException {
        int selectedPlaylistOrder = lstSongsInPlaylist.getSelectionModel().getSelectedItem().getPlaylistOrder();
        int selectedPlaylistId = lstSongsInPlaylist.getSelectionModel().getSelectedItem().getId();
        int selectedSongIndex = lstSongsInPlaylist.getSelectionModel().getSelectedIndex();

        lstSongsInPlaylist.getSelectionModel().select(selectedSongIndex - 1);

        int selectedNewPlaylistOrder = lstSongsInPlaylist.getSelectionModel().getSelectedItem().getPlaylistOrder();
        int selectedNewPlaylistId = lstSongsInPlaylist.getSelectionModel().getSelectedItem().getId();

        model.moveSong(selectedPlaylistOrder, selectedPlaylistId, selectedNewPlaylistOrder, selectedNewPlaylistId);

        Playlists selectedPlaylist = lstPlaylists.getSelectionModel().getSelectedItem();
        model.loadAllSP(selectedPlaylist.getId());
    }

    /**
     * moves a song down, due to the + 1 behind
     * @param event
     * @throws SQLException 
     * selectedSongIndex.
     */
    @FXML
    private void clickDown(ActionEvent event) throws SQLException {
        int selectedPlaylistOrder = lstSongsInPlaylist.getSelectionModel().getSelectedItem().getPlaylistOrder();
        int selectedPlaylistId = lstSongsInPlaylist.getSelectionModel().getSelectedItem().getId();
        int selectedSongIndex = lstSongsInPlaylist.getSelectionModel().getSelectedIndex();

        lstSongsInPlaylist.getSelectionModel().select(selectedSongIndex + 1);

        int selectedNewPlaylistOrder = lstSongsInPlaylist.getSelectionModel().getSelectedItem().getPlaylistOrder();
        int selectedNewPlaylistId = lstSongsInPlaylist.getSelectionModel().getSelectedItem().getId();

        model.moveSong(selectedPlaylistOrder, selectedPlaylistId, selectedNewPlaylistOrder, selectedNewPlaylistId);

        Playlists selectedPlaylist = lstPlaylists.getSelectionModel().getSelectedItem();
        model.loadAllSP(selectedPlaylist.getId());
    }

    /**
     * Will delete a song inside a playlist.
     * @param event 
     * @throws IOException
     */
    @FXML
    private void clickPlaylistDelete(ActionEvent event) throws IOException {
        Stage newWindow = new Stage();

        newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("DeleteSongInPlaylistWindow.fxml"));

        Parent root = fxLoader.load();

        DeleteSongsInPlaylistWindowController controller = fxLoader.getController();
        controller.setParentWindowController(this);

        Scene scene = new Scene(root);
        newWindow.setTitle("Delete");
        newWindow.setScene(scene);
        newWindow.showAndWait();

        Playlists selectedPlaylists = lstPlaylists.getSelectionModel().getSelectedItem();
        model.loadAllSP(selectedPlaylists.getId());
    }

    /**
     * Able to add a new song from local path.
     * @param event 
     * @throws IOException
     */
    @FXML
    private void clickNewSong(ActionEvent event) throws IOException {
        Stage newWindow = new Stage();

        newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AddWindow.fxml"));

        Parent root = fxLoader.load();

        AddWindowController controller = fxLoader.getController();
        controller.setParentWindowController(this, null);

        Scene scene = new Scene(root);

        newWindow.setTitle("Add a Song");
        newWindow.setScene(scene);
        newWindow.showAndWait();
        
        model.loadAll();
    }

    /**
     * Able to edit whatever song you want.
     * @param event 
     * @throws IOException
     */
    @FXML
    private void clickEditSong(ActionEvent event) throws IOException {
        getSelectedSong();
        Stage newWindow = new Stage();

        newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AddWindow.fxml"));

        Parent root = fxLoader.load();

        AddWindowController controller = fxLoader.getController();
        controller.setParentWindowController(this, getSelectedSong());
        
        Scene scene = new Scene(root);
        newWindow.setTitle("Edit Song");
        newWindow.setScene(scene);
        newWindow.showAndWait();

        model.loadAll();
    }

    /**
     * Delete method to songs.
     * @param event 
     * @throws IOException
     */
    @FXML
    private void clickDeleteSong(ActionEvent event) throws IOException {
        Stage newWindow = new Stage();

        newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("DeleteWindow.fxml"));

        Parent root = fxLoader.load();

        DeleteWindowController controller = fxLoader.getController();
        controller.setParentWindowController(this);

        Scene scene = new Scene(root);
        newWindow.setTitle("Delete");
        newWindow.setScene(scene);
        newWindow.showAndWait();
        
        model.loadAll();
    }

    /**
     * Adds a song to the Playlist table in the db.
     * @param event 
     */
    @FXML
    private void clickAddSong(ActionEvent event) {
        model.addSongToPlaylist(lstPlaylists.getSelectionModel().getSelectedItem(), lstSongs.getSelectionModel().getSelectedItem());

        Playlists selectedPlaylist = lstPlaylists.getSelectionModel().getSelectedItem();
        model.loadAllSP(selectedPlaylist.getId());
    }
    
    /**
     * Search for title or artist
     */
    @FXML
    private void clickSearch(ActionEvent event) {
        model.search(txtSearch.getText(), txtSearch.getText());
        System.out.println("Searching for song or artist");
        if (((Button) event.getSource()).getText().equals("Search")) {
            setClearButton();
        } else {
            setSearchButton();
        }
    }
    
    /**
     * Sets the text on btnSearch to Clear
     */
     private void setClearButton(){
         btnSearch.setText("Clear");
     }
     
   /**
    * Sets the text on btnSearch to Search, clears txtSearch and loads all songs.
    */
    private void setSearchButton() {		
         btnSearch.setText("Search");		
         txtSearch.clear();		
         model.loadAll();		
      }
    
    /**
     * Will play the following song in the list when you click it.
     * @param event 
     */
    @FXML
    private void clickForw(ActionEvent event) {
        if (player != null) {
            if (lastFocus.equals("Songs")) {
                int selectedSongIndexB = lstSongs.getSelectionModel().getSelectedIndex();
                lstSongs.getSelectionModel().select(selectedSongIndexB + 1);
                playSongFromSongList();
            } else if (lastFocus.equals("Playlist")) {
                int selectedSongFromPlaylistIndexB = lstSongsInPlaylist.getSelectionModel().getSelectedIndex();
                lstSongsInPlaylist.getSelectionModel().select(selectedSongFromPlaylistIndexB + 1);
                playSongFromPlaylist();
            }
        }
    }
    
    /**
     * Will play the previous song in the list when you click it.
     * @param event 
     */
    @FXML
    private void clickBack(ActionEvent event) {
        if (player != null) {
            if (lastFocus.equals("Songs")) {
                int selectedSongIndexB = lstSongs.getSelectionModel().getSelectedIndex();
                lstSongs.getSelectionModel().select(selectedSongIndexB - 1);
                playSongFromSongList();
            } else if (lastFocus.equals("Playlist")) {
                int selectedSongFromPlaylistIndexB = lstSongsInPlaylist.getSelectionModel().getSelectedIndex();
                lstSongsInPlaylist.getSelectionModel().select(selectedSongFromPlaylistIndexB - 1);
                playSongFromPlaylist();
            }
        }
    }
    
/**
 * loads song to the lstSongs
 * @param event 
 */
    @FXML
    private void clickLoadSDB(ActionEvent event) {
        model.loadAll();
    }
    
/**
 * loads playlists to the lstPlaylists
 * @param event 
 */
    @FXML
    private void clickLoadPDB(ActionEvent event) {
        model.loadAllP();
    }
}
