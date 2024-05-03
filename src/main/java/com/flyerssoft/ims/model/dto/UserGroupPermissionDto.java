package com.flyerssoft.ims.model.dto;

import java.util.List;

/**
 * Custom DTO object used to transfer the user group permission data with entitlements.
 *
 * @param id              - primary key.
 * @param groupName       - group name.
 * @param description     - user group permission's description.
 * @param isDefault       - meta to prioritize the user group permission.
 * @param entitilementDto - entitlements which are all belongs to the particular user permissions.
 */
public record UserGroupPermissionDto(Long id,
                                     String groupName,
                                     String description,
                                     Boolean isDefault,
                                     List<EntitlementDto> entitilementDto) {

}
