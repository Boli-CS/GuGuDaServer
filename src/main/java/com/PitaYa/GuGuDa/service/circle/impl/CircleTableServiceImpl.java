package com.PitaYa.GuGuDa.service.circle.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PitaYa.GuGuDa.dao.circle.CircleTableCellDao;
import com.PitaYa.GuGuDa.dao.config.SysConfigDao;
import com.PitaYa.GuGuDa.dao.user.UserDao;
import com.PitaYa.GuGuDa.service.circle.CircleTableService;
import com.PitaYa.domain.circle.CircleTableViewCellDomain;
import com.PitaYa.model.circle.CircleTableViewCell;
import com.PitaYa.model.user.User;
import com.pitaya.GuGuDa.common.constants.GuGuDaConstants;
import com.pitaya.GuGuDa.common.enums.CircleCellTypes;
import com.pitaya.GuGuDa.common.utils.DateUtils;
import com.pitaya.GuGuDa.common.utils.FileUtils;

@Service("circleTableServiceImpl")
public class CircleTableServiceImpl implements CircleTableService{

	@Autowired
	CircleTableCellDao circleTableCellDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	SysConfigDao sysConfigDao;
	
	private static final Logger log = LoggerFactory.getLogger(CircleTableServiceImpl.class);
	
	@Override
	public List<CircleTableViewCellDomain> findNewestSportCircle(Integer circleCellType) {
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

	@Override
	public Boolean uploadNewCircleTrend(String fileName, MultipartFile multipartFile) {
		BufferedOutputStream bufferedOutputStream = null;
		String filePath = null;
		if(multipartFile != null && !multipartFile.isEmpty()) {
			try {
				byte[] fileBytes = multipartFile.getBytes();
				filePath = FileUtils.finishPath(sysConfigDao.getValue(GuGuDaConstants.FILE_SERVER_MOCK_PATH));
				bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(filePath + fileName)));
				bufferedOutputStream.write(fileBytes);
				return true;
			} catch (Exception e) {
				//出现异常，删除已经上传的文件
				if(filePath != null) {
					File addedFile = new File(filePath + fileName);
					if(addedFile.exists()) {
						addedFile.delete();
					}
				}
			}
			finally {
				if(bufferedOutputStream != null) {
					try {
						bufferedOutputStream.close();
					} catch (IOException e) {
						log.error("上传文件时文件流关闭失败：");
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}

}
