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
import javafx.stage.Stage;
import mytunes.BE.Songs;

/**
 * FXML Controller class
 *
 * @author Anni
 */
public class DeleteWindowController implements Initializable {

    @FXML
    private Button btnCancel;
    private MainWindowController parent;
    @FXML
    private Button btnDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clickAcceptDelete(ActionEvent event) {
        Model model = new Model();

        this.parent = parent;
        Songs selected = parent.getSelectedSong();

        model.remove(selected);

        Stage window = (Stage) btnCancel.getScene().getWindow();
        window.close();
    }

    @FXML
    private void clickCancelDelete(ActionEvent event) {
        Stage window = (Stage) btnCancel.getScene().getWindow();
        window.close();
    }

    public void setParentWindowController(MainWindowController parent) {
        this.parent = parent;
    }

}
