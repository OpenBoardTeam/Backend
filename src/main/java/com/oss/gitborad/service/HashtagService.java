package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.HashtagDTO;

import java.util.List;

/**
 * HashtagService is an interface for managing Hashtag and Hashtag group entities.
 * It outlines the operations for Hashtags and Hashtag groups.
 */
public interface HashtagService {

    /**
     * Retrieves a list of Hashtag entities associated with a specific hashtag-group.
     *
     * @param id The identifier of the group for which to retrieve Hashtags.
     * @return A list of HashtagDTO.Info objects containing the details of each Hashtag.
     */
    List<HashtagDTO.Info> findListByGroup(Long id);

    /**
     * Retrieves a list of Hashtag entities created by a specific writer.
     *
     * @param id The identifier of the writer for which to retrieve Hashtags.
     * @return A list of HashtagDTO.Info objects containing the details of each Hashtag.
     */
    List<HashtagDTO.Info> findListByWriter(Long id);

    /**
     * Retrieves all Hashtag entities, paginated.
     *
     * @param pageNumber The page number to retrieve.
     * @param size The number of Hashtags per page.
     * @return A list of HashtagDTO.Info objects containing the details of each Hashtag.
     */
    List<HashtagDTO.Info> findAll(int pageNumber, int size);

    /**
     * Retrieves a list of Hashtag entities that are certified under a specific hashtag group.
     *
     * @param hashtagGroupId The identifier of the hashtag group to find.
     * @return A HashtagDTO.GroupListInfo object containing the details of certified Hashtags.
     */
    HashtagDTO.GroupListInfo findHashtagByCertified(long hashtagGroupId);

    /**
     * Saves a new Hashtag entity or updates an existing one.
     *
     * @param requestDTO The DTO containing information to save.
     */
    void saveHashtag(HashtagDTO.Request requestDTO);

    /**
     * Deletes a Hashtag entity, verifying the ownership by matching the userId.
     *
     * @param id The identifier of the Hashtag entity to delete.
     * @param userId The identifier of the user who owns the Hashtag.
     */
    void deleteHashtag(Long id, Long userId);

    /**
     * Saves a new HashtagGroup entity or updates an existing one.
     *
     * @param requestDTO The DTO containing information to save for the HashtagGroup.
     */
    void saveHashtagGroup(HashtagDTO.GroupRequest requestDTO);

    /**
     * Deletes a Hashtag group entity.
     *
     * @param id The identifier of the HashtagGroup entity to delete.
     */
    void deleteHashtagGroup(Long id);

}
