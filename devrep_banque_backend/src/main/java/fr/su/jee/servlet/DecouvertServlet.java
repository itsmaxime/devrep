package fr.su.jee.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class DecouvertServlet
 */
@WebServlet("/decouvert")
public class DecouvertServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory.getLogger(DecouvertServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String clientOrigin = request.getHeader("origin");
		response.setHeader("Access-Control-Allow-Origin", clientOrigin);

		//Lecture du montant de decouvert dans le fichier montant.txt
		URL path = DecouvertServlet.class.getResource("montant.txt");
		File file = new File(path.getFile());
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str = br.readLine();		
		while(br.readLine() != null);
		br.close();

		logger.info("recuperer le montant de decouvert actuel : " + str);
		
		//Retourner la valeur lue au client
		response.getWriter().println(str);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String clientOrigin = request.getHeader("origin");
		response.setHeader("Access-Control-Allow-Origin", clientOrigin);
		
		//Recuperer les parametres de la requete POST du client
		String requestData = request.getReader().lines().collect(Collectors.joining());
		
		logger.info("mise a jour du montant de decouvert : " + requestData);
		
		//Mise a jour du montant, ecriture de la nouvelle valeur dans le fichier montant.txt
		URL path = DecouvertServlet.class.getResource("montant.txt");
		File file = new File(path.getFile());
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write(requestData);
		bw.close();
				
		response.getWriter().println(requestData);
	}

}
