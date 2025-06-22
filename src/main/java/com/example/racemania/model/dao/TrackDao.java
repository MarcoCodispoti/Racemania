package com.example.racemania.model.dao;


import com.example.racemania.exceptions.FailedResearchException;
import com.example.racemania.model.Track;
import com.example.racemania.model.service.Connector;
import com.example.racemania.model.service.Query;
import com.example.racemania.model.bean.TrackBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TrackDao {
    public List<Track> findTracks() throws FailedResearchException {
        Statement stmt = null;
        Connection conn; // = null;
        List<Track> tracks = new ArrayList<>();

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findTracksq();
            while (rs.next()) {
                tracks.add(extractTracks(rs));
                System.out.println("Ciao");  //dummy
            }
        } catch (Exception e) {
            throw new FailedResearchException("An error during research occurred.");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                //not handled
            }
        }
        return tracks;
    }

    private Track extractTracks(ResultSet rs) throws SQLException {
        return new Track(
                rs.getInt("track_id"),
                rs.getString("trackname"),
                rs.getInt("length"),
                rs.getString("place"),
                rs.getInt("owner_id"),
                rs.getInt("lap_price"),
                rs.getInt("daily_price"));
    }

//    public Track getTrackFromDB(int trackId) throws SQLException {
//        Track track = null;
//
//        try {
//            ResultSet rs = Query.findTrackInfo(trackId);
//            while(rs.next()) {
//                track = extractTracks(rs);
//            }
//        }
//        catch (SQLException e) {
//            throw new SQLException();
//        }
//        return track;
//    }

    public TrackBean getTrackFromDB(int trackId) throws SQLException {
        TrackBean trackBean = null;

        try {
            ResultSet rs = Query.findTrackInfo(trackId);
            while(rs.next()) {
                trackBean = fillBean(rs);
            }
        }
        catch (SQLException e) {
            throw new SQLException();
        }
        return trackBean;
    }

    public TrackBean fillBean(ResultSet rs) throws SQLException{
        TrackBean trackBean = new TrackBean();
        trackBean.setName(rs.getString("trackname"));
        trackBean.setLenght(rs.getInt("length"));
        trackBean.setPlace(rs.getString("place"));
        trackBean.setOwnerId(rs.getInt("owner_id"));
        trackBean.setLapPrice(rs.getInt("lap_price"));
        trackBean.setDailyPrice(rs.getInt("daily_price"));
        return trackBean;
    }


}

