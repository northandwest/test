package rpc.entity;

import java.io.Serializable;

/**
 * 测试实体
 * @author MOTUI
 *
 */
@SuppressWarnings("serial")
public class User implements Serializable {

    private Integer id;
    private String name;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }
    public User(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + "]";
    }
}