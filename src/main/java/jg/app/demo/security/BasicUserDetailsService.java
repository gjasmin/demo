package jg.app.demo.security;

import jg.app.demo.repository.BasicUser;
import jg.app.demo.repository.BasicUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BasicUserDetailsService implements UserDetailsService {
  
  private BasicUserRepository basicUserRepository;
  
  public BasicUserDetailsService(BasicUserRepository basicUserRepository) {
    this.basicUserRepository = basicUserRepository;
  }
  
  @Override
  public UserDetails loadUserByUsername(String userName) {
    BasicUser user = basicUserRepository.findByUserName(userName);
    if (user == null) {
      throw new UsernameNotFoundException(userName);
    }
    return User.builder()
        .username(user.getUserName())
        .password(user.getPassword())
        .roles(user.getRole())
        .build();
  }
}
