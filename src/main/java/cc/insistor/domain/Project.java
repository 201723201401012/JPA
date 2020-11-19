package cc.insistor.domain;

import cc.insistor.domain.Emp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cc
 * 员工表 * *
 *     emp    project
 *     1        1
 *       emp_pro
 *         eid + pid 联合主键：
 *         eid
 *         pid
 *        1  1
 *
 *
 *
 * JoinTable:描述当前实体类和第三张表的关联关系
 * name="emps_pros"：第三张表的名称
 * uniqueConstraints:JoinTable当中的一个属性：  对应的值数组类型。数组当中放的是注解类型
 *          @UniqueConstraint 就是一个注解：
 *          columnNames是UniqueConstraint 注解的属性： 对应的值也是一个数组： 字符串类型了的数组。
 *
 *
 *uniqueConstraints={@UniqueConstraint(columnNames={"e_id","p_id"})
 *就是在第三张表当中添加了一个唯一的约束。 在e_id 和pid 这列上添加的。其实就是添加了一个联合主键。
 *
 *
 * joinColumns:当前对象在中间表的外键：
 *             name： 外键的名称
 *             referencedColumnName： 参照主键表的主键名称：
 *
 *
 * inverseJoinColumns: 配置对方对象在中间表的外键
 *             name： 对方外键的名称
 *             referencedColumnName： 对方  参照主键表的主键名称：
 *
 */
@Entity(name="t_project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="emps_pros",
            uniqueConstraints={@UniqueConstraint(columnNames={"e_id","p_id"})},
            joinColumns={@JoinColumn(name="p_id" ,referencedColumnName = "id")},
            inverseJoinColumns={@JoinColumn(name="e_id" ,referencedColumnName = "id")})
    private List<Emp> emps = new ArrayList<>();

    private String pname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Emp> getEmps() {
        return emps;
    }

    public void setEmps(List<Emp> emps) {
        this.emps = emps;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}
