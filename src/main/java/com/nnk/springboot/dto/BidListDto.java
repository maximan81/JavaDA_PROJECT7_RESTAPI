package com.nnk.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * The BidListDto class implements a variation of bidList
 * entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BidListDto {

  private Integer bidListId;

  @NotBlank(message = "Account can not be blank!")
  private String account;

  @NotBlank(message = "Type can not be blank!")
  private String type;

  private Double bidQuantity;

}
