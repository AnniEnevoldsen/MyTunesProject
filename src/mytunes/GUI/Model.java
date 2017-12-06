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

    /**
     * Gets the songs in the table Songs from db.
     */
    public ObservableList<Songs> getSongsList()
    {
        return sList;
    }

    /**
     * Gets the songs in the table Playlists from db.
     */
    public ObservableList<Playlists> getPlaylistsList()
    {
        return pList;
    }

    /**
     * Gets the songs in the table Playlist from db.
     */
    public ObservableList<Playlist> getSongsInPlaylistList()
    {
        return spList;
    }

    /**
     * Cleas the lstSongs and loads the songs in Songs to lstSongs.
     */
    public void loadAll()
    {
        sList.clear();
        sList.addAll(bllm.getAllSongs());
    }

    /**
     * Clears the lstPlaylists and loads the songs in Playlists to lstPlaylists.
     */
    public void loadAllP()
    {
        pList.clear();
        pList.addAll(bllm.getAllPlaylists());
    }

    /**
     * Clears the lstSongsInPlaylist and loads all songs in Playlist to lstSongsInPlaylist
     */
    public void loadAllSP(int playlists_id)
    {
        spList.clear();
        spList.addAll(bllm.getAllSongsInPlaylist(playlists_id));
    }

    /**
     * Adds a song to Playlist in db and to list lstSongsInPlaylist.
     */
    public void addSongToPlaylist(Playlists selectedPlaylists, Songs selectedSongs)
    {
        bllm.addSongToPlaylist(selectedPlaylists, selectedSongs);
    }

    /**
     * Adds a song to Songs in db and to list lstSongs.
     */
    public void add(Songs songs)
    {
        bllm.add(songs);
        sList.add(songs);
    }

    /**
     * Adds a playlist to Playlists in db and to list lstPlaylists.
     */
    public void addP(Playlists playlists)
    {
        bllm.addP(playlists);
        pList.add(playlists);
    }

    /**
     * Removes song from Songs in db and from list in lstSongs.
     */
    public void remove(Songs selectedSongs)
    {
        bllm.remove(selectedSongs);
        sList.remove(selectedSongs);
    }

    /**
     * Removes playlist from Playlists in db and from list in lstPlaylist. 
     */
    public void removeP(Playlists selectedPlaylists)
    {
        bllm.removeP(selectedPlaylists);
        pList.remove(selectedPlaylists);
    }

    /**
     * Removes song from Playlist in db and from list in lstSongsInPlaylist
     */
    public void removeSP(Playlist selectedPlaylist)
    {
        bllm.removeSP(selectedPlaylist);
        spList.remove(selectedPlaylist);
    }

    /*
     * Edits songs in db.
     */
    public void editSongs(Songs songs)
    {
        bllm.editSongs(songs);
    }

    /*
     * Edits name of playlist in db.
     */
    public void editPlaylists(Playlists playlists)
    {
        bllm.editPlaylists(playlists);
    }

    /**
     * Clears the list and searches in column title and artist for text in txtFilter.
     */
    public void search(String title, String artist)
    {
        sList.clear();
        sList.addAll(bllm.getAllSongsBySearching(title, artist));
    }
}
