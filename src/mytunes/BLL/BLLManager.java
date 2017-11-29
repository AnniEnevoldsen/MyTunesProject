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
    DALManager dalManager = new DALManager();
    
    public void add(Songs songs)
    {
        dalManager.add(songs);
    }
    
    public List<Songs> getAllSongs() {
        return dalManager.getAllSongs();
    }
}
