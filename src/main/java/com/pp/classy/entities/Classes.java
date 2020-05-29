package com.pp.classy.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Classes {

  @Id
  @GeneratedValue
  private Long id;
  private String name;

}
