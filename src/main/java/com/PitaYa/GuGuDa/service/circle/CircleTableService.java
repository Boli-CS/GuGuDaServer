package com.PitaYa.GuGuDa.service.circle;

import java.util.List;

import com.PitaYa.domain.circle.CircleTableViewCellDomain;

public interface CircleTableService {

	/**
	 * 根据运动圈加载数据类型返回对应的数据
	 * @param sportCircleCellType
	 */
	public List<CircleTableViewCellDomain> findNewestSportCircle(Integer sportCircleCellType);
}
