package servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import entities.TipoUsuario;
import entities.Usuario;
import logic.LogicUsuario;


/**
 * Servlet implementation class Register
 */
@WebServlet({ "/Register", "/register" })
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user = new Usuario();
		TipoUsuario tu = new TipoUsuario();
		LogicUsuario lu = new LogicUsuario();
		
		user.setNombre(request.getParameter("name"));
		user.setApellido(request.getParameter("surname"));
		user.setNombreUsuario(request.getParameter("username"));
		user.setEmail(request.getParameter("mail"));
		user.setClave(request.getParameter("password"));
		tu.setId_TipoUsuario(2); //se registra como usuario normal
		user.setTipoUsuario(tu);
		
		//validar que no exista el mismo username
		if(checkUsername(user)) {
			//ya existe
			request.setAttribute("msg", "Nombre de usuario ya existente. Por favor elija uno distinto");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		else {
			//valido
			lu.add(user);
			sendMail(user);
			request.setAttribute("msg", "Registro exitoso");
			request.getRequestDispatcher("index.jsp").forward(request, response);

		}
	}
	
	private boolean checkUsername(Usuario us) {
		LogicUsuario lu = new LogicUsuario();
		LinkedList<Usuario> users = lu.getAll();
		Boolean check = false;
		
		for(Usuario u : users) {
			if(u.getNombreUsuario().equals(us.getNombreUsuario())) {
				check = true;
			}
		}
		return check;
	}

	
	private void sendMail(Usuario usr) {
		
		String to = usr.getEmail();
        String from = "no-reply@obtcomputacion.com.ar";
        
        final String username = "obtcomputacion@gmail.com";
        final String password = "computacionobt123";

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));

            message.setSubject("Bienvenido a OBT computación");

            message.setText("Hola "+usr.getNombre()+" "+usr.getApellido()+". Te has registrado con éxito. Ya puedes examinar nuestro catálogo y comprar lo que desees.");

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}


}
