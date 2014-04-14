package eu.semagrow.stack.webapp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public ModelAndView login(HttpServletResponse response) throws IOException{
        ModelAndView mav = new ModelAndView("auth/login");
        return mav;
    }
    
    @RequestMapping(value="/authenticate", method=RequestMethod.GET)
    public void authenticate(HttpServletResponse response, HttpServletRequest request) throws IOException{
        response.getWriter().print(request.getUserPrincipal());
    }    
    
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public void logout(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        request.getSession().invalidate();
        response.sendError(401);
        response.setStatus(401);        
        response.addHeader("WWW-Authenticate", "Basic realm='Authentication required'");
        System.out.println("logout "+request.getUserPrincipal());
    }    
}
