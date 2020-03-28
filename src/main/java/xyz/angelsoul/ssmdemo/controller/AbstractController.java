package xyz.angelsoul.ssmdemo.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.angelsoul.ssmdemo.utils.JsonResult;

public abstract class AbstractController {
	@SuppressWarnings("rawtypes")
	@ExceptionHandler
	@ResponseBody
	public JsonResult exp(Exception e) {
		e.printStackTrace();
		return new JsonResult(e);
	}
}
