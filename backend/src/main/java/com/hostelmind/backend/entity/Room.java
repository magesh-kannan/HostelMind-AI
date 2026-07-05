package com.hostelmind.backend.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room extends BaseEntity {

    

    @Column(nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Integer occupiedBeds = 0;

    @ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "hostel_id", nullable = false)
private Hostel hostel;

  

    
}