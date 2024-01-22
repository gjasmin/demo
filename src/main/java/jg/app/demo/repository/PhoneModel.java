package jg.app.demo.repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
public class PhoneModel {
  @Id
  @GeneratedValue
  private Long id;
  @Column
  @NotNull
  private String modelName;
  @OneToMany(mappedBy = "phoneModel")
  private Set<Phone> phones;
  
  public PhoneModel() {
  }
  
  public PhoneModel(String modelName) {
    this.modelName = modelName;
  }
  
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getModelName() {
    return modelName;
  }
  
  public void setModelName(String modelName) {
    this.modelName = modelName;
  }
  
  public Set<Phone> getPhones() {
    return phones;
  }
  
  public void setPhones(Set<Phone> phones) {
    this.phones = phones;
  }
}
