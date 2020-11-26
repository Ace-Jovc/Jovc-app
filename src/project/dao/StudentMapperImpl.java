package project.dao;
import org.springframework.stereotype.Repository;
import project.pojo.Student;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.ArrayList;
import java.util.List;
@Repository
public class StudentMapperImpl implements StudentMapper {
    private SqlSessionTemplate sqlSession;
    @Override
    public List<Student> getAllStudents() {
        List<Student> studentList=new ArrayList<Student>();
        studentList= sqlSession.selectList("com.mybatis.dao.StudentMapper.getAllStudents");
        return studentList;
    }

    @Override
    public int deleteStudent(int id) {
        int flag=sqlSession.getMapper(StudentMapper.class).deleteStudent(id);
        System.out.println("mapperImplflag=:"+id+"=kmsxsk="+flag);
        return flag;
    }

    @Override
    public int updateStudent(Student student) {
        int flag=sqlSession.getMapper(StudentMapper.class).updateStudent(student);
        System.out.println("mapperImplflag=:"+student.getId()+"=kmsxsk="+flag);
        return flag;
    }

    @Override
    public int addStudent(Student student) {

        int flag=sqlSession.getMapper(StudentMapper.class).addStudent(student);
        System.out.println("mapperImplflag=:"+student+"=kmsxsk="+flag);
        return flag;
    }

    @Override
    public Student getStudentById(int id) {
        System.out.println("mapperImplId="+id);
        Student student=sqlSession.getMapper(StudentMapper.class).getStudentById(id);
        System.out.println("mapperImpl:getStudentById="+student);
        return student;
    }

    @Override
    public int delectAndInsertStudent(Student student) {
        int flag =sqlSession.getMapper(StudentMapper.class).delectAndInsertStudent(student);
        return flag;
    }

   public SqlSessionTemplate getSqlSession() {
        return sqlSession;
   }

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

}
