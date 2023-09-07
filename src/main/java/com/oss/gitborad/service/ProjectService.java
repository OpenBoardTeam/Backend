package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.ProjectDTO;
import com.oss.gitborad.data.dto.UserDTO;

import java.util.List;

/**
 * ProjectService is an interface for managing Project entities.
 * It provides CRUD operations and other functionalities for Project management.
 */
public interface ProjectService {

    /**
     * Retrieves a single Project entity by its identifier.
     *
     * @param id The identifier of the Project entity to retrieve.
     * @return The ProjectDTO.Info object containing Project details.
     */
    ProjectDTO.Info findOne(Long id);

    /**
     * Retrieves a list of Project entities associated with a specific user.
     *
     * @param id The identifier of the user for which to retrieve Projects.
     * @return A list of ProjectDTO.Info objects containing details of each Project.
     */
    List<ProjectDTO.Info> findListByUser(Long id);

    /**
     * Saves a new Project entity or updates an existing one.
     *
     * @param requestDTO The DTO containing information to save.
     * @return The ProjectDTO.Info object containing the saved Project details.
     */
    ProjectDTO.Info save(ProjectDTO.Request requestDTO);

    /**
     * Updates an existing Project entity.
     *
     * @param requestDTO The DTO containing information to update.
     */
    void update(ProjectDTO.Request requestDTO);

    /**
     * Retrieves basic information about a Project based on its URL.
     *
     * @param url The URL of the Project.
     * @return The ProjectDTO.ResponseBasicInfo object containing basic Project details.
     */
    ProjectDTO.ResponseBasicInfo getBasicInfo(String url);

    /**
     * Deletes a Project entity, verifying the ownership by matching the principal.
     *
     * @param id The identifier of the Project entity to delete.
     * @param principal The authenticated user performing the operation, represented as a UserDTO.Info object.
     */
    void delete(Long id, UserDTO.Info principal);
}