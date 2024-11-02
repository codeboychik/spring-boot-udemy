package eu.lundegaard.test.task.userapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    private String city;

    @Column(name = "country")
    @Setter
    private String country;

    @JsonIgnore
    private Long userId;
}
