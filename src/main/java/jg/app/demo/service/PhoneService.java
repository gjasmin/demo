package jg.app.demo.service;

import jg.app.demo.repository.PhoneDto;

import java.util.List;

public interface PhoneService {
  List<PhoneDto> getAllPhones();
  
  PhoneDto bookPhone(String user, Long id);
  
  PhoneDto returnPhone(Long id);
}
