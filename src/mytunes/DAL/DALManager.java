/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.BE.Playlist;
import mytunes.BE.Playlists;
import mytunes.BE.Songs;
import mytunes.GUI.MainWindowController;
import mytunes.GUI.NewPlaylistController;

/**
 *
 * @author Jesper
 */
public class DALManager
{
    
    private ConnectionManager cm = new ConnectionManager();
    
    public List<Songs> getAllSongs()
    {
        System.out.println("Getting all songs.");

        List<Songs> allSongs = new ArrayList();

        try (Connection con = cm.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Songs");
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                Songs songs = new Songs();
                songs.setId(rs.getInt("id"));
                songs.setTitle(rs.getString("title"));
                songs.setArtist(rs.getString("artist"));
                songs.setGenre(rs.getString("genre"));
                songs.setTime(rs.getString("time"));
                songs.setFileLocation(rs.getString("fileLocation"));

                allSongs.add(songs);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allSongs;
    }
       
    public List<Playlists> getAllPlaylists()
    {
        System.out.println("Getting all playlists.");

        List<Playlists> allPlaylists = new ArrayList();

        try (Connection con = cm.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Playlists");
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                Playlists p = new Playlists();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));

                allPlaylists.add(p);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allPlaylists;
    }
    
    public List<Playlist> getAllSongsInPlaylist(int playlists_id)
    {
        System.out.println("Getting all songs in playlist.");

        List<Playlist> allSongsInPlaylist = new ArrayList();

        try (Connection con = cm.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Playlist WHERE playlists_id = ?");

            stmt.setInt(1, playlists_id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                Playlist p = new Playlist();
                p.setId(rs.getInt("id"));
                p.setPlaylistsId(rs.getInt("playlists_id"));
                p.setSongsTitle(rs.getString("songs_title"));
                p.setSongsArtist(rs.getString("songs_artist"));
                p.setSongsGenre(rs.getString("songs_genre"));
                p.setSongsTime(rs.getString("songs_time"));
                p.setSongsFileLocation(rs.getString("songs_fileLocation"));

                allSongsInPlaylist.add(p);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allSongsInPlaylist;
    }
    
    public List<Songs> getAllSongsByTitle(
            String title)
    {

        List<Songs> allSongs = new ArrayList();

        try (Connection con = cm.getConnection())
        {
            
            String query
                    = "SELECT * FROM Songs "
                    + "WHERE title LIKE ? "
                    + "ORDER BY id";

            PreparedStatement pstmt
                    = con.prepareStatement(query);
            pstmt.setString(1, "%" + title + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                Songs s = new Songs();
                s.setId(rs.getInt("id"));
                s.setTitle(rs.getString("title"));
                s.setArtist(rs.getString("artist"));
                s.setGenre(rs.getString("genre"));
                s.setTime(rs.getString("time"));
                s.setFileLocation(rs.getString("fileLocation"));

                allSongs.add(s);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allSongs;

    }
    
    public void add(Songs songs)
    {
        System.out.println("Adding song to database.");

        try (Connection con = cm.getConnection())
        {
            String sql
                    = "INSERT INTO Songs"
                    + "(title, artist, genre, time, fileLocation) "
                    + "VALUES(?,?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, songs.getTitle());
            pstmt.setString(2, songs.getArtist());
            pstmt.setString(3, songs.getGenre());
            pstmt.setString(4, songs.getTime());
            pstmt.setString(5, songs.getFileLocation());

            int affected = pstmt.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Song could not be added");
            }

            // Get database generated id
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next())
            {
                songs.setId(rs.getInt(1));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

     public void addP(Playlists playlists)
    {
        System.out.println("Adding playlist to database.");

        try (Connection pcon = cm.getConnection())
        {
            String sql
                    = "INSERT INTO Playlists"
                    + "(name) "
                    + "VALUES(?)";

            PreparedStatement pstmt
                    = pcon.prepareStatement(
                            sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, playlists.getName());



            int affected = pstmt.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Playlist could not be added");
            }

            // Get database generated id
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next())
            {
                playlists.setId(rs.getInt(1));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
     
    public void remove(Songs selectedSongs)
    {
        System.out.println("Removing song");
        
        try (Connection con = cm.getConnection())
        {
            String sql = "DELETE FROM Songs WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, selectedSongs.getId());
            pstmt.execute();
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    
    public void removeP(Playlists selectedPlaylists)
    {
        System.out.println("Removing playlist");
        
        try (Connection con = cm.getConnection())
        {
            String sql = "DELETE FROM Playlists WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, selectedPlaylists.getId());
            pstmt.execute();
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    
        public void removeFromPlaylist(Playlist selectedPlaylist)
    {
        System.out.println("Removing song from playlist");
        
        try (Connection con = cm.getConnection())
        {
            String sql = "DELETE FROM Playlist WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, selectedPlaylist.getId());
            pstmt.execute();
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    
    public void editSongs(Songs songs)
    {
        try (Connection con = cm.getConnection())
        {
            String sql
                    = "UPDATE Songs SET "
                    + "title=?, artist=?, genre=?, time=?, fileLocation=? "
                    + "WHERE id=?";
            
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, songs.getTitle());
            pstmt.setString(2, songs.getArtist());
            pstmt.setString(3, songs.getGenre());
            pstmt.setString(4, songs.getTime());
            pstmt.setString(5, songs.getFileLocation());
            pstmt.setInt(6, songs.getId());

            int affected = pstmt.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Song could not be updated");
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    
    public void editPlaylists(Playlists playlists) {
        
        try (Connection con = cm.getConnection()) {
            String sql
                    = "UPDATE Playlists SET "
                    + "name=?"
                    + "WHERE id=?"; //maybe where id is selected?
            PreparedStatement pstmt
                    = con.prepareStatement(sql);
            pstmt.setString(1, playlists.getName());
            pstmt.setInt(2, playlists.getId());

            int affected = pstmt.executeUpdate();
            if (affected<1)
                throw new SQLException("Playlist could not be updated");

        }
        catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

      
    }
}
