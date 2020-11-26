package project.service;

import project.pojo.Student;

import java.util.List;

public interface StudentService {
    /*查询student list*/
    List<Student> list();
    /*添加*/
    int addStudent(Student student);
    /*修改*/
    int updateStudent(Student student);
    /*删除*/
    int deleteStudent(int id);
    /*根据Id查询*/
    Student searchStudent(int id);
    /*删除添加*/
    public int delectAndInsert(Student student);
}
