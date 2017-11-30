/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BLL;

import java.util.List;
import mytunes.BE.Songs;
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

    public List<Songs> getAllSongsByTitle(String title)
    {
        return dalm.getAllSongsByTitle(title);
    }

    public void add(Songs songs)
    {
        dalm.add(songs);
    }
    
        public void addP(Songs songs)
    {
     //   dalm.addP(playlists);
    }

    public void remove(Songs selectedSongs)
    {
        dalm.remove(selectedSongs);
    }

    public void update(Songs songs)
    {
        dalm.update(songs);
    }
}
