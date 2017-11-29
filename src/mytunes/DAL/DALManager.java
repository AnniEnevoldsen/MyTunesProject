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
import mytunes.BE.Songs;

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
                //songs.setId(rs.getInt("id"));
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
    

        public List<Songs> getAllSongsByTitle(
            String title) {

        List<Songs> allSongs = new ArrayList();

        try (Connection con = cm.getConnection()) {
            // No good when having userinput, because SQL injection
//            Statement stmt = con.createStatement();

            String query
                    = "SELECT * FROM Songs "
                    + "WHERE title LIKE ? "
                    + "ORDER BY id";

            // Protect against SQL injection
            PreparedStatement pstmt
                    = con.prepareStatement(query);
            pstmt.setString(1, "%" + title + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Songs s = new Songs();
                s.setId(rs.getInt("id"));
                s.setTitle(rs.getString("title"));
                s.setArtist(rs.getString("artist"));
                s.setGenre(rs.getString("genre"));
                s.setTime(rs.getString("time"));
                s.setFileLocation(rs.getString("fileLocation"));


                allSongs.add(s);
            }
        }
        catch (SQLException ex) {
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
            
            PreparedStatement pstmt
                    = con.prepareStatement(
                            sql, Statement.RETURN_GENERATED_KEYS);
            
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
    
    //songs or song?
    public void remove(Songs selectedSong) {
        try (Connection con = cm.getConnection()) {
            String sql
                    = "DELETE FROM Songs WHERE id=?";
            PreparedStatement pstmt
                    = con.prepareStatement(sql);
            pstmt.setInt(1, selectedSong.getId());
            pstmt.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    public void update(Songs songs) {
        try (Connection con = cm.getConnection()) {
            String sql
                    = "UPDATE Songs SET "
                    + "title=?, artist=?, genre=?, time=?, fileLocation=? "
                    + "WHERE id=?";
            PreparedStatement pstmt
                    = con.prepareStatement(sql);
            pstmt.setString(1, songs.getTitle());
            pstmt.setString(2, songs.getArtist());
            pstmt.setString(3, songs.getGenre());
            pstmt.setString(4, songs.getTime());
            pstmt.setString(5, songs.getFileLocation());
            pstmt.setInt(6, songs.getId());

            int affected = pstmt.executeUpdate();
            if (affected<1)
                throw new SQLException("Song could not be updated");

        }
        catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    
}
