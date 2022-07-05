package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "trade")
public class Trade {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer tradeId;

  private String account;

  public Trade(String account, String type) {
    this.account = account;
    this.type = type;
  }

  private String type;

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

}
