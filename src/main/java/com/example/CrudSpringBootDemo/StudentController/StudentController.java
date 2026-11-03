package com.example.CrudSpringBootDemo.StudentController;


import com.example.CrudSpringBootDemo.Student;
import com.example.CrudSpringBootDemo.StudentService.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {


private final StudentService studentService;

public StudentController(StudentService studentService){
    this.studentService = studentService;
}

//create
    @PostMapping("/")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){

        System.out.println("Entering StudentController");
        Student studentResult = studentService.createStudent(student);
         return ResponseEntity.status(201).body(studentResult);

    }


    //read
    @GetMapping("/")
    public ResponseEntity<Student> getStudent(@RequestParam Long id){

    Student getStudent = studentService.getStudent(id);

    if(getStudent == null){
        return ResponseEntity.status(404).body(null);
    }
    return ResponseEntity.status(200).body(getStudent);
    }

//read all
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents(){

        List<Student>getAllStudents = studentService.getAllStudents();

        if(getAllStudents.isEmpty()){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(getAllStudents);
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,@RequestBody Student student){

        Student updatedStudent = studentService.updatedStudent(id,student);

        if(updatedStudent == null){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(updatedStudent);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){


       boolean deletedStudent = studentService.deletedStudent(id);

        if(!deletedStudent){
            return ResponseEntity.status(404).body("Not Found");
        }

        return ResponseEntity.status(200).body("Record Deleted");

    }


    @PatchMapping("/{id}")
    public  ResponseEntity<String>softDelete(@PathVariable Long id){

        boolean softDeleted = studentService.softDeleted(id);

        if(!softDeleted){
            return ResponseEntity.status(404).body("NOT FOUND!!");
        }

        return ResponseEntity.status(200).body("Record SOft Deleted!!");

    }
}
