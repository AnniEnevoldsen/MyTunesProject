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

/**
 * FXML Controller class
 *
 * @author Jesper
 */
public class NewPlaylistController implements Initializable
{
    private MainWindowController parent;
    @FXML
    private TextField txtName;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    MainWindowController mainController = new MainWindowController();

    
    //private MainWindowController parent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO

    }    
    
 // figure out set and get name methods
    public String getName()
    {
        return txtName.getText();
    }
    
    public void setName(){
    parent.lblName.setText(getName());
    }
    

    
    public void setParentWindowController(MainWindowController parent)
    {
        this.parent = parent;
    }

    @FXML

    private void clickSave(ActionEvent event)
    {

        parent.lblName.setText(getName()); //works for the label, now just need to do listview
        System.out.println("save playlist to list in MainWindow");
        
    }
    @FXML
    private void clickCancel(ActionEvent event)
    {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    
 
            

}
