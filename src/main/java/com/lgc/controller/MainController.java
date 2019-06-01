package com.lgc.controller;

import com.lgc.model.User;
import com.lgc.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: javaConfig-ssm
 * @description: main contro
 * @author: lgc
 * @create: 2019-06-01 17:55
 **/
public class MainController {

    private final static Logger LOG = Logger.getLogger(MainController.class);

	/*
	//使用JdbcTemplate的方式
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@RequestMapping("/index")
	public String index(){
		String sql = "select * from blog_user";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		LOG.info(list);

		String ssql = "select * from blog_user where user_code=:user_code";
		Map<String, Object> params = new HashMap<>();
		params.put("user_code", "admin");
		List<Map<String, Object>> pList = namedParameterJdbcTemplate.queryForList(ssql, params);
		LOG.info(pList);

		return "Welcome to ssm-demo...";
	}*/

    @Autowired
    UserService userService;

    @RequestMapping("/main")
    public String main(){
        User user = userService.findById("admin", "2ff4d63f4adb19d17cc0a99d2532b6a1");
        LOG.info(user);
        System.out.println(user.toString());
        return "Welcome to ssm-demo...";
    }

    @RequestMapping("/update")
    public String updateTest(){
        Integer num = userService.updateUser("test", "admin");
        LOG.info("return result: "+num);
        return "update operation test...";
    }
}
