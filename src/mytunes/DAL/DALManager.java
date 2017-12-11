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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.BE.Playlist;
import mytunes.BE.Playlists;
import mytunes.BE.Songs;

/**
 *
 * @author Jesper
 */
public class DALManager
{

    private ConnectionManager cm = new ConnectionManager();

    /**
     * Gets all songs in a list from the table called Songs in the db called Songs
     * via the ConnectionManager and with a prepared statement.
     */
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
    
    /**
     * 
     * Gets all playlists in a list, from the db Songs, table Playlists. 
     */
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
    
    /**
     * 
     * Gets each and every song in the Playlist table where the songs playlist_id is equal the selected playlists playlists_id.
     */
    public List<Playlist> getAllSongsInPlaylist(int playlists_id)
    {
        List<Playlist> allSongsInPlaylist = new ArrayList();

        try (Connection con = cm.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Playlist WHERE playlists_id = ? ORDER BY playlistOrder");

            stmt.setInt(1, playlists_id);

            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {
                Playlist p = new Playlist();
                p.setId(rs.getInt("id"));
                p.setPlaylistsId(rs.getInt("playlists_id"));
                p.setPlaylistOrder(rs.getInt("playlistOrder"));
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

    /**
     * 
     * This gets text from the txtFilter and uses it to search in the db for a song 
     * by songtitle and artist 
     * @param title
     * @param artist
     * @return 
     */
    public List<Songs> getAllSongsBySearching
        (
            String title, String artist)
        {

        List<Songs> allSongs = new ArrayList();

        try (Connection con = cm.getConnection())
        {

            String query
                    = "SELECT * FROM Songs "
                    + "WHERE title LIKE ? "
                    + "OR "
                    + "artist LIKE ? "
                    + "ORDER BY id ";

            PreparedStatement pstmt
                    = con.prepareStatement(query);
            pstmt.setString(1, "%" + title + "%");
            pstmt.setString(2, "%" + artist + "%");

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

    /**
     * 
     * This uses Playlists_id from the Playlists table, a random int for its ordering and 
     * Songs_title, Songs_artist, Songs_genre, Songs_time, Songs_fileLocation from the Songs table, and
     * adds it all to the Playlist table.
     */
    public void addSongToPlaylist(Playlists selectedPlaylists, Songs selectedSongs)
    {
        System.out.println("Adding song to playlist.");
        
        Random random = new Random();
        int r = random.nextInt(2147483647);

        try (Connection con = cm.getConnection()) {
            String sql = "INSERT INTO Playlist "
                    + "(Playlists_id, PlaylistOrder, Songs_title, Songs_artist, Songs_genre, Songs_time, Songs_fileLocation) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, selectedPlaylists.getId());
            pstmt.setInt(2, r);
            pstmt.setString(3, selectedSongs.getTitle());
            pstmt.setString(4, selectedSongs.getArtist());
            pstmt.setString(5, selectedSongs.getGenre());
            pstmt.setString(6, selectedSongs.getTime());
            pstmt.setString(7, selectedSongs.getFileLocation());

            int affected = pstmt.executeUpdate();
            if (affected < 1) {
                throw new SQLException("Song could not be added");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * Switches playlistOrder of two songs in the Playlist table.
     */
    public void moveSong(int selectedPlaylistOrder, int selectedPlaylistId,
            int selectedNewPlaylistOrder, int selectedNewPlaylistId) throws SQLException
    {
        try (Connection con = cm.getConnection())
        {
            String sql = "UPDATE Playlist"
                    + " SET playlistOrder = ?"
                    + " WHERE id = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, selectedNewPlaylistOrder);
            pstmt.setInt(2, selectedPlaylistId);

            int affected = pstmt.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Song could not be moved");
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        
        try (Connection con = cm.getConnection())
        {
            String sql = "UPDATE Playlist"
                    + " SET playlistOrder = ?"
                    + " WHERE id = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, selectedPlaylistOrder);
            pstmt.setInt(2, selectedNewPlaylistId);

            int affected = pstmt.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Song could not be moved");
            }
        }
    }
    
    /**
     * 
     * This adds a new song by inserting data into the Songs table in the database.
     */
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

    /**
     * 
     * This adds a new Playlist in the Playlists table in the database. 
     */
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

    /**
     * 
     * This removes a song from the table Songs.
     */
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

    /**
     * 
     * This removes a playlist from table Playlists in the database.
     */
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

    /**
     * 
     * This removes a song from a playlist, so it removes a field in the table Playlist. 
     */
    public void removeSP(Playlist selectedPlaylist)
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

    /**
     * 
     * This allows us to edit the song in the table Songs.
     */
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

    /**
     * 
     * This allows us to edit the name of the individual playlist in the Playlists table. 
     */
    public void editPlaylists(Playlists playlists)
    {
        try (Connection con = cm.getConnection())
        {
            String sql
                    = "UPDATE Playlists SET "
                    + "name=? "
                    + "WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, playlists.getName());
            pstmt.setInt(2, playlists.getId());

            int affected = pstmt.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Playlist could not be updated");
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    
}
