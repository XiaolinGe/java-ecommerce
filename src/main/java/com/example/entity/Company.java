package com.example.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * 公司
 *
 * @author Luxh
 */
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 公司名称
     */
    @Column(name = "name", length = 32)
    private String name;

    /**
     * 拥有的员工
     */
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //拥有mappedBy注解的实体类为关系被维护端
    //mappedBy="company"中的company是Employee中的company属性
    private Set<Employee> employees = new HashSet<Employee>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

}