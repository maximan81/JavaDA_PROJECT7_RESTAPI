package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * The CurvePoint class implements a curvepoint
 * entity.
 */
@Data
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private Integer curveId;

  private Timestamp asOfDate;

  private Double term;

  private Double value;

  private Timestamp creationDate;

  public CurvePoint(Integer curveId, Double term, Double value) {
    this.curveId = curveId;
    this.term = term;
    this.value = value;
  }
}
