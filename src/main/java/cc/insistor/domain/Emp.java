package cc.insistor.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cc
 * ManyToMany(mappedBy = "emps"):使得Emp对象放弃外键的维护权利
 */
@Entity(name = "t_emp")
public class Emp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @ManyToMany(mappedBy = "emps")
    private List<Project> projects = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
