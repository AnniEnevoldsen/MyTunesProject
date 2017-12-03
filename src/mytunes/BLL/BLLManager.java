/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BLL;

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

    public List<Songs> getAllSongs()
    {
        return dalm.getAllSongs();
    }
    
    public List<Playlists> getAllPlaylists()
    {
        return dalm.getAllPlaylists();
    }
    
    public List<Playlist> getAllSongsInPlaylist(int playlists_id)
    {
        return dalm.getAllSongsInPlaylist(playlists_id);
    }
    
    public List<Songs> getAllSongsByTitle(String title)
    {
        return dalm.getAllSongsByTitle(title);
    }

    public void add(Songs songs)
    {
        dalm.add(songs);
    }
    
    public void addP(Playlists playlists)
    {
        dalm.addP(playlists);
    }

    public void remove(Songs selectedSongs)
    {
        dalm.remove(selectedSongs);
    }
    
        public void removeP(Playlists selectedPlaylists)
    {
        dalm.removeP(selectedPlaylists);
    }
        
    public void removeFromPlaylist(Playlist selectedPlaylist)
    {
        dalm.removeFromPlaylist(selectedPlaylist);
    }

    public void editSongs(Songs songs)
    {
        dalm.editSongs(songs);
    }
    
    public void editPlaylists(Playlists playlists) 
    {
        dalm.editPlaylists(playlists);
    }
    
}
