package com.tamguo.modules.tiku.service;

import java.util.List;

import com.tamguo.modules.tiku.model.PaperEntity;

public interface IPaperService {

	List<PaperEntity> findHistoryPaper();

	List<PaperEntity> findSimulationPaper();

	List<PaperEntity> findHotPaper(String areaId);

}
