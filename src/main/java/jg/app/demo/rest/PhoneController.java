package jg.app.demo.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jg.app.demo.repository.PhoneDto;
import jg.app.demo.security.SecurityUtil;
import jg.app.demo.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "basicAuth")
@RequestMapping("/phones")
@RestController
public class PhoneController {
  
  @Autowired
  private PhoneService phoneService;
  
  @GetMapping
  public List<PhoneDto> getAllPhones() {
    return phoneService.getAllPhones();
  }
  
  @PostMapping("/book/{id}")
  public PhoneDto bookPhone(@PathVariable Long id) {
    return phoneService.bookPhone(SecurityUtil.getCurrentLoggedUser(), id);
  }
  
  @PostMapping("/return/{id}")
  public PhoneDto returnPhone(@PathVariable Long id) {
    return phoneService.returnPhone(id);
  }
}
