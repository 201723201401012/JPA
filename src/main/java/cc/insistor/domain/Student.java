package cc.insistor.domain;

import javax.persistence.*;

/**
 * @author cc
 * 学生
 * @ManyToOne：多对一
 * @ManyToOne //多个学生对象属于同一个班级：
 * @JoinColumn(name="cid") //指定了外键列： 外键列没有指定name属性，默认名称clz。
 *
 */
@Entity(name = "t_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "cid")
    private Clazz clz;

    public Student() {
    }

    public Student(int sid, String name, Clazz clz) {
        this.sid = sid;
        this.name = name;
        this.clz = clz;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clazz getClz() {
        return clz;
    }

    public void setClz(Clazz clz) {
        this.clz = clz;
    }
}
