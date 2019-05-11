package com.presentation.command;

import java.util.HashMap;
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
        commands.put("Sidebar",new sidebar());
        commands.put("getJSP",new getJSP());
        
        commands.put("AddOrder", new AddOrder());
        commands.put("Orders", new OrderHistory());
        commands.put("OrderHistory", new OrderHistory());
        commands.put("ShowOrders", new ShowOrders());
        commands.put("ShowOrder", new ShowOrder());
        commands.put("ShowBOM", new ShowBOM());
        
        commands.put("ShowComponents", new ShowComponents());
        commands.put("ChangingComponent", new ChangingComponent());
        commands.put("ChangedComponent", new ChangedComponent());
        commands.put("NewFormComponent", new NewFormComponent());
        commands.put("NewComponent", new NewComponent());
        
        commands.put("ShowCustomers", new ChangingCustomer());
        commands.put("ChangingCustomer", new ChangingCustomer());
        commands.put("ChangedCustomer", new ChangedCustomer());
        commands.put("NewFormCustomer", new NewFormCustomer());
        commands.put("NewCustomer", new NewCustomer());
        
        commands.put("ShowEmployees", new ShowEmployees());
        commands.put("ChangingEmployee", new ChangingEmployee());
        commands.put("ChangedEmployee", new ChangedEmployee());
        commands.put("NewFormEmployee", new NewFormEmployee());
        commands.put("NewEmployee", new NewEmployee());

    }

    public static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());
    }

    abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception;

}
