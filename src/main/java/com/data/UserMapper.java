package com.data;

import com.enumerations.DBURL;
import com.entities.dto.Customer;
import com.entities.dto.Employee;
import com.entities.dto.User;
import com.exceptions.DataException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin, Martin Bøgh, Niels
 */
public class UserMapper {

    private Connection con;
    private PreparedStatement ps = null;
    private ResultSet rs;
    private DBURL dbURL;

    public UserMapper(DBURL dbURL) throws DataException {
        this.dbURL = dbURL;
    }

    /**
     * Method for fetching a user from the database, Requires both email and
     * password to match Sends an error to be interpreted in higher layer.
     *
     * * uses prepared statements to avoid SQL injects.
     *
     * @Author Martin, Martin Bøgh
     * @param email
     * @param password
     * @return User object containing the corresponding to the query.
     * @throws SQLException
     */
    Customer getCustomer(String email, String password) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "SELECT `customer_id`, `name`, `phone_number` FROM `customers` "
                    + "WHERE `email`=? AND `password`=?";
            ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                int customer_id = rs.getInt("customer_id");
                String name = rs.getString("name");
                String phone_number = rs.getString("phone_number");
                Customer cust = new Customer(customer_id, name, email, password, phone_number);
                return cust;
            } else {
                throw new DataException("User (customer) not found");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }
    }

    Customer getCustomer(int id) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "SELECT customer_id, `name`, `email`, `password`, `phone_number` FROM `customers` "
                    + "WHERE `customer_id`=?";
            ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                int customer_id = rs.getInt("customer_id");
                String name = rs.getString("name");
                String phone_number = rs.getString("phone_number");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Customer cust = new Customer(customer_id, name, email, password, phone_number);
                return cust;
            } else {
                throw new DataException("User (customer) not found");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }
    }
    
    Customer getCustomerFromId(int ID) throws DataException{
          try
        {
            con = Connector.connection(dbURL);
            String SQL = "SELECT * FROM customers "
                    + "WHERE `customer_id`=?";
            ps = con.prepareStatement(SQL);
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            if (rs.next())
            {
                String name = rs.getString("name");
                String phone_number = rs.getString("phone_number");
                String password = rs.getString("password");
                String email = rs.getString("email");
                return new Customer(ID, name, email, password, phone_number);
            } else
            {
                throw new DataException("User (customer) not found");
            }
        } catch (ClassNotFoundException | SQLException ex)
        {
            throw new DataException(ex.getMessage());
        } finally
        {
            Connector.CloseConnection(rs, ps, con);
        }      
    }
    
    

    /**
     * Method for adding a new user entry to the database.
     *
     * Takes a user entity and then converts it to a SQL statement.
     *
     * * uses prepared statements to avoid SQL injects.
     *
     * @Author Niels
     * @param user
     * @throws SQLException
     */
    void createCustomer(Customer customer) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "INSERT INTO `customers` (name, email, password, phone_number) VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(SQL);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPassword());
            ps.setString(4, customer.getPhone_number());
            ps.executeUpdate();

        } catch (NullPointerException | ClassNotFoundException | SQLException e) {
            throw new DataException(e.getMessage());
        } finally {
            Connector.CloseConnection(ps, con);
        }

    }

    /**
     * Method for updating a user in the database
     *
     * Takes user and a newUser entity and updates the old user to the newUser.
     *
     * @deprecated use getCustomer or getEmployee.
     * @Author Niels
     * @param user
     * @param newUser
     * @throws SQLException
     */
    void updateUser(User user, User newUser) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL, table;
            String ranked = "";
            int n = 0;
            if (newUser instanceof Customer) {
                table = "`customers`";
            } else {
                table = "`employees`";
                ranked = ", `rank` = ? ";
                n++;
            }
            SQL = "UPDATE " + table + " SET `email` = ?, `password`= ?,"
                    + " `name` = ? " + ranked
                    + "WHERE User.email = ? AND User.password = ?";

            //name, email, password, phone_number
            // name, email, password, phone_number, rank
            ps = con.prepareStatement(SQL);

            ps.setString(1, newUser.getEmail());
            ps.setString(2, newUser.getPassword());
            ps.setString(3, newUser.getName());
            if (n > 0) {
                ps.setString(4, ((Employee) newUser).getRank());
            }
            ps.setString(4 + n, user.getEmail());
            ps.setString(5 + n, user.getPassword());
            ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(ps, con);
        }
    }

    /**
     * Deletes the passed user from the database.
     *
     * @deprecated
     * @Author Niels
     * @param user
     * @throws SQLException
     */
    void deleteUser(User user) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "DELETE FROM ? WHERE User.email = ?"
                    + " AND User.password = ?";
            ps = con.prepareStatement(SQL);
            if (user instanceof Customer) {
                ps.setString(1, "Customers");
            } else {
                ps.setString(1, "Employees");
            }
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(ps, con);
        }
    }

    void deleteCustomer(Customer customer) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "DELETE FROM `customers` WHERE `customers`.`email` = ? "
                    + "AND `customers`.`password` = ?";
            ps = con.prepareStatement(SQL);
            ps.setString(1, customer.getEmail());
            ps.setString(2, customer.getPassword());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DataException(e.getMessage());
        } finally {
            Connector.CloseConnection(ps, con);
        }
    }

    /**
     *
     * @param customer
     * @param newCustomer
     * @throws SQLException
     */
    void updateCustomer(Customer customer, Customer newCustomer) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "UPDATE `customers` SET `email`=?, `name` = ?, `password`= ?, `phone_number`= ?"
                    + " WHERE `email` = ? AND `password`= ?";
            ps = con.prepareStatement(SQL);
            ps.setString(1, newCustomer.getEmail());
            ps.setString(2, newCustomer.getName());
            ps.setString(3, newCustomer.getPassword());
            ps.setString(4, newCustomer.getPhone_number());
            ps.setString(5, customer.getEmail());
            ps.setString(6, customer.getPassword());
            int succes = ps.executeUpdate();
            ps.executeUpdate();
        } catch (NullPointerException | ClassNotFoundException | SQLException e) {
            throw new DataException(e.getMessage());
        } finally {
            Connector.CloseConnection(ps, con);
        }
    }

    /**
     *
     * @author Brandstrup
     * @return a List<Customer> containing all the customers in the database
     * @throws DataException
     */
    public List<Customer> getAllCustomers() throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL
                    = "SELECT *"
                    + " FROM `fogcarport`.`customers`;";

            List<Customer> list = new ArrayList();
            ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int customer_id = rs.getInt("customer_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String phone_number = rs.getString("phone_number");

                list.add(new Customer(customer_id, name, email, password, phone_number));
            }

            return list;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }
    }

    /**
     *
     * @param email
     * @param password
     * @return
     * @throws DataException
     * @throws SQLException
     */
    Employee getEmployee(String email, String password) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "SELECT * FROM `employees` "
                    + "WHERE `email`=? AND `password`=?";
            ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                int employee_id = rs.getInt("employee_id");
                String name = rs.getString("name");
                String phone_number = rs.getString("phone_number");
                String rank = rs.getString("rank");
                Employee emp = new Employee(employee_id, name, phone_number, email, password, rank);
                return emp;
            } else {
                throw new DataException("Employee not found");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }
    }

    Employee getEmployee(int id) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "SELECT * FROM `employees` "
                    + "WHERE `employee_id`=?";
            ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                int employee_id = rs.getInt("employee_id");
                String name = rs.getString("name");
                String phone_number = rs.getString("phone_number");
                String rank = rs.getString("rank");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Employee emp = new Employee(employee_id, name, phone_number, email, password, rank);
                return emp;
            } else {
                throw new DataException("Employee not found");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }
    }

    /**
     *
     * @param emp
     * @throws SQLException
     */
    void createEmployee(Employee emp) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "INSERT INTO `employees` (`name`, `email`, `password`, `phone_number`, `rank`) VALUES (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(SQL);
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getPassword());
            ps.setString(4, emp.getPhone_number());
            ps.setString(5, emp.getRank());
            ps.executeUpdate();

        } catch (NullPointerException | ClassNotFoundException | SQLException e) {
            throw new DataException(e.getMessage());
        } finally {
            Connector.CloseConnection(ps, con);
        }
    }

    /**
     *
     * @param employee
     * @param newEmployee
     * @throws SQLException
     */
    void updateEmployee(Employee employee, Employee newEmployee) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "UPDATE `employees` SET `email`= ?, "
                    + "`name` = ?, `password`= ?, "
                    + "`phone_number`= ? WHERE `email` = ? "
                    + "AND `password`= ?";
            ps = con.prepareStatement(SQL);
            ps.setString(1, newEmployee.getEmail());
            ps.setString(2, newEmployee.getName());
            ps.setString(3, newEmployee.getPassword());
            ps.setString(4, newEmployee.getPhone_number());
            ps.setString(5, employee.getEmail());
            ps.setString(6, employee.getPassword());
            ps.executeUpdate();
        } catch (NullPointerException | ClassNotFoundException | SQLException e) {
            throw new DataException(e.getMessage());
        } finally {
            Connector.CloseConnection(ps, con);
        }
    }

    /**
     *
     * @param employee
     * @throws SQLException
     */
    void deleteEmployee(Employee employee) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "DELETE FROM `employees` WHERE `employees`.`email` = ? "
                    + "AND `employees`.`password` = ?";
            ps = con.prepareStatement(SQL);
            ps.setString(1, employee.getEmail());
            ps.setString(2, employee.getPassword());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DataException(e.getMessage());
        } finally {
            Connector.CloseConnection(ps, con);
        }
    }

    /**
     *
     * @author Brandstrup
     * @return a List<Employee> containing all the employees in the database
     * @throws DataException
     */
    public List<Employee> getAllEmployees() throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL
                    = "SELECT *"
                    + " FROM `fogcarport`.`employees`;";

            List<Employee> list = new ArrayList();
            ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int employee_id = rs.getInt("employee_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String phone_number = rs.getString("phone_number");
                String rank = rs.getString("rank");

                list.add(new Employee(employee_id, name, phone_number, email, password, rank));
            }

            return list;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }
    }
}
