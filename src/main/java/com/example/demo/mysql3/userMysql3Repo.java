package com.example.demo.mysql3;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.mysql3.User;

public interface userMysql3Repo extends JpaRepository<User, Integer> {

	User findByName(String name);
}
