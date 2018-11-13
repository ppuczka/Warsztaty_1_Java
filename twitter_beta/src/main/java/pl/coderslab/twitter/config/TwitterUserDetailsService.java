package pl.coderslab.twitter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.coderslab.twitter.entity.User;
import pl.coderslab.twitter.repository.UserRepository;

@Component
public class TwitterUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;

  @Autowired
  private TwitterUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.userRepository.findByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }
    return new TwitterUserDetails(user);
  }
}
