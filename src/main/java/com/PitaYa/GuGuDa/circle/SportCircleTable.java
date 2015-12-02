package com.PitaYa.GuGuDa.circle;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.PitaYa.domain.circle.CircleTableViewCellDomain;

@Controller
public class SportCircleTable {

	private static final Logger logger = LoggerFactory.getLogger(SportCircleTable.class);
	
	@RequestMapping(value="/circle/trends/cells", method = RequestMethod.GET)
	public @ResponseBody List<CircleTableViewCellDomain> home() {
		logger.info("enter home");
		List<CircleTableViewCellDomain> circleTableViewCellDomains = new ArrayList<CircleTableViewCellDomain>();
		CircleTableViewCellDomain circleTableViewCellDomain = new CircleTableViewCellDomain();
		circleTableViewCellDomain.setUserName("李博");
		circleTableViewCellDomain.setSportDays("2");
		circleTableViewCellDomain.setPostDate("2015-01-01");
		circleTableViewCellDomain.setPostLocation("深圳");
		circleTableViewCellDomain.setPostContent("十公里达成");
		
		circleTableViewCellDomains.add(circleTableViewCellDomain);
		circleTableViewCellDomains.add(circleTableViewCellDomain);
		return circleTableViewCellDomains;
	}
}
