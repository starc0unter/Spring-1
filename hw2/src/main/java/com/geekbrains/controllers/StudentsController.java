package com.geekbrains.controllers;

import com.geekbrains.entities.Student;
import com.geekbrains.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/students")
public class StudentsController {
    private StudentsService studentsService;

    @Autowired
    public void setStudentsService(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    // http://localhost:8189/app/students/showForm
    @RequestMapping("/showForm")
    public String showSimpleForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student-form";
    }

    // http://localhost:8189/app/students/processForm
    @RequestMapping("/processForm")
    public String processForm(@ModelAttribute("student") Student student) {
        System.out.println(student.getFirstName() + " " + student.getLastName());        
        return "student-form-result";
    }
    
    // http://localhost:8189/app/students/showStudentById?id=5
    @RequestMapping(path="/showStudentById", method=RequestMethod.GET)
    public String showStudentById(Model model, @RequestParam Long id) {
        Student student = studentsService.getStudentById(id);
        model.addAttribute("student", student);
        return "student-form-result";
    }

    // http://localhost:8189/app/students/getStudentById?id=20
    @RequestMapping(path="/getStudentById", method=RequestMethod.GET)
    @ResponseBody
    public Student getStudentById(@RequestParam Long id) {
        Student student = studentsService.getStudentById(id);
        return student;
    }

    // http://localhost:8189/app/students/getStudentById/10
    @RequestMapping(path="/getStudentById/{sid}", method=RequestMethod.GET)
    @ResponseBody
    public Student getStudentByIdFromPath(@PathVariable("sid") Long id) {
        Student student = studentsService.getStudentById(id);
        return student;
    }

}
