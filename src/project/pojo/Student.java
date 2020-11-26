package project.pojo;

import java.io.Serializable;

public class Student implements Serializable {

    private int id;
    private String name;
    private boolean sex;
    private double score;

    /**
     * 构造方法
     */
    public Student(){
    }
    public Student(int id){
        this.id=id;
    }
    /**
     *
     * @param id 学号
     * @param name 姓名
     * @param sex 性别
     * @param score 成绩
     */
    public Student(int id, String name, boolean sex, double score){
        this.id=id;
        this.name=name;
        this.sex=sex;
        this.score=score;
    }

    /**
     *
     * @param id
     */
    public void setId(int id){
        this.id=id;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
