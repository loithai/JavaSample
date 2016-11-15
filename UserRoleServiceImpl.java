package sample.data.jpa.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import sample.data.jpa.domain.UserRole;

import java.util.List;

/**
 * Created by loi on 11/14/16.
 */
@Component("userRoleService")
@Transactional
class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRole> getRoles() {
        UserRole role = new UserRole("Loi");
        List<UserRole> roles = null;
        roles = this.userRoleRepository.findAll();
        return roles.subList(1, 10);
    }

    @Override
    public UserRole getRole(String name) {
        Assert.notNull(name, "Name must not be null");
        return this.userRoleRepository.findByNameAllIgnoringCase(name);
    }

    @Override
    public void saveRole(UserRole role) {
        this.userRoleRepository.save(role);
    }
}
