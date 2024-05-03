package com.flyerssoft.ims.model.repository;

import com.flyerssoft.ims.model.entity.groupPermission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserGroupPermission table - Repository interface.
 */
public interface UserGroupPermissionRepository extends JpaRepository<groupPermission, Long> {
  List<groupPermission> findByIsDefault(boolean b);
}
