package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.InterestDTO;
import com.oss.gitborad.data.dto.UserDTO;

import java.util.List;

/**
 * InterestService is an interface for managing Interest entities.
 * It outlines the operations for Interest, providing CRUD functionalities.
 */
public interface InterestService {

    /**
     * Retrieves a list of Interest entities associated with a specific user.
     *
     * @param userId The identifier of the user for which to retrieve Interests.
     * @return A list of InterestDTO.Info objects containing details of each Interest.
     */
    List<InterestDTO.Info> findListByUser(Long userId);

    /**
     * Saves a new Interest or updates an existing one.
     * @param requestDTO The DTO containing information to save.
     * @param principal The authenticated user performing the operation, represented as a UserDTO.Info object.
     */
    void save(InterestDTO.Request requestDTO, UserDTO.Info principal);

    /**
     * Deletes an Interest entity by its identifier.
     * @param id The identifier of the Interest entity to delete.
     */
    void delete(Long id);
}
