package com.kh.countingBell.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @Column(name="local_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "locationSequence")
    @SequenceGenerator(name = "locationSequence", sequenceName = "SEQ_LOCATION", allocationSize = 1)
    private int localCode;

    @Column(name="local_name")
    private String localName;

}
