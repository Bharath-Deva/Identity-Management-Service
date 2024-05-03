package com.flyerssoft.ims.model.dto.task;

import com.flyerssoft.ims.model.entity.enums.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

/**
 * The TaskPutDto dto.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskPutDto {

  @Size(min = 2, max = 30, message = "must be between 2 to 30")
  private String taskName;

  @Size(min = 2, max = 30, message = "must be between 2 to 30")
  private String taskReference;

  private String taskDescription;

  @Enumerated(EnumType.STRING)
  private TaskStatus taskStatus;

  @Range(min = 1, max = 8, message = "must be between 1 and 8")
  private Short taskDuration;
}
