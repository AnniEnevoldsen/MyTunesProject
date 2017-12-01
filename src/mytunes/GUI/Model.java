/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.BE.Playlist;
import mytunes.BE.Playlists;
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
    private ObservableList<Playlists> pList = FXCollections.observableArrayList();
    private ObservableList<Playlist> spList = FXCollections.observableArrayList();

    public ObservableList<Songs> getSongsList()
    {
        return sList;
    }
    
    public ObservableList<Playlists> getPlaylistsList()
    {
        return pList;
    }

    public ObservableList<Playlist> getSongsInPlaylistList()
    {
        return spList;
    }
    
    public void loadAll()
    {
        sList.clear();
        sList.addAll(bllm.getAllSongs());
    }
    
    public void loadAllP()
    {
        pList.clear();
        pList.addAll(bllm.getAllPlaylists());
    }

    public void loadAllSP(int playlists_id)
    {
        spList.clear();
        spList.addAll(bllm.getAllSongsInPlaylist(playlists_id));
    }

    
    public void add(Songs songs)
    {
        bllm.add(songs);
        sList.add(songs);
    }
    
        public void addP(Playlists playlists)
    {
        bllm.addP(playlists);
        pList.add(playlists);
    }
    

    public void remove(Songs selectedSongs)
    {
        bllm.remove(selectedSongs);
        sList.remove(selectedSongs);
    }

        public void removeP(Playlists selectedPlaylists)
    {
        bllm.removeP(selectedPlaylists);
        pList.remove(selectedPlaylists);
    }
    
    public void update(Songs songs)
    {
        bllm.update(songs);
    }
}
