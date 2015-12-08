package com.PitaYa.GuGuDa.service.download.impl;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.PitaYa.GuGuDa.service.download.DownloadPicturesService;

@Service("downloadPicturesServiceImpl")
public class DownloadPicturesServiceImpl implements DownloadPicturesService {
	
	Logger logger = LoggerFactory.getLogger(DownloadPicturesServiceImpl.class);

	@Override
	public boolean getDownloadPictureResponse(String filePathName, HttpServletResponse httpServletResponse) {
		ClassPathResource fileResource = new ClassPathResource(filePathName);
		try {
			System.out.println(fileResource.getFile().getAbsolutePath());
			if (!fileResource.getFile().exists()) {
				String errorMessage = "Sorry. The file you are looking for does not exist";
				logger.error(errorMessage);
				httpServletResponse.getOutputStream().write(errorMessage.getBytes(Charset.forName("UTF-8")));
				httpServletResponse.getOutputStream().close();
				return true;
			}
			String mimeType = URLConnection.guessContentTypeFromName(fileResource.getFilename());
			if(null == mimeType) {
				mimeType = "application/octet-stream";
			}
			httpServletResponse.setContentType(mimeType);
			
			httpServletResponse.setHeader("Content-Disposition", String.format("inline; filename=\"" + fileResource.getFilename() +"\""));
			 
			httpServletResponse.setContentLength((int)fileResource.getFile().length());
	 
	        InputStream inputStream = new BufferedInputStream(new FileInputStream(fileResource.getFile()));
	 
	        //Copy bytes from source to destination(outputstream in this example), closes both streams.
	        FileCopyUtils.copy(inputStream, httpServletResponse.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("生成图片下载的HttpServletResponse失败：");
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
