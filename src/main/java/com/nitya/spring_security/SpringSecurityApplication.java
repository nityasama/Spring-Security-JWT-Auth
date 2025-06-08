package com.nitya.spring_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Base64;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) throws SQLException {
//		Connection conn = DriverManager.getConnection(
//				"jdbc:postgresql://ep-tight-sun-a4th07wi-pooler.us-east-1.aws.neon.tech:5432/neondb?sslmode=require", "neondb_owner", "npg_uzyGNAam43sj");
//		System.out.println("Connected!");
//		conn.close();
//	}
		SpringApplication.run(SpringSecurityApplication.class, args);
	}
}
