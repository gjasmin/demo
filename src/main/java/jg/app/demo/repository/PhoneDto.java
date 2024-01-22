package jg.app.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

public class PhoneDto {
  private Long id;
  private String modelName;
  private Boolean isAvailable;
  private String bookedBy;
  private LocalDateTime bookedAt;
  
  public static PhoneDto fromEntity(Phone phone) {
    PhoneDto phoneDto = new PhoneDto();
    phoneDto.setId(phone.getId());
    phoneDto.setAvailable(phone.getAvailable());
    phoneDto.setModelName(phone.getPhoneModel().getModelName());
    phoneDto.setBookedBy(phone.getBookedBy());
    phoneDto.setBookedAt(phone.getBookedAt());
    
    return phoneDto;
  }
  
  public static List<PhoneDto> fromListOfEntities(List<Phone> phones) {
    return phones.stream()
        .map(PhoneDto::fromEntity)
        .toList();
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
