package com.hostelmind.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostelmind.backend.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsByHostelId(Long hostelId);

}