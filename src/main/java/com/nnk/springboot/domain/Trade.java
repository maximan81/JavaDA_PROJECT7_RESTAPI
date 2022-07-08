package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * The Trade class implements a trade
 * entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trade")
public class Trade {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer tradeId;

  @NotBlank
  private String account;
  @NotBlank
  private String type;

  @NotNull
  private Double buyQuantity;

  private Double sellQuantity;

  private Double buyPrice;

  private Double sellPrice;

  private String benchmark;

  private Timestamp tradeDate;

  private String security;

  private String status;

  private String trader;

  private String book;

  private String creationName;

  private Timestamp creationDate;

  private String revisionName;

  private Timestamp revisionDate;

  private String dealName;

  private String dealType;

  private String sourceListId;

  private String side;

  public Trade(String account, String type, Double buyQuantity) {
    this.account = account;
    this.type = type;
    this.buyQuantity = buyQuantity;
  }
}
