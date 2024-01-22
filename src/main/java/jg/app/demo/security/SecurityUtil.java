package jg.app.demo.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
  
  public static String getCurrentLoggedUser() {
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }
}
