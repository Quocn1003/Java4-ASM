package com.slide5.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity()
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    private String id;
    private String fullname;
    private String password;
    @Column(name = "email", length = 50, unique = true, nullable = false)
    private String email;
    private Boolean admin;


    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "user")
    private List<Share> shares;

    // Getter methods for backward compatibility
    public String getPassword() {
        return password;
    }
    
    public String getFullname() {
        return fullname;
    }
    
    public String getEmail() {
        return email;
    }

    // Additional fields and methods can be added as needed
}
