package com.example.demo.mysql5;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.mysql5.User;

public interface userMysql5Repo extends JpaRepository<User, Integer> {

	User findByName(String name);
}
