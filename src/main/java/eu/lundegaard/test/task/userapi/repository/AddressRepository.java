package eu.lundegaard.test.task.userapi.repository;

import eu.lundegaard.test.task.userapi.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * File is created by andreychernenko at 03.11.2024
 */


public interface AddressRepository extends JpaRepository<Address, Long> {}
