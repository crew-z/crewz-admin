package crewz.admin.crewzadmin.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
@RequiredArgsConstructor
public class LoginController {

	// @GetMapping("/admin")
	// public @ResponseBody String admin() {
	// 	return "관리자 페이지입니다.";
	// }

}
