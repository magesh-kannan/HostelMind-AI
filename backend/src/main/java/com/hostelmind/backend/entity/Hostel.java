package com.hostelmind.backend.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "hostels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hostel extends BaseEntity {

   

    @Column(nullable = false)
    private String hostelName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer totalRooms;

    @Column(nullable = false)
    private Integer totalBeds;

    @Column(nullable = false)
    private Boolean active = true;

   

   
}