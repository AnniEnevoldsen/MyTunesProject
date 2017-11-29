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

    private BLLManager bllm = new BLLManager();

    private ObservableList<Songs> sList = FXCollections.observableArrayList();

    public ObservableList<Songs> getSongsList()
    {
        return sList;
    }

    public void loadAll()
    {
        sList.clear();
        sList.addAll(bllm.getAllSongs());
    }

    public void add(Songs songs)
    {
        bllm.add(songs);
        sList.add(songs);
    }

    public void remove(Songs selectedSongs)
    {
        bllm.remove(selectedSongs);
        sList.remove(selectedSongs);
    }

    public void update(Songs songs)
    {
        bllm.update(songs);
    }
}
