package com.blackiceinc.era.web;

import com.blackiceinc.era.services.security.SecurityUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
public class HomeController {

    @Value("${ibmcognos.url:http://10.50.143.8/ibmcognos/cgi-bin/cognos.cgi?b_action=xts.run&m=portal/cc.xts&m_folder=i7C5187CCA1EB4E2B91D3A32B2F95C5BF}")
    private String ibmCognosUrl;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String admin(Locale locale, HttpServletRequest request, HttpServletResponse response, Model model) {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        response.addCookie(new Cookie("XSRF-TOKEN", token.getToken()));
        model.addAttribute("serverTime", formattedDate);

        String userRole = SecurityUtils.getUserRole();
        model.addAttribute("user_role", userRole);
        model.addAttribute("ibmcognos_url", ibmCognosUrl);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("role", userRole);
        resultMap.put("username", SecurityUtils.getCurrentLogin());
        response.addCookie(new Cookie("user", new JSONObject(resultMap).toJSONString()));

        return "home";
    }

    //Spring Security see this :
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView welcomePage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Custom Login Form");
        model.addObject("message", "This is welcome page!");
        model.setViewName("login");
        return model;

    }

}
