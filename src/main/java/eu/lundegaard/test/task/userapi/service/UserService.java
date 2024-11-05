package eu.lundegaard.test.task.userapi.service;

import eu.lundegaard.test.task.userapi.entity.Address;
import eu.lundegaard.test.task.userapi.entity.User;
import eu.lundegaard.test.task.userapi.exception.AddressNotFoundException;
import eu.lundegaard.test.task.userapi.exception.UserNotFoundException;
import eu.lundegaard.test.task.userapi.repository.AddressRepository;
import eu.lundegaard.test.task.userapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * File is created by andreychernenko at 02.11.2024
 */

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private AddressRepository addressRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Address saveNewAddress(Long userId, Address address) {
        getById(userId);
        address.setUserId(userId);
        return addressRepository.save(address);
    }

    public Address updateUserAddress(Long userId, Long addressId, Address address) {
        getById(userId);
        return addressRepository.findById(addressId)
            .filter(a -> Objects.equals(a.getUserId(), userId))
            .map(a -> {
                a.setCity(address.getCity());
                a.setCountry(address.getCountry());
                return addressRepository.save(a);
            })
            .orElseThrow(() -> new AddressNotFoundException(addressId.toString()));
    }

    public User updateUser(Long userId, User user) {
        getById(userId);
        return saveUser(userRepository.findById(userId).map(temp -> {
            temp.setUsername(Optional.ofNullable(user.getUsername()).orElse(temp.getUsername()));
            temp.setEmail(Optional.ofNullable(user.getEmail()).orElse(temp.getEmail()));
            return temp;
        }).get());
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void deleteUserAddress(Long userId, Long addressId) {
        getById(userId)
            .getAddresses()
            .stream()
            .filter(address -> Objects.equals(address.getId(), addressId))
            .findFirst()
            .ifPresent(addressRepository::delete);
    }

}
