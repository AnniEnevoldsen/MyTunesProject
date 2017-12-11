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
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
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
public class MainWindowController implements Initializable {

    JFXPanel fxPanel = new JFXPanel();
    private Media media;
    private MediaPlayer player;
    private MediaView mediaView;
    private Model model = new Model();
    private ConnectionManager cm = new ConnectionManager();
    private Playlists playlists = new Playlists();
    private Songs sSong;

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

    /*
    * get methods
     */
    protected Songs getSelectedSong() {
        return lstSongs.getSelectionModel().getSelectedItem();
    }

    protected Playlist getSelectedSongInPlaylist() {
        return lstSongsInPlaylist.getSelectionModel().getSelectedItem();
    }

    protected Playlists getSelectedPlaylist() {
        return lstPlaylists.getSelectionModel().getSelectedItem();
    }

    /*
    * Play button methods
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
    }

    private void playSongFromSongList() {

        if (player != null && getSelectedSong().equals(songPlaying)) {
            playOrPause();
        } else {
            stopOrPlayNewMusicB();
        }
        songPlaying = getSelectedSong();
    }

    private void playSongFromPlaylist() {
        if (player != null && getSelectedSongInPlaylist().equals(playlistSongPlaying)) {
            playOrPause();
        } else {
            stopOrPlayNewMusic();
        }
        playlistSongPlaying = getSelectedSongInPlaylist();
    }

    private void playOrPause() {
        if (player.isAutoPlay()) {

            setPlayButton();
        } else {

            setPauseButton();
        }

    }

    private void setPauseButton() {
        player.setAutoPlay(true);
        playPane.setOpacity(0);
        pausePane.setOpacity(1);
        System.out.println(player.getTotalDuration().toSeconds());
    }

    private void setPlayButton() {
        player.pause();
        player.setAutoPlay(false);
        playPane.setOpacity(1);
        pausePane.setOpacity(0);
    }

    private void stopOrPlayNewMusic() {
        if (player != null) {
            player.stop();
        }
        playerMediaPlayer(lstSongsInPlaylist.getSelectionModel().getSelectedItem().getSongsFileLocation());
        volumeControl();
        setPauseButton();
    }

    private void stopOrPlayNewMusicB() {
        if (player != null) {
            player.stop();

        }
        playerMediaPlayer(lstSongs.getSelectionModel().getSelectedItem().getFileLocation());
        volumeControl();
        setPauseButton();
    }

    private void playerMediaPlayer(String songLocation) {

        media = new Media(new File(songLocation).toURI().toString());

        player = new MediaPlayer(media);
        mediaView = new MediaView(player);

    }
    /*
    * Volume method
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

    /*
    * Playlist methods
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
    }

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

    }

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
    }

    /*
    * Song methods
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

    }

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
    }

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
    }

    @FXML
    private void clickAddSong(ActionEvent event) {
        model.addSongToPlaylist(lstPlaylists.getSelectionModel().getSelectedItem(), lstSongs.getSelectionModel().getSelectedItem());

        Playlists selectedPlaylist = lstPlaylists.getSelectionModel().getSelectedItem();
        model.loadAllSP(selectedPlaylist.getId());
    }

    /*
    * Rest of methods
     */
    //evt get text method 
    @FXML
    private void clickSearch(ActionEvent event) {
        model.search(txtSearch.getText(), txtSearch.getText());
        System.out.println("Searching for song or artist");
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
