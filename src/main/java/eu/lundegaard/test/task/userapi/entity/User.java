package eu.lundegaard.test.task.userapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

/**
 * File is created by andreychernenko at 01.11.2024
 */

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    @Setter
    private String username;

    @Column(name = "email")
    @Setter
    private String email;

    @Column(name = "created_date", insertable = false, updatable = false)
    private Date createdDate;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Address> addresses;
}
