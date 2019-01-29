package com.stackroute.muzixApp.service;

import com.stackroute.muzixApp.domain.Track;
import com.stackroute.muzixApp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixApp.exceptions.TrackNotFoundException;

import java.util.List;

public interface TrackService {
    //Blueprint methods for track service to implement
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;
    public List<Track> getAllTracks();
    public Track getTrackByID(int trackId) throws TrackNotFoundException;
    public Track updateTrack(Track track);
    public boolean deleteByID(Track track);
//    public Track findTrackByName(String track) throws TrackNotFoundException;


}
