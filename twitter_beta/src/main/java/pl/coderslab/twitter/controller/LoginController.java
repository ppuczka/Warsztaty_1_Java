package pl.coderslab.twitter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.twitter.entity.User;
import pl.coderslab.twitter.repository.UserRepository;

@Controller
@RequestMapping("/")
public class LoginController {

  private static final String ANONYMOUS_USER = "anonymousUser";

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @RequestMapping(path = {"/login", "/"})
  public String loginView() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!authentication.getName().equals(ANONYMOUS_USER)) {
      return "redirect:/tweets";
    }
    return "login";
  }

  @RequestMapping(path = {"/register"}, method = RequestMethod.GET)
  public String registerView(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return "redirect:/login?logout";
  }

  @RequestMapping(path = {"/register"}, method = RequestMethod.POST)
  public String registerUser(@Valid @ModelAttribute User user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "register";
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setEnabled(true);
    this.userRepository.save(user);
    return "redirect:login";
  }


}
