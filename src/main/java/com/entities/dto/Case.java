package com.entities.dto;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author nille
 */
public class Case implements java.io.Serializable {

    int caseId, orderId, customerId, employeeId;
    String status, msg_owner, msg_status, type;
    Date timestamp;

    public Case(int caseId, int orderId, int customerId, int employeeId, String status) {
        this.caseId = caseId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.status = status;
    }

    public Case(int orderId, int customerId, int employeeId, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.status = status;
    }

    public Case(int orderId, int customerId, int employeeId, String type, String msg_owner) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.type = type;
        this.msg_owner = msg_owner;
    }

    //Overload:
    public Case(int caseId, Date timestamp, int orderId, int customerId,
            int employeeId, String status, String msg_owner, String msg_status,
            String type) {
        this.caseId = caseId;
        this.timestamp = timestamp;
        this.orderId = orderId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.status = status;
        this.msg_owner = msg_owner;
        this.msg_status = msg_status;
        this.type=type;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public void setMsg_owner(String msg_owner) {
        this.msg_owner = msg_owner;
    }

    public void setMsg_status(String msg_status) {
        this.msg_status = msg_status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg_owner() {
        return msg_owner;
    }

    public String getMsg_status() {
        return msg_status;
    }

    public String getType() {
        return type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.caseId;
        hash = 71 * hash + this.orderId;
        hash = 71 * hash + this.customerId;
        hash = 71 * hash + this.employeeId;
        hash = 71 * hash + Objects.hashCode(this.status);
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
        final Case other = (Case) obj;
        if (this.caseId != other.caseId) {
            return false;
        }
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.customerId != other.customerId) {
            return false;
        }
        if (this.employeeId != other.employeeId) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Case{" + "caseId=" + caseId + ", orderId=" + orderId + ", customerId=" + customerId + ", employeeId=" + employeeId + ", status=" + status + '}';
    }

}
