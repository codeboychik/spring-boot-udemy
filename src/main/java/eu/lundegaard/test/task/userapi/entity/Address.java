package eu.lundegaard.test.task.userapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * File is created by andreychernenko at 02.11.2024
 */


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city")
    @Setter
    @NotBlank(message = "city shouldn't be blank")
    private String city;

    @Column(name = "country")
    @Setter
    @NotBlank(message = "country shouldn't be blank")
    private String country;

    @JsonIgnore
    @Setter
    @Column(name = "user_id")
    private Long userId;
}
