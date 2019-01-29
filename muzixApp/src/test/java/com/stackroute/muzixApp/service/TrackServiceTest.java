package com.stackroute.muzixApp.service;

import com.stackroute.muzixApp.domain.Track;
import com.stackroute.muzixApp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixApp.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class TrackServiceTest {
    private Track track,track1;

    @Mock
    private TrackRepository trackRepository;

    @InjectMocks
    private TrackServiceImpl trackService;
    List<Track> list,list1 = null;


    @Before
    public void setUp() throws Exception {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setTrackID(26);
        track.setTrackName("Jonny");
        track.setTrackComments("Hello");
        list = new ArrayList<>();
        list.add(track);

//        track1 = new Track();
//        track1.setTrackID(nullable());
//        track1.setTrackName("Jonny");
//        track1.setTrackComments("Hello");
//        list1 = new ArrayList<>();
//        list1.add(track1);
    }

    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistsException {

        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(track, savedTrack);

        //verify here verifies that trackRepository save method is only called once
        verify(trackRepository, times(1)).save(track);

    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveUserTestFailure() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track)any())).thenReturn(track);
        when(trackRepository.existsById(track.getTrackID())).thenReturn(true);
        Track savedTrack = trackService.saveTrack(track);
        System.out.println("savedTrack" + savedTrack);
        Assert.assertNotEquals(null, savedTrack);
    }

    @Test
    public void getAllTrackSuccess(){

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> tracklist = trackService.getAllTracks();
        Assert.assertEquals(list,tracklist);
    }

    public void getTrackByIDSuccess() {


    }
    @Test
    public void getAllTracksFailure()  {
        when(trackRepository.findAll()).thenReturn(list);
        when(trackRepository.existsById(1)).thenReturn(false);
        List<Track> test=trackService.getAllTracks();
        Assert.assertEquals(list,test);
    }

    @Test
    public void testDeleteTrackSuccess()  {
        trackService.deleteByID(track);
        verify(trackRepository,times(1)).deleteById(track.getTrackID());
    }

    @Test
    public void testDeleteTrackFailure() {
        when(trackRepository.existsById(track.getTrackID())).thenReturn(false);
        trackService.deleteByID(track);
    }

    @Test
    public void testUpdateTracksSuccess() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track)any())).thenReturn(track);
        Track tracktest=trackService.saveTrack(track);
        Assert.assertEquals(track,tracktest);
    }

//    @Test(expected = TrackNotFoundExeption.class)
//    public void testUpdateTracksFailure() throws TrackNotFoundExeption {
//        when(trackRepository.save((Track)any())).thenReturn(track);
//        when(trackRepository.existsById(track.getTrackid())).thenReturn(false);
//        Track track1= trackService.updateTrack(track.getTrackid(),track.getComments());
//        Assert.assertEquals(track,track1);
//    }
//
//}
}
