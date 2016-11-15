package sample.data.jpa.domain;

import javax.persistence.*;

/**
 * Created by loi on 11/14/16.
 */
@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private SwamUser user;

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

    public SwamUser getUser() {
        return user;
    }

    public void setUser(SwamUser user) {
        this.user = user;
    }

    protected UserRole() {
    }

    public UserRole(String name) {
        this.name = name;
    }
}
