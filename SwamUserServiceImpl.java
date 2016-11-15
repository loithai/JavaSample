package sample.data.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import sample.data.jpa.domain.SwamUser;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by loi on 11/14/16.
 */
@Component("swamUserService")
@Transactional
class SwamUserServiceImpl implements SwamUserService {

    private final SwamUserRepository swamUserRepository;

    public SwamUserServiceImpl(SwamUserRepository swamUserRepository) {
        this.swamUserRepository = swamUserRepository;
    }

    public List<SwamUser> getUsers() {
        SwamUser user = new SwamUser("Loi");
        List<SwamUser> users = null;
        users = this.swamUserRepository.findAll();
        return users.subList(1, 10);
    }

    @Override
    public SwamUser getUser(String name) {
        Assert.notNull(name, "Name must not be null");
        return this.swamUserRepository.findByNameAllIgnoringCase(name);
    }

    @Override
    public void saveUser(SwamUser user) {
        this.swamUserRepository.save(user);
    }
}
