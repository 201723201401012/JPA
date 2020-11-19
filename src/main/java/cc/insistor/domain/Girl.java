package cc.insistor.domain;

import javax.persistence.*;

/**
 * @author cc
 */
@Entity(name = "t_girl")
public class Girl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gid;
    @Column
    private String name;

    @OneToOne(mappedBy = "girl")
    private Boy boy;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boy getBoy() {
        return boy;
    }

    public void setBoy(Boy boy) {
        this.boy = boy;
    }
}
