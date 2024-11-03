package eu.lundegaard.test.task.userapi.service;

import eu.lundegaard.test.task.userapi.entity.Address;
import eu.lundegaard.test.task.userapi.entity.User;
import eu.lundegaard.test.task.userapi.repository.AddressRepository;
import eu.lundegaard.test.task.userapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * File is created by andreychernenko at 02.11.2024
 */

@Service
public class UserService {

    UserRepository userRepository;
    AddressRepository addressRepository;

    UserService(UserRepository userRepository, AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Address saveNewAddress(Long userId, Address address) {
        address.setUserId(userId);
        return addressRepository.findById(addressRepository.save(address).getId()).get();
    }

    public Address updateUserAddress(Long userId, Long addressId, Address address) {
        User user = getById(userId);
        user.getAddresses()
            .stream()
            .filter(a -> Objects.equals(a.getId(), addressId))
            .peek(a -> {
                a.setCity(address.getCity());
                a.setCountry(address.getCountry());
            }).findFirst().ifPresent(addressRepository::save);
        return addressRepository.findById(addressId).get();
    }

    public User updateUser(Long userId, User user) {
        return saveUser(userRepository.findById(userId).map(temp -> {
            temp.setUsername(user.getUsername());
            temp.setEmail(user.getEmail());
            return temp;
        }).get());
    }

}
