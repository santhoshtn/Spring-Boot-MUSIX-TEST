package com.stackroute.muzixApp.repository;

import com.stackroute.muzixApp.domain.Track;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrackRepositoryTestIT {
//
//
//    @Autowired
//    private TrackRepositoryTestIT(TrackRepository trackRepository) {
//        this.trackRepository = trackRepository;
//    }
//
    @Autowired
    private TrackRepository trackRepository;

    private Track track;

    @Before
    public void setUp(){
        track = new Track();
        track.setTrackID(55);
        track.setTrackName("all of me");
        track.setTrackComments("john legend");
    }
//
    @After
    public void tearDown(){
        track=null;
        trackRepository.deleteAll();
    }

    @Test
    public void testSaveTrackSuccess(){
        Track track123=new Track(55,"all of me","john legend");
        System.out.println(track123);
    Track track12=trackRepository.save(track123);
        System.out.println(track12);
    Assert.assertEquals(track,track12);
    }

    @Test
    public void testSaveTrackFailure(){
        Track testTrack = new Track(13,"Paani da rang","ayushmann");
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackID()).get();
        Assert.assertNotSame(testTrack,fetchTrack);
    }
    @Test
    public void testGetAllTracks(){
        Track u = new Track(13,"Jiyein kyun","papon");
        Track u1 = new Track(17,"Paani da rang","ayushmann");
        trackRepository.save(u);
        trackRepository.save(u1);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("Jiyein kyun",list.get(0).getTrackName());

    }

    @Test
    public void testGetTrackByIdSuccess(){
        Track track1 = new Track(13,"Paani da rang","ayushmann");
        trackRepository.save(track1);
        Track track2 = trackRepository.findById(track1.getTrackID()).get();
        Assert.assertEquals(track2,track1);
    }

    @Test
    public void testGetTrackByIdFailure(){
        Track track1 = new Track(13,"Paani da rang","ayushmann");
        trackRepository.save(track1);
        Track track2 = trackRepository.findById(track1.getTrackID()).get();
        assertNotSame(track,track2);
    }

    @Test
    public void testUpdateCommentSuccess(){
        trackRepository.save(track);
        track.setTrackComments("john legend v2.0");
        trackRepository.save(track);
        String comment = (trackRepository.findById(55).get()).getTrackComments();
        Assert.assertEquals("john legend v2.0", comment);
    }

    @Test
    public void testUpdateCommentFailure(){
        trackRepository.save(track);
        track.setTrackComments("john legend v2.0");
        String comment = (trackRepository.findById(55).get()).getTrackComments();
        Assert.assertNotEquals("john legend v2.0", comment);
    }
}