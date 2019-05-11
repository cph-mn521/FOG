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
        commands.put("AddOrder", new AddOrder());
        commands.put("Orders", new OrderHistory());
        commands.put("JSTEST",new JSTEST());
        commands.put("OrderHistory", new OrderHistory());
        commands.put("ShowBOM", new ShowBOM());
        commands.put("ShowOrders", new ShowOrders());
        commands.put("ShowOrder", new ShowOrder());
        commands.put("ShowComponents", new ShowComponents());
        commands.put("DownloadPDF", new DownloadPDF());
        commands.put("Login",new Login());
        commands.put("Sidebar",new sidebar());
        commands.put("getJSP",new getJSP());
        commands.put("ChangingComponents", new ChangingComponents());
        commands.put("ChangedComponents", new ChangedComponents());
        commands.put("ChangedCustomers", new ChangedCustomers());
        commands.put("ChangedEmployee", new ChangedEmployee());
        commands.put("ChangingComponents", new ChangingComponents());
        commands.put("ChangingCustomers", new ChangingCustomers());
        commands.put("ChangingEmployee", new ChangingEmployee());
        commands.put("ShowDrawing", new ShowDrawing());
        commands.put("ShowDrawing", new ShowDrawing());

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
