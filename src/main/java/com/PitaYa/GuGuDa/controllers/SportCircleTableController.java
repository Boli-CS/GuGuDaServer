package com.PitaYa.GuGuDa.controllers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.PitaYa.GuGuDa.service.circle.CircleTableService;
import com.PitaYa.domain.circle.CircleTableViewCellDomain;
import com.PitaYa.domain.common.UpladDataResult;

/**
 * 运动圈界面分发
 * @author boli
 *
 */
@RestController
public class SportCircleTableController {
	
	private static final Logger logger = LoggerFactory.getLogger(SportCircleTableController.class);
	
	@Autowired
	CircleTableService circleTableService;
 	
	@RequestMapping(value="/circle/cells", method = RequestMethod.GET)
	public List<CircleTableViewCellDomain> trendsCells(@RequestParam("type") Integer type) {
		List<CircleTableViewCellDomain> result = null;
		try {
			result = circleTableService.findNewestSportCircle(type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
//	@RequestMapping(value="/circle/upload",
////			headers=("content-type=multipart/*"),
//			method=RequestMethod.POST)
//	public UpladDataResult uploadNewCircle( 
//			@RequestParam("multipartFile")MultipartFile multipartFile,
//			Model model) {
//		UpladDataResult upladDataResult = new UpladDataResult();
//		upladDataResult.setIsSuc(circleTableService.uploadNewCircleTrend("1", multipartFile));
//		//TODO:错误原因暂时不提供
//		return upladDataResult;
//	}
	
	@RequestMapping(value="/circle/upload")
	public UpladDataResult updateProfile(HttpServletRequest request) {
		UpladDataResult upladDataResult = new UpladDataResult();
		if(request instanceof MultipartHttpServletRequest) { 
			MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest) request;
			for (Map.Entry entry : multipartHttpServletRequest.getMultiFileMap().entrySet()) {
				circleTableService.uploadNewCircleTrend(entry.getKey().toString(),
						multipartHttpServletRequest.getFile(entry.getKey().toString()));	
			}
		}
		return upladDataResult;
	}
}
