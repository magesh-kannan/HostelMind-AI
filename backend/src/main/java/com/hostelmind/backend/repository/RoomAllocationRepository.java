package com.hostelmind.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hostelmind.backend.entity.RoomAllocation;
import com.hostelmind.backend.enums.AllocationStatus;

@Repository
public interface RoomAllocationRepository extends JpaRepository<RoomAllocation, Long> {

    Optional<RoomAllocation> findByStudentIdAndStatus(
            Long studentId,
            AllocationStatus status);

    List<RoomAllocation> findByRoomId(Long roomId);

    List<RoomAllocation> findByStatus(AllocationStatus status);

    boolean existsByStudentIdAndStatus(
            Long studentId,
            AllocationStatus status);
}