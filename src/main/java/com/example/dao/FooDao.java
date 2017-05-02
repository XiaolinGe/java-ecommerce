package com.example.dao;

import com.example.entity.Foo;
import com.example.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FooDao extends JpaRepository<Foo,Long>{

    List<Foo> findByStatus(Status status);
}
