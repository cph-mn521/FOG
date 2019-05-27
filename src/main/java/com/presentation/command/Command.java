package com.presentation.command;

import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {

    private static final HashMap<String, Command> commands;

    //when class is loaded the static initialiser will run
    // avoiding race condition
    static {
        commands = new HashMap<>();
        commands.put("Customer", new CustomerActions());
        commands.put("LoginCustomer", new LoginCustomer());
        commands.put("Register", new Register());
        commands.put("JSTEST", new JSTEST());
        commands.put("ShowDrawing", new ShowDrawing());
        commands.put("DownloadPDF", new DownloadPDF());
        commands.put("Login", new Login());
        commands.put("Logout", new Logout());
        commands.put("Sidebar", new sidebar());
        commands.put("getJSP", new getJSP());
        commands.put("CaseAction", new CaseAction());
        commands.put("profile", new editProfile());
        commands.put("ComponentCommand", new ComponentCommand());
        commands.put("CustomerCommand", new CustomerCommand());
        commands.put("EmployeeCommand", new EmployeeCommand());
        commands.put("OrderCommand", new OrderCommand());

    }

    public static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        return commands.getOrDefault(commandName, new UnknownCommand());
    }

    abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception;

}
