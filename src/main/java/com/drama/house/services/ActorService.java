package com.drama.house.services;

import com.drama.house.dto.ActorDTO;

import java.util.List;

public interface ActorService {
    ActorDTO getActorById(Long actorId);
    List<ActorDTO> getAllActors();
    ActorDTO createActor(ActorDTO actorDTO);
    ActorDTO updateActor(Long actorId, ActorDTO actorDTO);
    void deleteActor(Long actorId);
}
