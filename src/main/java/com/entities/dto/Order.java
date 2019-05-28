package com.entities.dto;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Martin
 */
public class Order implements java.io.Serializable {

    private int order_id, customer_id;
    private float total_price;
    private Date order_receive_date, order_send_date;
    private String customer_address, order_status;

    public Order(int order_id, int customer_id, Date order_receive_date, Date order_send_date, String customer_address, String order_status, float total_price) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.order_receive_date = order_receive_date;
        this.order_send_date = order_send_date;
        this.customer_address = customer_address;
        this.order_status = order_status;
        this.total_price = total_price;
    }

    public Order(int customer_id, Date order_receive_date, Date order_send_date, String customer_address, String order_status, float total_price) {
        this.customer_id = customer_id;
        this.order_receive_date = order_receive_date;
        this.order_send_date = order_send_date;
        this.customer_address = customer_address;
        this.order_status = order_status;
        this.total_price = total_price;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Date getOrder_receive_date() {
        return order_receive_date;
    }

    public void setOrder_receive_date(Date order_receive_date) {
        this.order_receive_date = order_receive_date;
    }

    public Date getOrder_send_date() {
        return order_send_date;
    }

    public void setOrder_send_date(Date order_send_date) {
        this.order_send_date = order_send_date;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.order_id;
        hash = 71 * hash + this.customer_id;
        hash = 71 * hash + Objects.hashCode(this.order_receive_date);
        hash = 71 * hash + Objects.hashCode(this.order_send_date);
        hash = 71 * hash + Objects.hashCode(this.customer_address);
        hash = 71 * hash + Objects.hashCode(this.order_status);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            System.out.println("2");
            return false;
        }
        if (getClass() != obj.getClass()) {
            System.out.println("3");
            return false;
        }
        final Order other = (Order) obj;
        if (this.order_id != other.order_id) {
            System.out.println("4");
            return false;
        }
        if (this.customer_id != other.customer_id) {
            System.out.println("6");
            return false;
        }
        if (!Objects.equals(this.customer_address, other.customer_address)) {
            System.out.println("7");
            return false;
        }
        if (!Objects.equals(this.order_status, other.order_status)) {
            System.out.println("8");
            return false;
        }
        if (this.order_send_date.equals(other.order_send_date)) {
            System.out.println("10");
            return false;
        }
        if (this.order_receive_date.equals(other.order_receive_date)) {
            System.out.println("9");
            return false;
        }
        if (this.total_price != other.total_price) {
            System.out.println("9");
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "order_id=" + order_id + ", customer_id=" + customer_id
                + ", order_receive_date=" + order_receive_date + ", order_send_date="
                + order_send_date + ", customer_address=" + customer_address
                + ", order_status=" + order_status + ", total_price=" + total_price + '}';
    }

}
