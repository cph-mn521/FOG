package com.presentation.command;

import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("LoginCustomer", new LoginCustomer());
        commands.put("Register", new Register());
        commands.put("JSTEST",new JSTEST());
        commands.put("ShowDrawing", new ShowDrawing());
        commands.put("DownloadPDF", new DownloadPDF());
        commands.put("Login",new Login());
        commands.put("Logout",new Logout());
        commands.put("Sidebar",new sidebar());
        commands.put("getJSP",new getJSP());
        
        commands.put("ComponentCommand",new ComponentCommand());
        commands.put("CustomerCommand",new CustomerCommand());
        commands.put("EmployeeCommand",new EmployeeCommand());
        commands.put("OrderCommand",new OrderCommand());
    }

    public static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());
    }

    abstract String execute(ServletContext context, HttpServletRequest request, HttpServletResponse response)
            throws Exception;

}
