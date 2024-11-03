package eu.lundegaard.test.task.userapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    @Setter
    @NotBlank(message = "Username shouldn't be blank")
    @NotNull(message = "Username shouldn't be null")
    private String username;

    @Column(name = "email")
    @Setter
    @NotBlank(message = "Email shouldn't be blank")
    @Email
    private String email;

    @Column(name = "created_date", insertable = false, updatable = false)
    @Setter
    private Date createdDate;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.REMOVE)
    private List<Address> addresses;
}
