/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BLL;

import java.util.List;
<<<<<<< HEAD
import mytunes.BE.Songs;
=======
>>>>>>> 8d14b5ac4957c8167fed9c1646ec1eb21814be03
import mytunes.DAL.DALManager;

/**
 *Should work once we get a database and the dalm works too, AEE
 * @author Anni
 */
public class BLLManager
{
<<<<<<< HEAD
    DALManager dalManager = new DALManager();
    
    public void add(Songs songs)
    {
        dalManager.add(songs);
    }
    
    public List<Songs> getAllSongs() {
        return dalManager.getAllSongs();
    }
=======
        DALManager dalm= new DALManager();
    
//    public List<Song> getAllSongs() {
//        return dalm.getAllSongs();
//    }
//    public List<Song> getAllSongsByTitle(
//             String title) {
//        return dalm.getAllSongsByTitle(title);
//    }        
//
//    public void remove(Song selectedSong) {
//        dalm.remove(selectedSong);
//    }
//    
//    public void add(Song song)
//    {
//        dalm.add(song);
//    }
//
//    public void update(Song song) {
//        dalm.update(song);
//    }
>>>>>>> 8d14b5ac4957c8167fed9c1646ec1eb21814be03
}
