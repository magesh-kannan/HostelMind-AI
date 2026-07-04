package com.hostelmind.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostelmind.backend.entity.Hostel;

public interface HostelRepository extends JpaRepository<Hostel, Long> {
}