
package com.moviebooking.dao;

import com.moviebooking.model.MovieShow;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieShowDAO {

    // Get all movie shows
    public List<MovieShow> getAllMovieShows() {

        List<MovieShow> shows = new ArrayList<>();

        String query = "SELECT ms.id, ms.movie_id, ms.screen_id, ms.show_date, ms.show_time, "
                + "ms.price, ms.available_seats, "
                + "m.name AS movie_name, m.genre, m.language, "
                + "c.name AS cinema_name, s.screen_name "
                + "FROM movie_shows ms "
                + "JOIN movie m ON ms.movie_id = m.id "
                + "JOIN screen s ON ms.screen_id = s.id "
                + "JOIN cinema c ON s.cinema_id = c.id "
                + "ORDER BY ms.show_date, ms.show_time";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("Executing Movie Shows Query...");

            while (rs.next()) {

                MovieShow show = new MovieShow();

                show.setId(rs.getInt("id"));
                show.setMovieId(rs.getInt("movie_id"));
                show.setScreenId(rs.getInt("screen_id"));
                show.setShowDate(rs.getDate("show_date"));
                show.setShowTime(rs.getTime("show_time"));
                show.setPrice(rs.getDouble("price"));
                show.setAvailableSeats(rs.getInt("available_seats"));
                show.setMovieName(rs.getString("movie_name"));
                show.setGenre(rs.getString("genre"));
                show.setLanguage(rs.getString("language"));
                show.setCinemaName(rs.getString("cinema_name"));
                show.setScreenName(rs.getString("screen_name"));

                shows.add(show);
            }

            System.out.println("Total shows found: " + shows.size());

        } catch (Exception e) {
            System.out.println("Error in getAllMovieShows()");
            e.printStackTrace();
        }

        return shows;
    }

    // Get show by ID
    public MovieShow getMovieShowById(int showId) {

        MovieShow show = null;

        String query = "SELECT ms.id, ms.movie_id, ms.screen_id, ms.show_date, ms.show_time, "
                + "ms.price, ms.available_seats, "
                + "m.name AS movie_name, m.genre, m.language, "
                + "c.name AS cinema_name, s.screen_name "
                + "FROM movie_shows ms "
                + "JOIN movie m ON ms.movie_id = m.id "
                + "JOIN screen s ON ms.screen_id = s.id "
                + "JOIN cinema c ON s.cinema_id = c.id "
                + "WHERE ms.id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, showId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                show = new MovieShow();

                show.setId(rs.getInt("id"));
                show.setMovieId(rs.getInt("movie_id"));
                show.setScreenId(rs.getInt("screen_id"));
                show.setShowDate(rs.getDate("show_date"));
                show.setShowTime(rs.getTime("show_time"));
                show.setPrice(rs.getDouble("price"));
                show.setAvailableSeats(rs.getInt("available_seats"));
                show.setMovieName(rs.getString("movie_name"));
                show.setGenre(rs.getString("genre"));
                show.setLanguage(rs.getString("language"));
                show.setCinemaName(rs.getString("cinema_name"));
                show.setScreenName(rs.getString("screen_name"));
            }

        } catch (Exception e) {
            System.out.println("Error in getMovieShowById()");
            e.printStackTrace();
        }

        return show;
    }

    // Update seats after booking
    public boolean updateAvailableSeats(int showId, int seatsBooked) {

        String query = "UPDATE movie_shows "
                + "SET available_seats = available_seats - ? "
                + "WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, seatsBooked);
            pstmt.setInt(2, showId);

            int rows = pstmt.executeUpdate();

            System.out.println("Seats updated: " + rows);

            return rows > 0;

        } catch (Exception e) {
            System.out.println("Error in updateAvailableSeats()");
            e.printStackTrace();
        }

        return false;
    }
}
