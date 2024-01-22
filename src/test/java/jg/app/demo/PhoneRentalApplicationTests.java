package jg.app.demo;

import jg.app.demo.exception.PhoneAlreadyBooked;
import jg.app.demo.exception.PhoneNotFound;
import jg.app.demo.repository.Phone;
import jg.app.demo.repository.PhoneDto;
import jg.app.demo.repository.PhoneRepository;
import jg.app.demo.service.PhoneServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static jg.app.demo.TestUtil.ID_1;
import static jg.app.demo.TestUtil.TEST_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class PhoneRentalApplicationTests {
  
  @Mock
  PhoneRepository phoneRepository;
  
  @InjectMocks
  PhoneServiceImpl phoneService;
  
  
  @Test
  void getsAllSavedPhones() {
    when(phoneRepository.findAll()).thenReturn(List.of(TestUtil.alreadyBookedTestPhoneOne()));
    
    List<PhoneDto> phoneDtos = phoneService.getAllPhones();
    
    verify(phoneRepository).findAll();
    assertThat(phoneDtos.size()).isEqualTo(1);
    assertThat(phoneDtos.get(0).getId()).isEqualTo(ID_1);
    assertThat(phoneDtos.get(0).getAvailable()).isEqualTo(false);
    assertThat(phoneDtos.get(0).getModelName()).isEqualTo("Samsung");
  }
  
  @Test
  void throwsNotFoundWhenBooksNonExistingId() {
    when(phoneRepository.findById(ID_1)).thenReturn(Optional.empty());
    
    Assertions.assertThrows(PhoneNotFound.class, () -> phoneService.bookPhone(TEST_USER, ID_1));
  }
  
  @Test
  void throwsAlreadyBookedWhenBooksAlreadyBooked() {
    when(phoneRepository.findById(ID_1)).thenReturn(Optional.of(TestUtil.alreadyBookedTestPhoneOne()));
    
    Assertions.assertThrows(PhoneAlreadyBooked.class, () -> phoneService.bookPhone(TEST_USER, ID_1));
    verify(phoneRepository, times(0)).save(any());
  }
  
  @Test
  void updatesPhoneWhenBooking() {
    when(phoneRepository.findById(ID_1)).thenReturn(Optional.of(TestUtil.availableTestPhoneOne()));
    when(phoneRepository.save(any())).thenReturn(TestUtil.alreadyBookedTestPhoneOne());
    
    ArgumentCaptor<Phone> phoneCaptor = ArgumentCaptor.forClass(Phone.class);
    
    PhoneDto phoneDto = phoneService.bookPhone(TEST_USER, ID_1);
    
    assertThat(phoneDto.getId()).isEqualTo(ID_1);
    assertThat(phoneDto.getAvailable()).isEqualTo(false);
    assertThat(phoneDto.getBookedBy()).isEqualTo(TEST_USER);
    
    verify(phoneRepository).save(phoneCaptor.capture());
    
    assertThat(phoneCaptor.getValue())
        .extracting("id", "isAvailable", "bookedBy")
        .containsExactly(phoneDto.getId(), phoneDto.getAvailable(), phoneDto.getBookedBy());
  }
  
  @Test
  void updatesPhoneWhenReturning() {
    when(phoneRepository.findById(ID_1)).thenReturn(Optional.of(TestUtil.alreadyBookedTestPhoneOne()));
    when(phoneRepository.save(any())).thenReturn(TestUtil.availableTestPhoneOne());
    
    ArgumentCaptor<Phone> phoneCaptor = ArgumentCaptor.forClass(Phone.class);
    
    PhoneDto phoneDto = phoneService.returnPhone(ID_1);
    
    assertThat(phoneDto.getId()).isEqualTo(ID_1);
    assertThat(phoneDto.getAvailable()).isEqualTo(true);
    assertThat(phoneDto.getBookedBy()).isNull();
    
    verify(phoneRepository).save(phoneCaptor.capture());
    
    assertThat(phoneCaptor.getValue())
        .extracting("id", "isAvailable", "bookedBy")
        .containsExactly(phoneDto.getId(), phoneDto.getAvailable(), phoneDto.getBookedBy());
  }
}
