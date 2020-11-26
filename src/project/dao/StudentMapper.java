package project.dao;

import project.pojo.Student;

import java.util.List;

public interface StudentMapper {
    /*查询student list列表*/
    List<Student> getAllStudents();
    /*删除*/
    int deleteStudent(int id);
    /*修改*/
    int updateStudent(Student student);
    /*添加*/
    int addStudent(Student student);
    /*根据id查询学生*/
    Student getStudentById(int id);
    /*添加删除*/
    int delectAndInsertStudent(Student student);
}
