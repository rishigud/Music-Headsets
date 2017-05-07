package music.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import music.business.User;
import music.data.UserDB;
import music.util.MailUtil;
import music.util.PasswordUtil;

public class UserController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = "/index.jsp";
        String action="";
        if (requestURI.endsWith("/deleteCookies")) {
            url = deleteCookies(request, response);
        }
        else if (requestURI.endsWith("/logout")) {
            
                url = logout(request, response); 
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = "";
        if (requestURI.endsWith("/subscribeToEmail")) {
            
                url = subscribeToEmail(request, response);
           
        }
        else if (requestURI.endsWith("/register")) {
            
                url = registerUser(request, response); 
        }else if (requestURI.endsWith("/loginuser")) {
            
                url = login(request, response); 
        }
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
 
  private String logout(HttpServletRequest request,
            HttpServletResponse response) {

        HttpSession session = request.getSession();    
        
       session.removeAttribute("user");
      
        
        return "/index.jsp";
    }    
    
    
    private String login(HttpServletRequest request,
            HttpServletResponse response) {

        HttpSession session = request.getSession();    
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String url ="";
        String hashedUser = "";
        String salt="";
        String verifyPassword="";
        User user = (User) UserDB.selectUser(email); 
        try{
     verifyPassword =  user.getPassword();
        }catch(Exception e){
            this.log("error");
        }
      
      if(user!=null && verifyPassword!=null){
      String hashedPassword = user.getHashCode();
     
      salt = user.getSalt();
     String hashCode = user.getHashCode();
     
    
     
        try {
            hashedUser = PasswordUtil.hashPassword(password);
           
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
     if(hashedUser.matches(hashedPassword)){
         session.setAttribute("user", user);
         url = "/index.jsp";
     }
      }
     else{
         url = "/login/loginError.jsp";
        
         this.log(hashedUser);
         this.log(salt);
         this.log(verifyPassword);
     }
         
    return url;
    }  
    
private String registerUser(HttpServletRequest request,
            HttpServletResponse response) {

        HttpSession session = request.getSession();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
         String hashedPassword = null ;
        String salt = "";
        String saltedAndHashedPassword = null;
        try{
           hashedPassword = PasswordUtil.hashPassword(password);
           salt = PasswordUtil.getSalt();
           saltedAndHashedPassword = PasswordUtil.hashPassword(password + salt);
           user.setPassword(saltedAndHashedPassword);
           user.setSalt(salt);
           user.setHashCode(hashedPassword);
        }
        catch(NoSuchAlgorithmException e){
            String message = e.toString();
            this.log(message);
        }
        
      
        
        if (UserDB.emailExists(email)) {
            UserDB.update(user);
        } else {
            UserDB.insert(user);
        }

        session.setAttribute("user", user);
        

        Cookie emailCookie = new Cookie("emailCookie", email);
        emailCookie.setMaxAge(60 * 60 * 24 * 365 * 2);
        emailCookie.setPath("/");
        response.addCookie(emailCookie);
     
      
        return "/index.jsp";
    }    
    private String deleteCookies(HttpServletRequest request,
            HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);  //delete the cookie
            cookie.setPath("/");  //entire application
            response.addCookie(cookie);
        }
        return "/delete_cookies.jsp";
    }

    private String subscribeToEmail(HttpServletRequest request,
            HttpServletResponse response) {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        request.setAttribute("user", user);

        String url;
        String message;
        //check that email address doesn't already exist
        if (UserDB.emailExists(email)) {
            message = "This email address already exists. <br>"
                    + "Please enter another email address.";
            request.setAttribute("message", message);
            url = "/email/index.jsp";
        } else {
            UserDB.insert(user);
            message = "";
            request.setAttribute("message", message);
            try{
            MailUtil.sendMail(email, "rishigud@gmail.com", "thanks for subscribing", "thank you", false);
            }catch(MessagingException e){
                
                String errorMessage = e.getMessage();
                request.setAttribute("errormessage", errorMessage);
                this.log(errorMessage);
            }
            url = "/email/thanks.jsp";
        }
        return url;
    }
}