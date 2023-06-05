package crewz.admin.crewzadmin.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crewz.admin.crewzadmin.model.dto.RequestClubApplyUpdateDto;
import crewz.admin.crewzadmin.model.dto.RequestPageDto;
import crewz.admin.crewzadmin.model.dto.ResponseClubApplyDto;
import crewz.admin.crewzadmin.model.dto.ResponseClubDto;
import crewz.admin.crewzadmin.service.ClubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/club")
@RequiredArgsConstructor
@Slf4j
public class ClubController {

	private final ClubService clubService;

	@GetMapping("/all")
	public ResponseEntity<ResponseClubDto> clubList(@ModelAttribute RequestPageDto requestPageDto) {
		PageRequest pageRequest = PageRequest.of(requestPageDto.getPage(), requestPageDto.getPageSize(),
			Sort.by("clubNo"));

		return clubService.findClubList(pageRequest);
	}

	@GetMapping("/clubapply")
	public ResponseEntity<ResponseClubApplyDto> clubApplyList(@ModelAttribute RequestPageDto requestPageDto) {
		PageRequest pageRequest = PageRequest.of(requestPageDto.getPage(), requestPageDto.getPageSize(),
			Sort.by("clubApplyNo"));
		String keyword;
		
		if (requestPageDto.getKeyword() != null) {
			keyword = requestPageDto.getKeyword();
		} else {
			keyword = "";
		}

		return clubService.findClubApplyList(keyword, pageRequest);
	}

	@PatchMapping("/clubapply")
	public ResponseEntity<String> clubApplyUpdate(
		@RequestBody RequestClubApplyUpdateDto requestClubApplyUpdateDto) {
		log.info("request: {}", requestClubApplyUpdateDto);
		try {
			clubService.updateClubApply(requestClubApplyUpdateDto);
			return new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
