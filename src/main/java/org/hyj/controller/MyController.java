/*
package org.hyj.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.hyj.bean.Custom;
import org.hyj.bean.Level;
import org.hyj.bean.Source;
import org.hyj.bean.Trade;
import org.hyj.dao.CustomMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


@Controller
public class MyController {

	*/
/**
	 * 获取全部用户
	 * 
	 * @param req
	 * @param response
	 * @return
	 *//*

	@ResponseBody
	@RequestMapping(value = "/allCustom.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String allCustom(HttpServletRequest req,
			HttpServletResponse response) {
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(req.getServletContext());
		CustomMapper customMapper = (CustomMapper) context
				.getBean("customMapper");
		List<Custom> customs = customMapper.selectAllCustom();
		for (int i = 0; i < customs.size(); i++) {
			System.out.println(JSONObject.toJSON(customs.get(i)));
		}
		return JSONArray.toJSONString(customs);
	}

	*/
/**
	 *
	 * @param page
	 *            请求的页面
	 * @param customName
	 *            客户名称
	 * @param sourceName
	 *            来源名称
	 * @param tradeName
	 *            行业名称
	 * @param levelName
	 *            等级
	 * @param req
	 * @return 返回搜索结果的集合
	 *//*

	@ResponseBody
	@RequestMapping(value = "/search.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String search(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) String customName,
			@RequestParam(required = false) String sourceName,
			@RequestParam(required = false) String tradeName,
			@RequestParam(required = false) String levelName,
			HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (page == null) {
			// 说明是第一次搜索
			page = 0;
			session.setAttribute("customName", customName);
			session.setAttribute("sourceName", sourceName);
			session.setAttribute("tradeName", tradeName);
			session.setAttribute("levelName", levelName);
		} else if (page != null && customName == null
				&& sourceName == null && tradeName == null
				&& levelName == null) {
			// 如果参数都空但是page有参数，还原数据之前的
			page = (page - 1) * 10;
			customName = (String) session.getAttribute("customName");
			sourceName = (String) session.getAttribute("sourceName");
			tradeName = (String) session.getAttribute("tradeName");
			levelName = (String) session.getAttribute("levelName");
		}
		System.out.println(page + "\t" + customName + "\t"
				+ sourceName + "\t" + tradeName + "\t" + levelName);
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(req.getServletContext());
		CustomMapper customMapper = (CustomMapper) context
				.getBean("customMapper");
		List<Custom> customs = customMapper.selectCustomByCondition(
				page, customName, sourceName, tradeName, levelName);
		return JSONArray.toJSONString(customs);
	}

	*/
/**
	 * 获取搜索数据的总数，并进行分页
	 *
	 * @param req
	 * @return 返回分页信息
	 *//*

	@ResponseBody
	@RequestMapping(value = "/getPageTotle.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String getPageTotle(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String customName = (String) session
				.getAttribute("customName");
		String sourceName = (String) session
				.getAttribute("sourceName");
		String tradeName = (String) session.getAttribute("tradeName");
		String levelName = (String) session.getAttribute("levelName");
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(req.getServletContext());
		CustomMapper customMapper = (CustomMapper) context
				.getBean("customMapper");
		Integer pageTotle = customMapper.getPageTotle(customName,
				sourceName, tradeName, levelName);
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		for (int i = 1; i <= (int) Math
				.ceil((pageTotle) / 10.0); i++) {
			arrayList.add(i);
		}
		System.out.println(JSONArray.toJSONString(arrayList));
		return JSONArray.toJSONString(arrayList);
	}

	*/
/**
	 * 把POST的参数直接注入到以下对象中
	 *
	 * @param custom
	 *            客户名
	 * @param source
	 *            客户来源
	 * @param trade
	 *            行业
	 * @param level
	 *            等级
	 * @param req
	 * @return 1表示成功，0表示注册失败
	 *//*

	@ResponseBody
	@RequestMapping(value = "/regCustom.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public int regCustom(Custom custom, Source source, Trade trade,
						 Level level, HttpServletRequest req) {
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(req.getServletContext());
		CustomMapper customMapper = (CustomMapper) context
				.getBean("customMapper");
		custom.setSource(source);
		custom.setTrade(trade);
		custom.setLevel(level);
		return customMapper.insert(custom);
	}
}
*/
