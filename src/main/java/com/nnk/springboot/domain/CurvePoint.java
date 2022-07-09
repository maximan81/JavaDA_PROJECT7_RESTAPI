package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;

/**
 * The CurvePoint class implements a curvepoint
 * entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  private Integer curveId;

  private Timestamp asOfDate;

  @Column(columnDefinition = "Decimal(10,2) default '0.00'")
  @NotNull
  private Double term;

  @Column(columnDefinition = "Decimal(10,2) default '0.00'")
  @NotNull
  private Double value;

  private Timestamp creationDate;

  public CurvePoint(Integer curveId, Double term, Double value) {
    this.curveId = curveId;
    this.term = term;
    this.value = value;
  }
}
