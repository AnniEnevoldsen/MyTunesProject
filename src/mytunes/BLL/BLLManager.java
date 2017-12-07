/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BLL;

import java.sql.SQLException;
import java.util.List;
import mytunes.BE.Playlist;
import mytunes.BE.Songs;
import mytunes.BE.Playlists;
import mytunes.DAL.DALManager;

/**
 *
 * @author Jesper
 */
public class BLLManager
{

    DALManager dalm = new DALManager();
    
    /**
     * Gets all songs from the db.
     */
    public List<Songs> getAllSongs()
    {
        return dalm.getAllSongs();
    }
    
    /**
     * 
     * gets all playlists from the db.
     */
    public List<Playlists> getAllPlaylists()
    {
        return dalm.getAllPlaylists();
    }
    
    /**
     * 
     * gets all songs in each playlist.
     */
    public List<Playlist> getAllSongsInPlaylist(int playlists_id)
    {
        return dalm.getAllSongsInPlaylist(playlists_id);
    }
    
    /**
     * 
     * Gets all songs with artist/title like text in txtFilter.
     */
    public List<Songs> getAllSongsBySearching(String title, String artist)
    {
        return dalm.getAllSongsBySearching(title, artist);
    }

    /**
     * 
     * Adds a song to a playlist.
     */
    public void addSongToPlaylist(Playlists selectedPlaylists, Songs selectedSongs)
    {
        dalm.addSongToPlaylist(selectedPlaylists, selectedSongs);
    }
    
    /**
     * Moves a song eihter up or down in the Playlist depending on moveIndex.
     */
    public void moveSong(int selectedPlaylistOrder, int selectedPlaylistId,
            int selectedNewPlaylistOrder, int selectedNewPlaylistId) throws SQLException
    {
        dalm.moveSong(selectedPlaylistOrder, selectedPlaylistId, selectedNewPlaylistOrder, selectedNewPlaylistId);
    }
    
    /**
     * 
     * Adds song to db.
     */
    public void add(Songs songs)
    {
        dalm.add(songs);
    }
    
    /**
     * 
     * Adds playlist to db.
     */
    public void addP(Playlists playlists)
    {
        dalm.addP(playlists);
    }

    /**
     * 
     * Removes song from db.
     */
    public void remove(Songs selectedSongs)
    {
        dalm.remove(selectedSongs);
    }
    
    /**
     * 
     * Removes playlist from db. 
     */
        public void removeP(Playlists selectedPlaylists)
    {
        dalm.removeP(selectedPlaylists);
    }
        
    /**
     * 
     * Removes song from playlist.
     */    
    public void removeSP(Playlist selectedPlaylist)
    {
        dalm.removeSP(selectedPlaylist);
    }

    /**
     * 
     * Edits song in db.
     */
    public void editSongs(Songs songs)
    {
        dalm.editSongs(songs);
    }
    
    /**
     * 
     * Edits playlist name in db.
     */
    public void editPlaylists(Playlists playlists) 
    {
        dalm.editPlaylists(playlists);
    }
    
}
