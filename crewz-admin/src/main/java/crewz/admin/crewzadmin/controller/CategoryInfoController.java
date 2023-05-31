package crewz.admin.crewzadmin.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crewz.admin.crewzadmin.model.dto.RequestCategoryInfoDto;
import crewz.admin.crewzadmin.model.dto.RequestPageDto;
import crewz.admin.crewzadmin.model.dto.ResponseCategoryDto;
import crewz.admin.crewzadmin.model.entity.CategoryInfo;
import crewz.admin.crewzadmin.service.CategoryInfoManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/category/")
@RequiredArgsConstructor
@Slf4j
public class CategoryInfoController {

	private final CategoryInfoManager categoryInfoManager;

	@GetMapping("all")
	public ResponseEntity<ResponseCategoryDto> categoryList(@ModelAttribute RequestPageDto requestPageDto) {
		PageRequest pageRequest = PageRequest.of(requestPageDto.getPage(), requestPageDto.getPageSize(),
			Sort.by("categoryNo"));
		return categoryInfoManager.findCategoryList(pageRequest);
	}

	@PostMapping("/insert")
	public ResponseEntity<String> categoryInfoAdd(@RequestBody RequestCategoryInfoDto requestCategoryInfoDto) {
		CategoryInfo categoryInfo = requestCategoryInfoDto.toEntity();
		return categoryInfoManager.addCategoryInfo(categoryInfo);
	}
}
