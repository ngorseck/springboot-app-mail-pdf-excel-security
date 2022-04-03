package sn.isi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sn.isi.dao.IUser;
import sn.isi.entities.User;
import sn.isi.utils.EmailService;
import sn.isi.utils.ExcelUserListReportView;
import sn.isi.utils.PdfUserListReportView;
import sn.isi.utils.QrCodeService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private IUser userdao;
    @Autowired
    private QrCodeService qrCodeService;
    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/Rapport/excel")
    public ModelAndView createExcel(HttpServletRequest request, HttpServletResponse response) {
        //create data
        List<User> list = userdao.findAll();

        return new ModelAndView(new ExcelUserListReportView(), "usersList", list);
    }

    @RequestMapping(value = "/Rapport/qrCode")
    public ModelAndView qrCode(HttpServletRequest request, HttpServletResponse response) {
        //create data
        List<User> list = userdao.findAll();
        String data = "";
        for (User user : list) {
            data = data + user.getPrenom() + " " + user.getNom() + " " + user.getEmail() + "\n";
        }
        try {
            qrCodeService.generateQrCode(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ModelAndView();
    }
    @RequestMapping(value = "/Rapport/email")
    public ModelAndView email(HttpServletRequest request, HttpServletResponse response) {

        emailService.sendSimpleMessage("ngorsecka@gmail.com",
                "Spring boot", "Bonjour, nous testons spirng mailer");

        return new ModelAndView();
    }
    @RequestMapping(value = "/Rapport/pdf")
    public ModelAndView createPdf(HttpServletRequest request, HttpServletResponse response) {
        String typeReport = request.getParameter("type");

        //create data
        List<User> list = userdao.findAll();

        return new ModelAndView(new PdfUserListReportView(), "usersList", list);
    }
}
