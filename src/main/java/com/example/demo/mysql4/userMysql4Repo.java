package com.example.demo.mysql4;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.mysql4.User;

public interface userMysql4Repo extends JpaRepository<User, Integer> {

	User findByName(String name);
}
