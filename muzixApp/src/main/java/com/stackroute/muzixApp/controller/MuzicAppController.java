package com.stackroute.muzixApp.controller;

import com.stackroute.muzixApp.domain.Track;
import com.stackroute.muzixApp.exceptions.TrackNotFoundException;
import com.stackroute.muzixApp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixApp.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class MuzicAppController {

    private TrackService trackService;

    //Set TrackService variable using trackcontroller constructor
    @Autowired
    public MuzicAppController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;

        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity("Successfully created", HttpStatus.CREATED);
        } catch (TrackAlreadyExistsException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("tracks")
    public ResponseEntity<?> getAllTracks() {
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
    }

    @DeleteMapping("track/{trackId}")
    public ResponseEntity<?> removeTrack(@PathVariable int trackId) throws TrackNotFoundException {
        ResponseEntity responseEntity;
            Track track1 = trackService.getTrackByID(trackId);
            trackService.deleteByID(track1);
            responseEntity = new ResponseEntity<String>("Track removed", HttpStatus.OK);

        return responseEntity;
    }

   //This method is used to get track by id on request
    @GetMapping("track/{trackId}")
    public ResponseEntity<?> getTrackById(@PathVariable int trackId) throws TrackNotFoundException {
        return new ResponseEntity<Track>(trackService.getTrackByID(trackId), HttpStatus.OK);
    }

    @PutMapping("track")
    public ResponseEntity<?> updateComment(@RequestBody Track track){
        return new ResponseEntity<Track>(trackService.updateTrack(track),HttpStatus.OK);
    }
}