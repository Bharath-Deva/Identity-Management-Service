package com.flyerssoft.ims.mapper;

import com.flyerssoft.ims.model.dto.UserGroupPermissionDto;
import com.flyerssoft.ims.model.entity.groupPermission;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * Mapper class which holded all the mapper interface and its
 * implementations of the user group permissions.
 */
@Mapper(componentModel = "spring")
public interface UserGroupPermissionMapper {

  UserGroupPermissionDto toDto(groupPermission groupPermission);

  List<UserGroupPermissionDto> toDtoList(List<groupPermission> groupPermissionList);
}
