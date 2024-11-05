package eu.lundegaard.test.task.userapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

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
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    @Setter
    @NotBlank(message = "Username shouldn't be blank or null")
    @Size(min = 1, max = 32, message = "Username length must have 1-32 characters")
    private String username;

    @Column(name = "email")
    @Setter
    @Pattern(regexp = "\\S+", message = "Must not be blank or contain whitespace")
    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @Column(name = "created_date", insertable = false, updatable = false)
    @Setter
    private Date createdDate;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.REMOVE)
    private List<Address> addresses;
}
