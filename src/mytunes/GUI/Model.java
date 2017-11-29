/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.BE.Songs;
import mytunes.BLL.BLLManager;

/**
 *
 * @author Jesper
 */
public class Model
{
    private BLLManager BLLManager = new BLLManager();
    
    private ObservableList<Songs> lstSongs = FXCollections.observableArrayList();

    public ObservableList<Songs> getPrisonersList() {
        return lstSongs;
    }
    
    public void add(Songs songs) {
        BLLManager.add(songs);
        lstSongs.add(songs);
    }
    
    public void loadAll() {
        lstSongs.clear();
        lstSongs.addAll(BLLManager.getAllSongs());
    }
}
