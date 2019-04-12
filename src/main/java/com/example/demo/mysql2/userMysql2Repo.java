package com.example.demo.mysql2;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.mysql2.User;

public interface userMysql2Repo extends JpaRepository<User, Integer> {

	User findByName(String name);
}
