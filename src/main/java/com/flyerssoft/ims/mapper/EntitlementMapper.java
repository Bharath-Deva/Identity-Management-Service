package com.flyerssoft.ims.mapper;

import com.flyerssoft.ims.model.dto.EntitlementDto;
import com.flyerssoft.ims.model.entity.Entitlement;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;


/**
 * The EntitlementMapper interface is responsible for mapping Entitlement
 * objects to custom objects and vice versa.
 */
@Mapper(componentModel = "spring")
public interface EntitlementMapper {

  Entitlement toEntity(EntitlementDto entitlementDto);

  List<Entitlement> dtoListToEntity(List<EntitlementDto> entitlementsDtos);

  EntitlementDto toDto(Entitlement entitlement);

  List<EntitlementDto> toDto(List<Entitlement> entitlements);

  Set<EntitlementDto> toDto(Set<Entitlement> entitlements);

}
