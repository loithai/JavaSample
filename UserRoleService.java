package sample.data.jpa.service;

import sample.data.jpa.domain.UserRole;

import java.util.List;

/**
 * Created by loi on 11/14/16.
 */
public interface UserRoleService {

    List<UserRole> getRoles();

    UserRole getRole(String name);

    void saveRole(UserRole role);

}
