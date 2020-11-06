package cc.insistor.domain;

import javax.persistence.*;

/**
 * mysql数据库里没有pet没有这个表
 * 可以使用jpa提供的正向工程
 *
 *
 * @Entity 描述实体了和表之间的关系，name表示表的名字
 */


@Entity(name="t_pet")
public class Pet {
    @Id //表示该属性对应数据库的逐渐列，而且自增
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;


    @Column
    private String pname;

    @Column
    private String color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", pname='" + pname + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
