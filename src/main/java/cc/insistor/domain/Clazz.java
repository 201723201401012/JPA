package cc.insistor.domain;


import javax.persistence.*;
import java.util.List;

/**
 * @author cc
 * 创建一个班级
 * 一对多
 * OneToMany(mappedBy = "clz",fetch = FetchType.EAGER)
 * //mapperBy 创建一对多的映射关系： 值是对方外键对应的属性名称。
 */

@Entity(name = "t_clazz")
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;

    @OneToMany(mappedBy = "clz",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<Student> list;

    @Column
    private String cname;

    public Clazz() {
    }

    public Clazz(int cid, List<Student> list, String cname) {
        this.cid = cid;
        this.list = list;
        this.cname = cname;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
