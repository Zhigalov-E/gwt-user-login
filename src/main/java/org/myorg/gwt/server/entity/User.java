package org.myorg.gwt.server.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_users", uniqueConstraints = {@UniqueConstraint(columnNames = "ID"), @UniqueConstraint(columnNames = "LOGIN")})
public class User implements Serializable {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private int id;
    @Column(name = "NAME", unique = false, nullable = false, length = 100)
    private String name;
    @Column(name = "LOGIN", unique = true, nullable = false, length = 100)
    private String login;
    @Column(name = "HASH_PWD", nullable = false, length = 60)
    private String hashPwd;

    public User(int id, String name, String login, String hashPwd) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.hashPwd = hashPwd;
    }

    public User (){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashPwd() {
        return hashPwd;
    }

    public void setHashPwd(String hashPwd) {
        this.hashPwd = hashPwd;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return getId() == user.getId();

    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", hashPwd='" + hashPwd + '\'' +
                '}';
    }

}
