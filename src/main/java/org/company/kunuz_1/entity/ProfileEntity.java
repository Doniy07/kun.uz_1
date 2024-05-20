package org.company.kunuz_1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.company.kunuz_1.enums.ProfileRole;
import org.company.kunuz_1.enums.ProfileStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "profiles")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname" , nullable = false)
    private String surname;
    @Column(name = "email" , nullable = false)
    private String email;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "password" , nullable = false)
    private String password;
    @Enumerated()
    @Column(name = "status" , nullable = false)
    private ProfileStatus status;
    @Enumerated()
    @Column(name = "role")
    private ProfileRole role;
    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
}
