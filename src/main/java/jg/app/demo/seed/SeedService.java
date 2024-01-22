package jg.app.demo.seed;

import jg.app.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeedService implements CommandLineRunner {
  
  @Value("${populate.initial.database:true}")
  private Boolean populateDatabase;
  
  @Autowired
  private PhoneRepository phoneRepository;
  
  @Autowired
  private PhoneModelRepository phoneModelRepository;
  
  @Autowired
  private BasicUserRepository basicUserRepository;
  
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  
  @Override
  public void run(String... args) throws Exception {
    BasicUser basicUser = new BasicUser();
    basicUser.setUserName("demo");
    basicUser.setPassword(bCryptPasswordEncoder.encode("demo"));
    basicUser.setRole("ADMIN");
    basicUserRepository.save(basicUser);
    
    if (populateDatabase) {
      PhoneModel galaxyS9 = new PhoneModel("Samsung Galaxy S9");
      PhoneModel galaxyS8 = new PhoneModel("Samsung Galaxy S8");
      PhoneModel nexus6 = new PhoneModel("Motorola Nexus 6");
      PhoneModel onePlus9 = new PhoneModel("Oneplus 9");
      PhoneModel iPhone13 = new PhoneModel("Apple iPhone 13");
      PhoneModel iPhone12 = new PhoneModel("Apple iPhone 12");
      PhoneModel iPhone11 = new PhoneModel("Apple iPhone 11");
      PhoneModel iPhoneX = new PhoneModel("iPhone X");
      PhoneModel nokia3310 = new PhoneModel("Nokia 3310");
      
      phoneModelRepository.saveAll(List.of(
          galaxyS9, galaxyS8, nexus6, onePlus9, iPhone13, iPhone12, iPhone11, iPhoneX, nokia3310
      ));
      
      
      List<Phone> phones = List.of(
          new Phone(galaxyS9, true),
          new Phone(galaxyS8, true),
          new Phone(galaxyS8, true),
          new Phone(nexus6, true),
          new Phone(onePlus9, true),
          new Phone(iPhone13, true),
          new Phone(iPhone12, true),
          new Phone(iPhone11, true),
          new Phone(iPhoneX, true),
          new Phone(nokia3310, true)
      );
      
      phoneRepository.saveAll(phones);
    }
  }
}
