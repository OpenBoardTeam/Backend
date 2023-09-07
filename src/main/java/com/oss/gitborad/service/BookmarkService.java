package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.BookmarkDTO;

import java.util.List;

/**
 * BookmarkService is an interface for managing Bookmark entities.
 * It outlines the CRUD operation for Bookmark.
 */
public interface BookmarkService {

    /**
     * Retrieves a list of Bookmark entities associated with a specific user.
     *
     * @param userId The identifier of the user for whom to retrieve Bookmarks.
     * @return A list of BookmarkDTO.InfoForList objects containing the details of each Bookmark.
     */
    List<BookmarkDTO.InfoForList>  findListByUser(Long userId);

    /**
     * Save a new Bookmark entity.
     *
     * @param requestDTO The DTO containing information to save.
     * @return The BookmarkDTO.Info object containing the saved Bookmark details.
     */
    BookmarkDTO.Info save(BookmarkDTO.Request requestDTO);

    /**
     * Deletes a Bookmark entity, verifying the ownership by matching the userId.
     *
     * @param id The identifier of the Bookmark entity to delete.
     * @param userId userId The identifier of the user who owns the Bookmark.
     */
    void delete(Long id, Long userId);
}
