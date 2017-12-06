/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mytunes.BE.Playlists;

/**
 * FXML Controller class
 *
 * @author Jesper
 */
public class NewPlaylistController implements Initializable
{

    @FXML
    private TextField txtName;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    private MainWindowController parent;
    private Model model = new Model();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void clickSave(ActionEvent event)
    {
        Playlists p = new Playlists();
        p.setId(-1);
        p.setName(txtName.getText());

        model.addP(p);

        Stage window = (Stage) btnCancel.getScene().getWindow();
        window.close();
    }

    @FXML
    private void clickCancel(ActionEvent event)
    {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void setParentWindowController(MainWindowController parent)
    {
        this.parent = parent;
        //still an error here
        //txtName.setText(parent.getSelectedPlaylist().getName());
    }

}
