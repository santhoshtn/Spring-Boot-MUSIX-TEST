package com.stackroute.muzixApp.repository;

import com.stackroute.muzixApp.domain.Track;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TrackRepository extends MongoRepository<Track,Integer> {
}