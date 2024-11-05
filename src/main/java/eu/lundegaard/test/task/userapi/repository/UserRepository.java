package eu.lundegaard.test.task.userapi.repository;

import eu.lundegaard.test.task.userapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * File is created by andreychernenko at 02.11.2024
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> { }
