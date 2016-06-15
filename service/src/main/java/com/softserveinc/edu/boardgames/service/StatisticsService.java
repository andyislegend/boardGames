package com.softserveinc.edu.boardgames.service;

import java.util.List;

import com.softserveinc.edu.boardgames.persistence.entity.dto.ActionsDTO;

public interface StatisticsService {

	List<ActionsDTO> getActionsStatistics();
}
