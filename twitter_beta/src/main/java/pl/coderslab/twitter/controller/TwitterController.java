package pl.coderslab.twitter.controller;

import java.time.LocalDateTime;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.twitter.entity.Tweet;
import pl.coderslab.twitter.entity.User;
import pl.coderslab.twitter.repository.TweetRepository;
import pl.coderslab.twitter.repository.UserRepository;

@Controller
public class TwitterController {

  @Autowired
  private TweetRepository tweetRepository;
  @Autowired
  private UserRepository userRepository;

  @RequestMapping(value = "/tweets", method = RequestMethod.GET)
  public String tweetsView(Model model) {
    model.addAttribute("tweets", this.tweetRepository.getAllByOrderByCreatedDesc());
    model.addAttribute("newTweet", new Tweet());
    return "tweets";
  }

  private User getLoggedUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return this.userRepository.findByEmail(auth.getName());
  }

  @RequestMapping(path = "/tweets", method = RequestMethod.POST)
  public String tweetsViewAddNewTweet(@Valid @ModelAttribute(name = "newTweet") Tweet newTweet,
      BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "tweets";
    }
    newTweet.setUser(getLoggedUser());
    newTweet.setCreated(LocalDateTime.now());
    this.tweetRepository.save(newTweet);

    model.addAttribute("tweets", this.tweetRepository.getAllByOrderByCreatedDesc());
    model.addAttribute("newTweet", new Tweet());
    return "tweets";
  }
}
