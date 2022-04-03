package sn.isi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sn.isi.dao.IUser;
import sn.isi.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    private IUser userdao;

    /**
     * Spring security
     */
    @RequestMapping(value = "/login")
    public String login() {

        return "logins";
    }

    @RequestMapping(value = "")
    public String home() {

        return "redirect:/logon";
    }

    @RequestMapping(value = "/")
    public String index() {

        return "redirect:/logon";
    }

    @RequestMapping(value = "/logon")
    public String logon(HttpServletRequest req, HttpServletResponse resp) {

        return "redirect:/accueil";
    }

    @RequestMapping(value = "/accueil")
    public String accueil(HttpServletRequest req, HttpServletResponse resp, Model map) {
        String email = req.getRemoteUser();
        User user = userdao.getByEmail(email);
        map.addAttribute("user", user);
        return "accueil";
    }
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }

    @RequestMapping(value="/403")
    public String accessDenied(){
        return "403";
    }


}
