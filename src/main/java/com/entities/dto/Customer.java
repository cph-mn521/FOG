package com.entities.dto;

import java.util.Objects;

/**
 *
 * @author Martin Bøgh
 */
public class Customer extends User {

    private int customer_id;
    private String name, email_address, password, phone_number;

    public Customer(int customer_id, String name, String email_address, String password, String phone_number) {
        super(name, email_address, password, phone_number);
        this.customer_id = customer_id;
    }

    public Customer(String name, String email_address, String password, String phone_number) {
        super(name, email_address, password, phone_number);
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "Customer{" + "customer_id=" + customer_id + ", phone_number=" + phone_number + ", name=" + name + ", email_address=" + email_address + ", password=" + password + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Customer other = (Customer) obj;
        if (this.customer_id != other.customer_id) {
            return false;
        }
        if (this.phone_number != other.phone_number) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email_address, other.email_address)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;

    }

}
