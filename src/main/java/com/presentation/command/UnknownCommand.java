package com.presentation.command;

import com.exceptions.LoginException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The purpose of UnknownCommand is to...
 *
 * @author kasper
 */
public class UnknownCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        String msg = "Unknown command. Contact IT";
        throw new LoginException(msg);
    }

}
