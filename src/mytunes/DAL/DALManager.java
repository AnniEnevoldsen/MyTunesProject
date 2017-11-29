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
}
