package crewz.admin.crewzadmin.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crewz.admin.crewzadmin.model.entity.CategoryInfo;
import crewz.admin.crewzadmin.repository.CategoryInfoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryInfoManager {

	private final CategoryInfoRepository categoryInfoRepository;

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
