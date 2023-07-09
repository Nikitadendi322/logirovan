package com.example.log.Controller;

import com.example.log.Service.EmployeeService;
import lombok.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("dev")
@RequestMapping("/appInfo")
public class InfoController {

    private final EmployeeService employeeService;

    @Value("${app.nev}")
    public String appEnv;

    @Value("${dev}")
    public String dev;

    public InfoController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public String getAppEnv() {
        return appEnv;
    }

    public void setAppEnv(String appEnv) {
        this.appEnv = appEnv;
    }

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }


}
