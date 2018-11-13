package pl.coderslab.twitter.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.twitter.entity.Tweet;
import pl.coderslab.twitter.entity.User;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

  List<Tweet> getAllByOrderByCreatedDesc();
}
