package project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.StudentMapper;
import project.pojo.Student;
import project.service.StudentService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    //自动绑定依赖注入属性对象
    @Autowired
    private StudentMapper studentMapper;

    //private SqlSession sqlSession;
    @Override
    public List<Student> list() {
       // System.out.println("serviceImpl:");
        List<Student> studentList=new ArrayList<Student>();
       // System.out.println("serviceImpl:1");
        studentList=studentMapper.getAllStudents();
       // System.out.println("serviceImpl:2");
       // System.out.println("serviceImpl:"+studentList);

        return studentList;
    }

    @Override
    public int addStudent(Student student) {
      int state=-1;
      if(studentMapper.getStudentById(student.getId())==null){
          int flag=studentMapper.addStudent(student);
      if (flag==1){
          state=1;
      }else {
          state=0;
      }
      }
      return state;

    }

    @Override
    public int updateStudent(Student student) {
        System.out.println("studentServiceImpl:"+student.getId());
        if (studentMapper.getStudentById(student.getId())==null){
            return -1;//学号不存在
        }
        if (studentMapper.updateStudent(student)==1){
            return 1;//更新成功
        }
        return 0;
    }

    @Override
    public int deleteStudent(int id) {
        int state=-1;
        System.out.println("StudentServiceImpl修改:"+studentMapper.getStudentById(id));
        if(studentMapper.getStudentById(id)!=null){
            int flag=studentMapper.deleteStudent(id);
            if (flag==1){
                state=1;
            }else {
                state=0;
            }
        }
        return state;
    }

    @Override
    public Student searchStudent(int id) {
        System.out.println("6666666implservoce="+id);
      Student student=studentMapper.getStudentById(id);
      return student;
    }

    @Override
   @Transactional(propagation = Propagation.REQUIRED)
    public int delectAndInsert(Student student) {
        studentMapper.deleteStudent(student.getId()-1);
            //int i=1/0;
            studentMapper.addStudent(student);
        return 1;
    }

    public StudentMapper getStudentMapper() {
        return studentMapper;
    }

    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }
}
