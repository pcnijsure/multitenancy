package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.mysql3.Details;
import com.example.demo.mysql3.User;

@Component
public class mysqlService {

	@Autowired
	private com.example.demo.mysql1.userMysql1Repo userMysql1Repo;

	@Autowired
	private com.example.demo.mysql2.userMysql2Repo userMysql2Repo;

	@Autowired
	private com.example.demo.mysql3.userMysql3Repo userMysql3Repo;

	@Autowired
	private com.example.demo.mysql4.userMysql4Repo userMysql4Repo;

	@Autowired
	private com.example.demo.mysql5.userMysql5Repo userMysql5Repo;

	@Autowired
	private com.example.demo.mysql3.detailsMysql3Repo detailsMysql3Repo;

	public Boolean checkUserName(String name)
	{
		if(name.endsWith("1"))
		{
			com.example.demo.mysql1.User user = userMysql1Repo.findByName(name);
			if(user!=null)
				return true;
			else
				return false;
		}
		else if(name.endsWith("2"))
		{
			com.example.demo.mysql2.User user = userMysql2Repo.findByName(name);
			if(user!=null)
				return true;
			else
				return false;
		}
		else if(name.endsWith("3"))
		{
			com.example.demo.mysql3.User user = userMysql3Repo.findByName(name);
			if(user!=null)
				return true;
			else
				return false;
		}
		else if(name.endsWith("4"))
		{
			com.example.demo.mysql4.User user = userMysql4Repo.findByName(name);
			if(user!=null)
				return true;
			else
				return false;
		}
		else if(name.endsWith("5"))
		{
			com.example.demo.mysql5.User user = userMysql5Repo.findByName(name);
			if(user!=null)
				return true;
			else
				return false;
		}

		return false;
	}

	public Boolean saveDetails(String name,String detailsName)
	{
		if(name.endsWith("3"))
		{
			User user = userMysql3Repo.findByName(name);
			if(user!=null)
			{
				Details details = detailsMysql3Repo.save(new Details(user.getId(),detailsName));
				if(details!=null)
					return true;
			}
		}
		return false;
	}
	//	@Autowired
	//	private userMysqlRepo userMysqlRepo;

	//	public List<User> getAllUsers()
	//	{
	//		return userMysqlRepo.findAll();
	//	}


}