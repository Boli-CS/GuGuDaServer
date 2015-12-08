package com.PitaYa.GuGuDa.controllers;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PitaYa.GuGuDa.service.download.DownloadPicturesService;

@RestController
public class DownloadFileController {
	private static final Logger logger = LoggerFactory.getLogger(DownloadFileController.class);
	
	@Autowired
	DownloadPicturesService downloadPicturesService;
	
	@RequestMapping(value="/download/userHeadPortrait", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse httpServletResponse, @RequestParam("filePath")String filePath) {
		System.out.println(filePath);
		boolean result = downloadPicturesService.getDownloadPictureResponse("userHeadPortrait/"+ filePath, httpServletResponse);
	}
}
