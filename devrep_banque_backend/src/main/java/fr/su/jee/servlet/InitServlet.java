package fr.su.jee.servlet;

import fr.su.jee.db.connection.DatabaseConnectionFactory;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value="/initServlet", loadOnStartup=1)
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public InitServlet() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
        try {
			DatabaseConnectionFactory.getConnectionFactory().init();
		} catch (IOException e) {
			config.getServletContext().log(e.getLocalizedMessage(),e);
		}
    }    

}