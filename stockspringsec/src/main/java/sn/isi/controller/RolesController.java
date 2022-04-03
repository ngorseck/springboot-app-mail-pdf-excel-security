package sn.isi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sn.isi.dao.IRoles;
import sn.isi.dao.IUser;
import sn.isi.entities.Roles;
import sn.isi.entities.User;
@Controller
public class RolesController {

    @Autowired
    private IRoles rolesdao;

    @PostMapping("/admin/roles/save")
    public String save (Roles roles) {
        rolesdao.save(roles);
        return "redict:/admin/roles";
    }

    @GetMapping("/admin/roles")
    public String list (Model map) {
        map.addAttribute("roles", new Roles());
        map.addAttribute("roles_list", rolesdao.findAll());
        return "/admin/roles/list";
    }
}
