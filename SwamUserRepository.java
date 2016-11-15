package sample.data.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import sample.data.jpa.domain.SwamUser;

import java.util.List;

/**
 * Created by loi on 11/14/16.
 */
interface SwamUserRepository extends CrudRepository<SwamUser, Long> {

    Page<SwamUser> findAll(Pageable pageable);

    List<SwamUser> findAll();

    Page<SwamUser> findByNameContainingAllIgnoringCase(String name, Pageable pageable);

    SwamUser findByNameAllIgnoringCase(String name);

}
