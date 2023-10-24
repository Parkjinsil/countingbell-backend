package com.kh.countingBell.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Location {

    @Id
    @Column(name="local_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "locationSequence")
    @SequenceGenerator(name = "locationSequence", sequenceName = "SEQ_LOCATION", allocationSize = 1)
    private int localCode;

    @Column(name="local_name")
    private String localName;

}
