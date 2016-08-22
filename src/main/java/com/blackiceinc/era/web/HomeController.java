package com.blackiceinc.era.web;

import com.blackiceinc.era.persistence.erau.model.ConfigApp;
import com.blackiceinc.era.persistence.erau.repository.ConfigAppRepository;
import com.blackiceinc.era.services.security.SecurityUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    private static final String TRUE = "true";
    private static final String IBMCOGNOS_URL = "ibmcognos.url";
    private static final String EMPTY_STRING = "";
    private static Logger log = LoggerFactory.getLogger(HomeController.class);

    @Value("${ibmcognos.url:http://10.50.143.8/ibmcognos/cgi-bin/cognos.cgi?b_action=xts.run&m=portal/cc.xts&m_folder=i7C5187CCA1EB4E2B91D3A32B2F95C5BF}")
    private String ibmCognosUrl;

    @Value("${era.app.config.enabled:false}")
    private String eraAppConfigEnabled;

    @Value("${era.app.version:0.0.0-SNAPSHOT}")
    private String version;

    @Autowired
    private ConfigAppRepository configAppRepository;

    @RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
    public String admin(Locale locale, HttpServletRequest request, HttpServletResponse response, Model model) {
        String userRole = SecurityUtils.getUserRole();
        String currentLogin = SecurityUtils.getCurrentLogin();

        model.addAttribute("version", version);
        if (userRole != null) {
            Date date = new Date();
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

            String formattedDate = dateFormat.format(date);
            CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
            response.addCookie(new Cookie("XSRF-TOKEN", token.getToken()));
            model.addAttribute("serverTime", formattedDate);

            model.addAttribute("user_role", userRole);
            model.addAttribute("ibmcognos_url", getIbmCognosUrl());

            Map<String, String> resultMap = new HashMap<>();
            resultMap.put("role", userRole);
            resultMap.put("username", currentLogin);
            response.addCookie(new Cookie("user", new JSONObject(resultMap).toJSONString()));

            return "home";
        } else {
            return "login";
        }
    }

    private String getIbmCognosUrl() {
        if (TRUE.equals(eraAppConfigEnabled)) {
            ConfigApp oneByKey = configAppRepository.findOneByKey(IBMCOGNOS_URL);
            if (oneByKey != null) {
                return oneByKey.getValue();
            } else {
                return EMPTY_STRING;
            }
        } else {
            return ibmCognosUrl;
        }
    }

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
        model.addObject("version", version);
        model.setViewName("login");

        return model;

    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView handleError405(HttpServletRequest request, Exception e) {
        ModelAndView mav = new ModelAndView("error/error405");
        mav.addObject("exception", e);
        log.error("handleError405", e);
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest request, Exception e) {
        ModelAndView mav = new ModelAndView("error/error500");
        mav.addObject("exception", e);
        log.error("Exception is thrown", e);
        return mav;
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleError2(HttpServletRequest request, Exception e) {
        ModelAndView mav = new ModelAndView("error/error500");
        mav.addObject("exception", e);
        log.error("RuntimeException is thrown", e);
        return mav;
    }

}
