package com.PitaYa.GuGuDa.service.circle;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.PitaYa.domain.circle.CircleTableViewCellDomain;

public interface CircleTableService {

	/**
	 * 根据运动圈加载数据类型返回对应的数据
	 * @param sportCircleCellType
	 * @throws Exception 
	 */
	public List<CircleTableViewCellDomain> findNewestSportCircle(Integer sportCircleCellType);
	
	public Boolean uploadNewCircleTrend(String fileName, MultipartFile multipartFile);
}
