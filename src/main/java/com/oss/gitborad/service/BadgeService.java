package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.BadgeDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * BadgeService is an interface for managing Badge entities.
 * It outlines the CRUD operations for Badge.
 */
public interface BadgeService {

    /**
     * Finds a single Badge entity by its identifier.
     *
     * @param id The identifier of the Badge entity to retrieve.
     * @return The Badge.DTO.Info object containing Badge details.
     */
    BadgeDTO.Info findOne(Long id);

    /**
     * Saves a new Badge entity or updates an existing one.
     * It also uploads an image file associated with the Badge.
     *
     * @param requestDTO The DTO containing information to save.
     * @param imageFile The MultipartFile containing the Badge image.
     * @return The BadgeDTO.Info object containing the saved Badge details.
     * @throws IOException If an error occurs while handling the image file.
     */
    BadgeDTO.Info save(BadgeDTO.Request requestDTO, MultipartFile imageFile) throws IOException;

    /**
     * Deletes a Badge entity by its identifier.
     *
     * @param id The identifier of the Badge entity to delete.
     */
    void delete(Long id);
}
