package crewz.admin.crewzadmin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crewz.admin.crewzadmin.model.dto.RequestClubSubsidyDto;
import crewz.admin.crewzadmin.repository.ResponseClubSubsidyInterface;
import crewz.admin.crewzadmin.service.ClubSubsidyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/clubsubsidy")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClubSubsidyController {

	private final ClubSubsidyService clubSubsidyService;

	@GetMapping
	public ResponseEntity<List<ResponseClubSubsidyInterface>> countUsersByClubNo(
		@ModelAttribute RequestClubSubsidyDto requestClubSubsidyDto) {
		return ResponseEntity.ok(clubSubsidyService.findClubSubsidyList(requestClubSubsidyDto));
	}

}



