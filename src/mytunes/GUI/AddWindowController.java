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
        Songs s = new Songs();
        s.setId(-1);
        s.setTitle(txtTitle.getText());
        s.setGenre(txtGenre.getText());
        s.setGenre(txtGenre.getText());
        s.setTime(txtTime.getText());
        s.setFileLocation(txtFileLocation.getText());

        model.add(s);
    
        Stage window = (Stage) btnSave.getScene().getWindow();
        window.close();
    }
    
    public void setParentWindowController(MainWindowController parent)
    {
        this.parent = parent;
        //this is making add do like edit. use if edit clicked fill, else do nothing
        txtTitle.setText(parent.getSelectedSong().getTitle());
        txtArtist.setText(parent.getSelectedSong().getTitle());
        txtGenre.setText(parent.getSelectedSong().getGenre());
        txtTime.setText(parent.getSelectedSong().getTime());       
        txtFileLocation.setText(parent.getSelectedSong().getFileLocation());
    }

}
