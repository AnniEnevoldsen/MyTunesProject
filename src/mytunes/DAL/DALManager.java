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

/**
 * - we need to get the database up and running ppl
 * we also need to clean up the code
 * all the outcommented is search bar getAllSongsByTitle
 * also add, remove and update
 * @author Anni
 */
public class DALManager
{
//        private ConnectionManager cm = new ConnectionManager();
//
//    public List<Song> getAllSongsByTitle(
//            String title) {
//
//        List<Song> allSongs = new ArrayList();
//
//        try (Connection con = cm.getConnection()) {
//
//            String query
//                    = "SELECT * FROM Songs "
//                    + "WHERE title LIKE ? "
//                    + "ORDER BY id";
//
//            PreparedStatement pstmt
//                    = con.prepareStatement(query);
//            pstmt.setString(1, "%" + title + "%");
//
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                Song s = new Song();
//                s.setId(rs.getInt("id"));
//                s.setTitle(rs.getString("title"));
//                s.setArtist(rs.getString("artist"));
//                s.setGenre(rs.getString("genre"));
//                
//
//                allSongs.add(s);
//            }
//        }
//        catch (SQLException ex) {
//            Logger.getLogger(DALManager.class.getName()).log(
//                    Level.SEVERE, null, ex);
//        }
//        return allSongs;
//    }
//    
//     public List<Song> getAllSongs() {
//        List<Song> allSongs
//                = new ArrayList();
//
//        try (Connection con = cm.getConnection()) {
//            PreparedStatement stmt
//                    = con.prepareStatement("SELECT * FROM Songs");
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                Song s = new Song();
//                s.setId(rs.getInt("id")); 
//                s.setTitle(rs.getString("title"));
//                s.setArtist(rs.getString("artist"));
//                s.setGenre(rs.getString("genre"));
//
//                allSongs.add(s);
//            }
//        }
//        catch (SQLException ex) {
//            Logger.getLogger(DALManager.class.getName()).log(
//                    Level.SEVERE, null, ex);
//        }
//        return allSongs;
//    }
//     
//      public void remove(Song selectedSong) {
//        try (Connection con = cm.getConnection()) {
//            String sql
//                    = "DELETE FROM Songs WHERE id=?";
//            PreparedStatement pstmt
//                    = con.prepareStatement(sql);
//            pstmt.setInt(1, selectedSong.getId());
//            pstmt.execute();
//        }
//        catch (SQLException ex) {
//            Logger.getLogger(DALManager.class.getName()).log(
//                    Level.SEVERE, null, ex);
//        }
//    }
//      
//      public void add(Song song) {
//        try (Connection con = cm.getConnection()) {
//            String sql
//                    = "INSERT INTO Songs"
//                    + "(title, artist, genre) "
//                    + "VALUES(?,?,?)";
//            PreparedStatement pstmt
//                    = con.prepareStatement(
//                            sql, Statement.RETURN_GENERATED_KEYS);
//            pstmt.setString(1, song.getTitle());
//            pstmt.setString(2, song.getArtist());
//            pstmt.setString(3, song.getGenre());
//
//            int affected = pstmt.executeUpdate();
//            if (affected<1)
//                throw new SQLException("Song could not be added");
//
//            // Get database generated id
//            ResultSet rs = pstmt.getGeneratedKeys();
//            if (rs.next()) {
//                song.setId(rs.getInt(1));
//            }
//        }
//        catch (SQLException ex) {
//            Logger.getLogger(DALManager.class.getName()).log(
//                    Level.SEVERE, null, ex);
//        }
//    }
//      
//      //not sure if update is needed
//      public void update(Song song) {
//        try (Connection con = cm.getConnection()) {
//            String sql
//                    = "UPDATE Songs SET "
//                    + "title=?, artist=?, genre=?"
//                    + "WHERE id=?";
//            PreparedStatement pstmt
//                    = con.prepareStatement(sql);
//            pstmt.setString(1, song.getTitle());
//            pstmt.setString(2, song.getArtist());
//            pstmt.setString(3, song.getGenre());
//
//
//            int affected = pstmt.executeUpdate();
//            if (affected<1)
//                throw new SQLException("Songs could not be updated");
//
//        }
//        catch (SQLException ex) {
//            Logger.getLogger(DALManager.class.getName()).log(
//                    Level.SEVERE, null, ex);
//        }
//    }
      
}
