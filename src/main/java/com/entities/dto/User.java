package com.entities.dto;

import java.util.Objects;

/**
 *
 * @author Martin, Martin Bøgh
 */
public class User {

    String phone_number;
    String name, email, password;

    public User(String name, String email, String password, String phone_number) {
        this.phone_number = phone_number;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Overloaded constructor for when not all information is needed. Mainly
     * for use for displaying the user.
     *
     * @param name
     * @param email
     * @param phone_number
     */
    public User(String name, String email, String phone_number) {
        this.phone_number = phone_number;
        this.name = name;
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.phone_number, other.phone_number)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

}
