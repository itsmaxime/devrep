package fr.su.jee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.su.jee.beans.Compte;
import fr.su.jee.dao.CompteDAO;

/**
 * Servlet implementation class CompteServlet
 */
@WebServlet("/compte")
public class CompteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(CompteServlet.class);
	
	private static int numeroCompteClient = -1;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Methode GET non utilisee
		logger.info("hello get");
		
		PrintWriter out = response.getWriter();
		out.println("a mettre en place");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String clientOrigin = request.getHeader("origin");
		response.setHeader("Access-Control-Allow-Origin", clientOrigin);
		
		PrintWriter out = response.getWriter();
		
		String requestData = request.getReader().lines().collect(Collectors.joining());

		//split la requete pour savoir quel cas traiter
		String reqSplit[] = requestData.split("\\+");
		
		if(reqSplit[0].equals("creation")) {
			//Creation d'un nouveau compte par l'admin
			//creation+email+numeroCompte
			String email = reqSplit[1];
			int numeroCompte = Integer.parseInt(reqSplit[2]);
			Compte compte = new Compte();
			compte.setEmail(email);
			compte.setBalance(0);
			compte.setNumeroCompte(numeroCompte);
			try {
				CompteDAO.creationCompte(compte);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			logger.info("creation du compte : " + numeroCompte + ", email : " + email);
		}
		
		if(reqSplit[0].equals("first")) {
			//Premiere connexion d'un client sur son compte
			//Recuperer le numero de compte et balance
			String email = reqSplit[1];
			try {
				Compte compte = CompteDAO.getCompte(email);
				//Lors de la premiere connexion du client, recuperer et garder ton numero de compte dans une variable static
				numeroCompteClient = compte.getNumeroCompte();
				String ret = numeroCompteClient + "+" + compte.getBalance();
				out.println(ret);
				logger.info("connexion de : " + email + ", acces au compte : " + numeroCompteClient);
				return;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(reqSplit[0].equals("deposit")) {
			//Depot de 200 sur le compte
			logger.info("depot de 200 sur le compte numero : " + numeroCompteClient);
			try {
				Compte compte = CompteDAO.updateCompte(numeroCompteClient, reqSplit[0], 200);
				out.println(compte.getBalance());
				return;
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
		if(reqSplit[0].equals("withdraw")) {
			//Retrait de 200 sur le compte
			logger.info("retrait de 200 sur le compte numero : " + numeroCompteClient);
			try {
				Compte compte = CompteDAO.updateCompte(numeroCompteClient, reqSplit[0], 200);
				out.println(compte.getBalance());
				return;
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
		if(reqSplit[0].equals("transfert")) {
			//Operation de transfert du compte client vers un destinataire en precisant le montant
			int dest = Integer.parseInt(reqSplit[1]);
			int montant = Integer.parseInt(reqSplit[2]);
			
			logger.info("operation de transfert de " + montant + " du compte " + numeroCompteClient + " vers le compte " + dest);
			
			try {
				Compte compte = CompteDAO.transfert(numeroCompteClient, dest, montant);
				out.println(compte.getBalance());
				return;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(reqSplit[0].equals("fermeture")) {
			//Fermeture, suppression d'un compte client dans la base de donnees
			int numero = Integer.parseInt(reqSplit[1]);
			
			logger.info("fermeture du compte " + numero);
			
			try {
				CompteDAO.deleteCompte(numero);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}