package sample.data.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by loi on 11/14/16.
 */
@Entity
public class SwamUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //    @Column(columnDefinition = "serial")
    @Column(name = "USER_ID", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserRole> roles = new HashSet<UserRole>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    protected SwamUser() {
    }

    public SwamUser(String name) {
        this.name = name;
    }
}
