package com.stackroute.muzixApp.service;

import com.stackroute.muzixApp.domain.Track;
import com.stackroute.muzixApp.exceptions.TrackNotFoundException;
import com.stackroute.muzixApp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixApp.repository.TrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

//    @Autowired
    private TrackRepository trackRepository;

//    public TrackServiceImpl(){
//
//    }
//    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    //Save the tracks
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getTrackID())){
            throw new TrackAlreadyExistsException("Already Exists"+track.getTrackID());
        }
        Track tracks= trackRepository.save(track);
        return  tracks;
    }

    //Getting all Tracks method Implementation
    @Override
    public List<Track> getAllTracks() {
       return  trackRepository.findAll();
    }

    //Finding track by ID method implentation
    @Override
    public Track getTrackByID(int id) throws TrackNotFoundException {
        if(!trackRepository.existsById(id)){
            throw new TrackNotFoundException("Track Does Not Exist");
        }
        Track track= trackRepository.findById(id).get();

        return track;
    }

    //Updating Track method Implementation
    @Override
    public Track updateTrack(Track track) {
        Track updateTrack= trackRepository.save(track);
        return updateTrack;
    }

    //Deleting Track method Implementation
    @Override
    public boolean deleteByID(Track track)  {
        trackRepository.deleteById(track.getTrackID());
        return true;
    }

//    Finding Track method Implementation
//    @Override
//    public Track findTrackByName(String trackName)  {
//        return trackRepository.findByTrackName(trackName);
//    }
}
