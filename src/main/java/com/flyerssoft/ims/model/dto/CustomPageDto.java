package com.flyerssoft.ims.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Custom page dto class .
 *
 * @param <T> data
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class CustomPageDto<T> {
  private T content;
  private long totalElements;
  private int totalPages;
  private int offset;
  private int pageNo;
  private int numberOfElements;
}
