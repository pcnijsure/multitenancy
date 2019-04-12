package com.example.demo.mysql1;

import org.springframework.data.jpa.repository.JpaRepository;

public interface userMysql1Repo extends JpaRepository<User, Integer> {

	User findByName(String name);
}
