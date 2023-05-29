package crewz.admin.crewzadmin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crewz.admin.crewzadmin.model.dto.RequestCategoryInfoDto;
import crewz.admin.crewzadmin.model.entity.CategoryInfo;
import crewz.admin.crewzadmin.service.CategoryInfoManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/category/")
@RequiredArgsConstructor
@Slf4j
public class CategoryInfoController {

	private final CategoryInfoManager categoryInfoManager;

	@PostMapping("/insert")
	public ResponseEntity<String> categoryInfoAdd(@ModelAttribute RequestCategoryInfoDto requestCategoryInfoDto) {
		CategoryInfo categoryInfo = requestCategoryInfoDto.toEntity();
		return categoryInfoManager.addCategoryInfo(categoryInfo);
	}
}
