package com.PitaYa.GuGuDa.controllers;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PitaYa.GuGuDa.service.circle.CircleTableService;
import com.PitaYa.domain.circle.CircleTableViewCellDomain;

@RestController
public class SportCircleTableController {
	
	private static final Logger logger = LoggerFactory.getLogger(SportCircleTableController.class);
	
	@Autowired
	CircleTableService circleTableService;
 	
	@RequestMapping(value="/circle/cells", method = RequestMethod.GET)
	public List<CircleTableViewCellDomain> trendsCells(@RequestParam("type") Integer type) {
		List<CircleTableViewCellDomain> result = circleTableService.findNewestSportCircle(type);
		return result;
	}
	
}
