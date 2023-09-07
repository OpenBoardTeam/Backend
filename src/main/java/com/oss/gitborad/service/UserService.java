package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.UserDTO;

/**
 * UserService is an interface for managing User entities.
 * It provides various functionalities for User management including finding,
 * saving, and deleting users.
 */
public interface UserService {

    /**
     * Retrieves a single User entity by its username.
     *
     * @param username The username of the User entity to retrieve.
     * @return The UserDTO.InfoForAll object containing User details.
     */
    UserDTO.InfoForAll findOne(String username);

    /**
     * Saves badge-related information for a User entity.
     *
     * @param requestDTO The DTO containing badge information to save.
     */
    void saveForBadge(UserDTO.BadgeRequest requestDTO);

    /**
     * Deletes a User entity by its identifier.
     *
     * @param userId The identifier of the User entity to delete.
     */
    void deleteUserById(Long userId);
}