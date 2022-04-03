package sn.isi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sn.isi.dao.IUser;
import sn.isi.entities.User;

@Controller
public class UserController {
    @Autowired
    private IUser userdao;

    @PostMapping("/admin/users/save")
    public String save (User user) {
        userdao.save(user);
        return "redict:/admin/users";
    }

    @GetMapping("/admin/users")
    public String list (Model map) {
        map.addAttribute("user", new User());
        map.addAttribute("users_list", userdao.findAll());
        return "/admin/users/list";
    }

}
