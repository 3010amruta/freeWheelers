package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Country;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.CountryService;
import com.trailblazers.freewheelers.service.ServiceResult;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;
import com.trailblazers.freewheelers.service.impl.CountryServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sun.management.resources.agent;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private CountryService countryService;

    public AccountController(AccountService accountService, CountryService countryService) {
        this.accountService = accountService;
        this.countryService=countryService;
    }

    public AccountController() {
        this.accountService = new AccountServiceImpl();
        this.countryService = new CountryServiceImpl();
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public ModelAndView createAccountForm(ModelMap model) {

        List countries = countryService.findAll();
        model.put("countries",countries);
        return new ModelAndView("account/create", "validationMessage", model);
    }


    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public ModelAndView processCreate(HttpServletRequest request) throws IOException {

        Account account = makeAccount(request);

        try {
            ServiceResult<Account> result = accountService.createAccount(account);

            if (result.hasErrors()) {

                return showErrors(result.getErrors(), account);

            }
            return showSuccess(result.getModel());
        } catch (Exception e) {
            return showError();
        }
    }

    private ModelAndView showErrors(Map errors, Account account) {
        ModelMap model = new ModelMap();
        model.put("errors", errors);
        model.put("userDetails", account);

        List countries = countryService.findAll();
        model.put("countries",countries);


        return new ModelAndView("account/create", "validationMessage", model);
    }



    private ModelAndView showError() {
        return new ModelAndView("account/createFailure");
    }

    private ModelAndView showSuccess(Account account) {
        ModelMap model = new ModelMap();
        model.put("name", account.getAccount_name());
        return new ModelAndView("account/createSuccess", "postedValues", model);
    }

    public Account makeAccount(HttpServletRequest httpRequest) {
        String email = httpRequest.getParameter("email");
        String password = httpRequest.getParameter("password");
        String name = httpRequest.getParameter("name");
        String phoneNumber = httpRequest.getParameter("phoneNumber");
        long country_id = Long.parseLong(httpRequest.getParameter("country"));

        Account account = new Account(name, password, true, email, phoneNumber, country_id);

        return account;
    }
}
