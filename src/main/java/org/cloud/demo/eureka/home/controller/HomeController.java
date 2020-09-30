package org.cloud.demo.eureka.home.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test.Druid;

@RestController
public class HomeController {
	@Value("${server.port}")
	private String port;

	@GetMapping("/gethome1")
	public String gethome1(@RequestParam String name) {
		return "hello home!!!  端口为：" + port + "名字为：" + name;
	}
	
	@GetMapping("/getdbuser")
	public Object getdbuser(@RequestParam String name) throws SQLException {
		return Druid.getDruidTest();
		//return "dbuser";
	}
}
