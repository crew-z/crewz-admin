package crewz.admin.crewzadmin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crewz.admin.crewzadmin.model.PagingUtil;
import crewz.admin.crewzadmin.model.dto.ResponseCategoryDto;
import crewz.admin.crewzadmin.model.entity.CategoryInfo;
import crewz.admin.crewzadmin.repository.CategoryInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryInfoManager {

	private final CategoryInfoRepository categoryInfoRepository;

	public ResponseEntity<ResponseCategoryDto> findCategoryList(PageRequest pageRequest) {
		Page<CategoryInfo> pageObj = categoryInfoRepository.findAll(pageRequest);
		List<CategoryInfo> categoryInfoList = pageObj.stream().collect(Collectors.toList());

		ResponseEntity<ResponseCategoryDto> entity;
		log.info("totalElements: {}", pageObj.getTotalElements());
		log.info("totalPages: {}", pageObj.getTotalPages());
		log.info("pageNumber: {}", pageObj.getNumber());
		log.info("pageSize: {}", pageObj.getSize());
		PagingUtil pagingUtil = new PagingUtil(pageObj.getTotalElements(), pageObj.getTotalPages(),
			pageObj.getNumber(),
			pageObj.getSize());

		ResponseCategoryDto responseCategoryDto = new ResponseCategoryDto();
		responseCategoryDto.setPagingUtil(pagingUtil);
		responseCategoryDto.setCategoryInfoList(categoryInfoList);

		entity = ResponseEntity.ok(responseCategoryDto);
		return entity;
	}

	@Transactional
	public ResponseEntity<String> addCategoryInfo(CategoryInfo categoryInfo) {
		ResponseEntity<String> entity;
		try {
			categoryInfoRepository.save(categoryInfo);
			entity = new ResponseEntity<>("카테고리등록에 성공하였습니다.", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>("카테고리등록에 실패하였습니다.", HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
}
