package com.kh.countingBell.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Photo {

    @Id
    @Column(name="res_photo_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "photoSequence")
    @SequenceGenerator(name="photoSequence", sequenceName = "SEQ_PHOTO", allocationSize = 1)
    private int resPhotoCode;

    @Column(name="photo_url")
    private String photoUrl;

    @Column(name="photo_name")
    private String photoName;

    @ManyToOne
    @JoinColumn(name="res_code")
    private Restaurant restaurant;
}
