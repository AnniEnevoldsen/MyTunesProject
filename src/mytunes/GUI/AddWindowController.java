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

    private MainWindowController parent;
    
    private Window stage;

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

        System.out.println("choose song from pathfinder");

        
//        final FileChooser fileChooser = new FileChooser();
//        
//        File song = fileChooser.showOpenDialog(stage);
//        if (song != null)
//        {
//            absolutePath = song.getAbsolutePath();
//        }
//        
//        txtFile.setText(absolutePath);
        
    }

    @FXML
    private void clickCancel(ActionEvent event)
    {

         Stage stage = (Stage) btnCancel.getScene().getWindow();
         stage.close();

    }

    @FXML
    private void clickSave(ActionEvent event)
    {
        System.out.println("save song");
    }

}
