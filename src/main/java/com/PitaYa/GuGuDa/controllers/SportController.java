package com.PitaYa.GuGuDa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.PitaYa.GuGuDa.service.sport.SportRecordService;
import com.PitaYa.domain.common.UpladDataResult;
import com.PitaYa.domain.sport.SportRecordDomain;

/**
 * 运动界面分发
 * @author boli
 *
 */
@RestController
public class SportController {
	
	@Autowired
	SportRecordService sportRecordService;

	@RequestMapping(value="/updateSportRecord", method=RequestMethod.POST, headers = {"Content-type=application/json"})
	public UpladDataResult addSportRecord(@RequestBody SportRecordDomain sportRecordDomain) {
		UpladDataResult updateDataResult = new UpladDataResult();
		updateDataResult.setIsSuc(sportRecordService.insertNewSportRecord(sportRecordDomain));
		return updateDataResult;
	}
}
