package crewz.admin.crewzadmin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import crewz.admin.crewzadmin.model.dto.RequestClubSubsidyDto;
import crewz.admin.crewzadmin.repository.ResponseClubSubsidyInterface;
import crewz.admin.crewzadmin.repository.SubsidyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClubSubsidyService {
	private final SubsidyRepository subsidyRepository;

	public List<ResponseClubSubsidyInterface> findClubSubsidyList(RequestClubSubsidyDto requestClubSubsidyDto) {
		return subsidyRepository.findByMonthSubsidy(requestClubSubsidyDto.getClubNo(), requestClubSubsidyDto.getYear());

	}
}
