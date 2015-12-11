package com.PitaYa.GuGuDa.service.sport.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PitaYa.GuGuDa.dao.sport.SportRecordDao;
import com.PitaYa.GuGuDa.service.sport.SportRecordService;
import com.PitaYa.domain.sport.SportRecordDomain;
import com.PitaYa.model.sport.SportRecord;

@Service("sportRecordServiceImpl")
public class SportRecordServiceImpl implements SportRecordService{
	
	@Autowired
	SportRecordDao sportRecordDao;

	@Override
	public boolean insertNewSportRecord(SportRecordDomain sportRecordDomain) {
		SportRecord sportRecord = new SportRecord(sportRecordDomain);
		if(sportRecordDao.insert(sportRecord) > 0) {
			return true;
		}
		return false;
	}

}
