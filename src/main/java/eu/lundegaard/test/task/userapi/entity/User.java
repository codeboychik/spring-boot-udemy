package eu.lundegaard.test.task.userapi.entity;

import jakarta.persistence.*;

import java.util.Date;

/**
 * File is created by andreychernenko at 01.11.2024
 */

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "email")
    private String email;

    @Column(name = "created_date", insertable = false, updatable = false)
    private Date createdDate;

    // TODO: address field
}
