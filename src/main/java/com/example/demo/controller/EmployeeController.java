package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("/")
    public String viewHomePage(Model model, @RequestParam(required = false) String name) {
        if(name!=null){
            model.addAttribute("listEmployees", service.getAllEmployeesByName(name));
        }else{
            model.addAttribute("listEmployees", service.getAllEmployees());
        }
        return "index";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

//    @GetMapping("/findEmployeeByName")
//    public String findEmployeeByName(Model model, @RequestParam String name) {
//        model.addAttribute("listEmployees", service.getAllEmployeesByName(name));
//        return "index";
//    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        service.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Employee employee = service.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {
        service.deleteEmployee(id);
        return "redirect:/";
    }
}

