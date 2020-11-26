package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.pojo.Student;
import project.service.StudentService;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
    //列表
    @RequestMapping("/listServlet")
    public String list(Model model){
        List<Student> students=studentService.list();
        model.addAttribute("studentList",students);
        return "listStudent";
    }
    //查询
    @RequestMapping("search")
    public String search(@RequestParam(value = "id",required = false)Integer id, Model model){
        Student s=null;
        if(id!=null){
           s=studentService.searchStudent(id);
        }
        model.addAttribute("student",s);
        model.addAttribute("id",id);
        return "findStudent";
    }
    //添加
    @RequestMapping("add")
    public String add(Student student,Model model){
        int id=student.getId();
        Student student1=studentService.searchStudent(id);
        if(student1==null){
            int flag=studentService.addStudent(student);
            if(flag==1){
                System.out.println("添加成功");
            }else {
                System.out.println("添加失败");
            }
        }
        return "redirect:/listServlet";
    }
    //删除
    @RequestMapping("delete")
    public String delete(@RequestParam(value = "id",required = false)Integer id, Model model) {
        int flag=studentService.deleteStudent(id);
        if (flag==1){
            System.out.println("\t操作信息：删除学生信息成功");
        }else if (flag==-1){
            System.out.println("\t操作信息：删除学生信息失败");
        }else if (flag==0){
            System.out.println("\t操作信息：该学生信息不存在");
        }
        return "redirect:/listServlet";
    }
        //修改
        @RequestMapping("update")
        public String update(Student student,Model model){
        int flag=studentService.updateStudent(student);
            if (flag==1){//更新成功
                System.out.println("\t操作信息：更新成功");
            }else {//更新失败
                System.out.println("\t操作信息：更新失败");
            }
            return "redirect:/listServlet";
        }
        //通过id查询内容
        @RequestMapping("/updateSelectById")
        public String  updateSelectById(@RequestParam(value = "id",required = false)Integer id,Model model){
            Student stu=studentService.searchStudent(id);
            System.out.print(stu);
            model.addAttribute("student1",stu);
            return "editStudent";

        }
}
