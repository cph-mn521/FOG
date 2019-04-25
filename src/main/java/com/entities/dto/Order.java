package com.entities.dto;

import java.sql.Date;

/**
 *
 * @author Martin
 */
public class Order {

    private int order_id, carport_id, customer_id, bill_id;
    private Date order_recieve_date, order_send_date;
    private String customer_address, order_status;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCarport_id() {
        return carport_id;
    }

    public void setCarport_id(int carport_id) {
        this.carport_id = carport_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public Date getOrder_recieve_date() {
        return order_recieve_date;
    }

    public void setOrder_recieve_date(Date order_recieve_date) {
        this.order_recieve_date = order_recieve_date;
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

    public Order(int order_id, int carport_id, int customer_id, int bill_id, Date order_recieve_date, Date order_send_date, String customer_address, String order_status) {
        this.order_id = order_id;
        this.carport_id = carport_id;
        this.customer_id = customer_id;
        this.bill_id = bill_id;
        this.order_recieve_date = order_recieve_date;
        this.order_send_date = order_send_date;
        this.customer_address = customer_address;
        this.order_status = order_status;
    }

}
