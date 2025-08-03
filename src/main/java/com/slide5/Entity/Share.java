package com.slide5.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "shares")
@Data
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "emails", length = 500)
    private String emails;
    
    @Column(name = "share_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shareDate;

    @ManyToOne
    @JoinColumn(name = "videoId")    
    private Video video;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

}
