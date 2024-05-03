package com.flyerssoft.ims.model.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * The project dto.
 */
@Getter
@Setter
public class ProjectDto {

  private int projectId;

  @NotBlank(message = "is mandatory")
  @Pattern(regexp = "^[A-Za-z0-9 ]+$",
      message = "must not contain special characters & numerics")
  @Size(min = 2, max = 30, message = "must be between 2 to 30")
  private String projectName;

}
