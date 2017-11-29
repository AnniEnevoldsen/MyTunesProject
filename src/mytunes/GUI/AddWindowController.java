/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template song, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import mytunes.BE.Songs;

/**
 * FXML Controller class
 *
 * @author Jesper
 */
public class AddWindowController implements Initializable
{
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtArtist;
    @FXML
    private TextField txtGenre;
    @FXML
    private TextField txtTime;
    @FXML
    private TextField txtFile;
    @FXML
    private Button btnChoose;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtFileLocation;
    private MainWindowController parent;
    private Window stage;
    Model model = new Model();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    public void setParentWindowController(MainWindowController parent)
    {
        this.parent = parent;
    }

    @FXML
    private void clickChoose(ActionEvent event)
    {
        String absolutePath = null;
        
        final FileChooser fileChooser = new FileChooser();
        
        File song = fileChooser.showOpenDialog(stage);
        if (song != null)
        {
            absolutePath = song.getAbsolutePath();
        }
        
        txtFileLocation.setText(absolutePath);    
    }

    @FXML
    private void clickCancel(ActionEvent event)
    {
        Stage window = (Stage) btnCancel.getScene().getWindow();
        window.close();
    }

    @FXML
    private void clickSave(ActionEvent event)
    {
        
        
        Songs songs = new Songs();
        songs.setId(-1);
        songs.setTitle(txtTitle.getText());
        songs.setArtist(txtArtist.getText());
        songs.setGenre(txtGenre.getText());
        songs.setTime(txtTime.getText());
        songs.setFileLocation(txtFileLocation.getText());

        model.add(songs);
    
        Stage window = (Stage) btnCancel.getScene().getWindow();
        window.close();
    }

}
