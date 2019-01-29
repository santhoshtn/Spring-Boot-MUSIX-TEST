package com.stackroute.muzixApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzixApp.MuzixAppApplication;
import com.stackroute.muzixApp.config.MuzixAppConfiguration;
import com.stackroute.muzixApp.domain.Track;
import com.stackroute.muzixApp.exceptions.GlobalExceptionHandler;
import com.stackroute.muzixApp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixApp.exceptions.TrackNotFoundException;
import com.stackroute.muzixApp.service.TrackService;
import com.stackroute.muzixApp.service.TrackServiceImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.exceptions.util.ScenarioPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@RunWith(SpringRunner.class)
@WebMvcTest
public class MuzicAppControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Track track;

    @MockBean
    private TrackServiceImpl trackService;
    @InjectMocks
    private MuzicAppController muzicAppController;

    private List<Track> list=null;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(muzicAppController).build();
        track = new Track();
        track.setTrackID(26);
        track.setTrackName("Jonny");
        track.setTrackComments("Hello");
        list = new ArrayList();
        list.add(track);
    }

    @Test
    public void saveTrackSuccess() throws Exception {
        when(trackService.saveTrack(any())).thenReturn(track);
        //doReturn(track).when(trackService.saveTrack(track));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        //verify here verifies that userRepository save method is only called once
//        verify(trackService,times(1)).saveTrack(track);

    }

    @Test
    public void saveTrackFailure() throws Exception {
//        doThrow(TrackAlreadyExistsException.class).when((trackService).saveTrack(track));
        when(trackService.saveTrack(any())).thenThrow(TrackAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());

        //verify here verifies that userRepository save method is only called once
            verify(trackService,times(1)).saveTrack(track);
    }

    @Test
    public void getAllTracksSuccess() throws Exception {
        when(trackService.getAllTracks()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //verify here verifies that userRepository save method is only called once
        verify(trackService,times(1)).getAllTracks();
    }

    @Test
    public void getAllTracksFailure() throws Exception {
        when(trackService.getAllTracks()).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        //verify here verifies that userRepository save method is only called once
        verify(trackService,times(1)).getAllTracks();
    }

    @Test
    public void removeTrackSuccess() throws Exception {
        when(trackService.deleteByID(any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/100")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //verify here verifies that userRepository save method is only called once
//        verify(trackService,times(1)).deleteByID(track);
       }
    @Test
    public void removeTrackFailure() throws Exception {
        when(trackService.deleteByID(any())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/7")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //verify here verifies that userRepository save method is only called once
//        verify(trackService,times(1)).deleteByID(track);
    }







    @Test
    public void getTrackByIdSuccess() throws Exception {
//        doReturn(track).when(trackService.getTrackByID(track.getTrackID()));
        when(trackService.getTrackByID(anyInt())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/100")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //verify here verifies that userRepository save method is only called once
        verify(trackService,times(1)).getTrackByID(anyInt());

    }
    @Test
    public void getTrackByIdFailure() throws Exception {
//        doReturn(track).when(trackService.getTrackByID(track.getTrackID()));
        when(trackService.getTrackByID(anyInt())).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/userservice")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

        //verify here verifies that userRepository save method is only called once
//        verify(trackService,times(1)).getTrackByID(anyInt());

    }

    @Test
    public void updateCommentSuccess() throws Exception {
        when(trackService.updateTrack(any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //verify here verifies that userRepository save method is only called once
        verify(trackService,times(1)).updateTrack(track);

    }

    @Test
    public void updateCommentFailure() throws Exception {
        when(trackService.updateTrack(any())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //verify here verifies that userRepository save method is only called once
        verify(trackService,times(1)).updateTrack(track);

    }

    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}