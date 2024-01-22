package jg.app.demo;

import jg.app.demo.repository.Phone;
import jg.app.demo.repository.PhoneDto;
import jg.app.demo.repository.PhoneModel;

public class TestUtil {
  
  public static Long ID_1 = 1L;
  
  public static Long ID_2 = 2L;
  
  public static String TEST_USER = "testUser";
  public static PhoneModel testPhoneModel() {
    PhoneModel phoneModel = new PhoneModel();
    phoneModel.setId(ID_1);
    phoneModel.setModelName("Samsung");
    return phoneModel;
  }
  
  public static Phone alreadyBookedTestPhoneOne() {
    Phone phone = new Phone();
    phone.setId(ID_1);
    phone.setBookedBy(TEST_USER);
    phone.setAvailable(false);
    phone.setPhoneModel(testPhoneModel());
    
    return phone;
  }
  
  public static Phone availableTestPhoneOne() {
    Phone phone = new Phone();
    phone.setId(ID_1);
    phone.setAvailable(true);
    phone.setPhoneModel(testPhoneModel());
    
    return phone;
  }
  
  public static Phone availableTestPhoneTwo() {
    Phone phone = new Phone();
    phone.setId(ID_2);
    phone.setAvailable(true);
    phone.setPhoneModel(testPhoneModel());
    
    return phone;
  }
  
  public static PhoneDto phoneDto() {
    PhoneDto phoneDto = new PhoneDto();
    phoneDto.setId(ID_1);
    phoneDto.setAvailable(true);
    phoneDto.setModelName("Samsung");
    
    return phoneDto;
  }
}
