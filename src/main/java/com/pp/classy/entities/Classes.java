package com.pp.classy.entities;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
public class Classes {

  @Id
  @GeneratedValue
  private Long id;
  private String name;

  @OneToMany(mappedBy = "classes")
  private List<Attendances> attendances;

  @ManyToOne
  private User teacher;

  @Column(updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
