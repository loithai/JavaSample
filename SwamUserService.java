package sample.data.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sample.data.jpa.domain.SwamUser;

import java.util.List;

/**
 * Created by loi on 11/14/16.
 */
public interface SwamUserService {

    List<SwamUser> getUsers();

    SwamUser getUser(String name);

    void saveUser(SwamUser user);

}
