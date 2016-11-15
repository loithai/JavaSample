package sample.data.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import sample.data.jpa.domain.UserRole;

import java.util.List;

/**
 * Created by loi on 11/14/16.
 */
interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    Page<UserRole> findAll(Pageable pageable);

    List<UserRole> findAll();

    Page<UserRole> findByNameContainingAllIgnoringCase(String name, Pageable pageable);

    UserRole findByNameAllIgnoringCase(String name);

}
