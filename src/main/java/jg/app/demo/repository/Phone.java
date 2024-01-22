package jg.app.demo.repository;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Phone {
  @Id
  @GeneratedValue
  private Long id;
  @ManyToOne
  @JoinColumn(name = "phone_model_id", nullable = false)
  private PhoneModel phoneModel;
  @NotNull
  @Column
  private Boolean isAvailable;
  @Column
  private String bookedBy;
  @Column
  private LocalDateTime bookedAt;
  
  public Phone() {
  }
  
  public Phone(PhoneModel phoneModel, Boolean isAvailable) {
    this.phoneModel = phoneModel;
    this.isAvailable = isAvailable;
  }
  
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public PhoneModel getPhoneModel() {
    return phoneModel;
  }
  
  public void setPhoneModel(PhoneModel phoneModel) {
    this.phoneModel = phoneModel;
  }
  
  public Boolean getAvailable() {
    return isAvailable;
  }
  
  public void setAvailable(Boolean available) {
    isAvailable = available;
  }
  
  public String getBookedBy() {
    return bookedBy;
  }
  
  public void setBookedBy(String bookedBy) {
    this.bookedBy = bookedBy;
  }
  
  public LocalDateTime getBookedAt() {
    return bookedAt;
  }
  
  public void setBookedAt(LocalDateTime bookedAt) {
    this.bookedAt = bookedAt;
  }
}
