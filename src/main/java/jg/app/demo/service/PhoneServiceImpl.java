package jg.app.demo.service;

import jg.app.demo.exception.PhoneAlreadyBooked;
import jg.app.demo.exception.PhoneNotFound;
import jg.app.demo.repository.Phone;
import jg.app.demo.repository.PhoneDto;
import jg.app.demo.repository.PhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {
  
  private Logger logger = LoggerFactory.getLogger(PhoneServiceImpl.class);
  private PhoneRepository phoneRepository;
  
  public PhoneServiceImpl(PhoneRepository phoneRepository) {
    this.phoneRepository = phoneRepository;
  }
  
  @Override
  public List<PhoneDto> getAllPhones() {
    List<Phone> phones = phoneRepository.findAll();
    
    return PhoneDto.fromListOfEntities(phones);
  }
  
  @Override
  public PhoneDto bookPhone(String user, Long id) {
    Optional<Phone> phoneMaybe = phoneRepository.findById(id);
    if (phoneMaybe.isEmpty()) {
      throw new PhoneNotFound();
    }
    
    Phone phone = phoneMaybe.get();
    if (!phone.getAvailable()) {
      throw new PhoneAlreadyBooked();
    }
    
    phone.setAvailable(false);
    phone.setBookedAt(LocalDateTime.now(ZoneOffset.UTC));
    phone.setBookedBy(user);
    
    Phone savedPhone = phoneRepository.save(phone);
    
    return PhoneDto.fromEntity(savedPhone);
  }
  
  @Override
  public PhoneDto returnPhone(Long id) {
    Optional<Phone> phoneMaybe = phoneRepository.findById(id);
    if (phoneMaybe.isEmpty()) {
      throw new PhoneNotFound();
    }
    
    Phone phone = phoneMaybe.get();
    phone.setAvailable(true);
    phone.setBookedAt(null);
    phone.setBookedBy(null);
    
    Phone savedPhone = phoneRepository.save(phone);
    
    return PhoneDto.fromEntity(savedPhone);
  }
  
}
