package com.example.CrudSpringBootDemo.StudentService;

import com.example.CrudSpringBootDemo.Student;
import com.example.CrudSpringBootDemo.StudentRepository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {


    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {

        System.out.println("business logic layer");
        System.out.println("Inside Student Service");



        Student studentResult = studentRepository.save(student);
        studentResult.setDelete(false);

        System.out.println("Exiting Student Service");


        System.out.println("Hi" + studentResult);
        return studentResult;

    }

    public Student getStudent(Long id){

         Optional<Student>getStudent = studentRepository.findByIdAndDeletedFalse(id);




         if(getStudent.isPresent()) {
             return getStudent.get();
         }
         else return null;



    }

    public List<Student> getAllStudents(){

        List<Student>studentList= studentRepository.findByDeletedFalse();


        if(studentList.isEmpty()) return null;
        else return studentList;

}

public Student updatedStudent(Long id,Student student){

    Optional<Student>existingStudent = studentRepository.findByIdAndDeletedFalse(id);

    if(existingStudent.isEmpty()){
        return null;
    }

    Student changeTo = existingStudent.get();

    changeTo.setAge(student.getAge());
    changeTo.setEmail(student.getEmail());
    changeTo.setName(student.getSubject());
    changeTo.setRollNo(student.getRollNo());
    changeTo.setSubject(student.getSubject());


    studentRepository.save(changeTo);

    return changeTo;




}


    public  boolean deletedStudent(Long id){


         boolean isDeleted = studentRepository.existsById(id);


           if(!isDeleted) return false;

           studentRepository.deleteById(id);

           return true;



}


public boolean softDeleted(Long id){


      Optional<Student>makeDelete =  studentRepository.findByIdAndDeletedFalse(id);

      if(makeDelete.isEmpty()) return false;

      Student makeDeleted = makeDelete.get();

      makeDeleted.setDelete(true);
      studentRepository.save(makeDeleted);

      return true;
}





}