package com.PitaYa.GuGuDa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.PitaYa.GuGuDa.service.sport.SportRecordService;
import com.PitaYa.domain.common.UpdateDataResult;
import com.PitaYa.domain.sport.SportRecordDomain;

@RestController
public class UpdateDataController {
	
	@Autowired
	SportRecordService sportRecordService;

	@RequestMapping(value="/updateSportRecord", method=RequestMethod.POST, headers = {"Content-type=application/json"})
	public UpdateDataResult addSportRecord(@RequestBody SportRecordDomain sportRecordDomain) {
		UpdateDataResult updateDataResult = new UpdateDataResult();
		updateDataResult.setIsSuc(sportRecordService.insertNewSportRecord(sportRecordDomain));
		return updateDataResult;
	}
}
