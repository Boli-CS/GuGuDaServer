package com.PitaYa.GuGuDa.service.circle.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PitaYa.GuGuDa.dao.circle.CircleTableCellDao;
import com.PitaYa.GuGuDa.dao.user.UserDao;
import com.PitaYa.GuGuDa.service.circle.CircleTableService;
import com.PitaYa.domain.circle.CircleTableViewCellDomain;
import com.PitaYa.model.circle.CircleTableViewCell;
import com.PitaYa.model.circle.User;
import com.pitaya.GuGuDa.common.enums.CircleCellTypes;
import com.pitaya.GuGuDa.common.utils.DateUtils;

@Service("circleTableServiceImpl")
public class CircleTableServiceImpl implements CircleTableService{

	@Autowired
	CircleTableCellDao circleTableCellDao;
	
	@Autowired
	UserDao userDao;
	
	@Override
	public List<CircleTableViewCellDomain> findNewestSportCircle(Integer circleCellType) throws Exception {
		CircleCellTypes circleCellTypes = CircleCellTypes.getInstance(circleCellType);
		List<CircleTableViewCellDomain> circleTableViewCellDomains = new ArrayList<CircleTableViewCellDomain>();
		if(circleCellTypes != null) {
			switch (circleCellTypes) {
			case TRENDS:
			case NEARBY:
			case SQUARE:
				List<CircleTableViewCell> circleTableViewCells = circleTableCellDao.findAll();
				for (int i = 0; i < circleTableViewCells.size(); i++) {
					CircleTableViewCellDomain circleTableViewCellDomain = new CircleTableViewCellDomain(circleTableViewCells.get(i));
					User user = userDao.getUser(circleTableViewCells.get(i).getPostUser());
					circleTableViewCellDomain.setUserID(user.getID());
					circleTableViewCellDomain.setHeadPortrait(user.getHeadPortrait());
					circleTableViewCellDomain.setUserName(user.getUserName());
					circleTableViewCellDomain.setSportDays(DateUtils.getDaysFrom(user.getRegisterDate()).toString());
					circleTableViewCellDomains.add(circleTableViewCellDomain);
				}
				break;
			}
		}
		return circleTableViewCellDomains;
	}

}
