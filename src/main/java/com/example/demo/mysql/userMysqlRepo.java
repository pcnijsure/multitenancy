package com.example.demo.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

public interface userMysqlRepo extends JpaRepository<User, Integer> {

}
