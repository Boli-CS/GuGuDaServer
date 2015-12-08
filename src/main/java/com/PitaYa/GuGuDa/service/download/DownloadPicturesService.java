package com.PitaYa.GuGuDa.service.download;

import javax.servlet.http.HttpServletResponse;

public interface DownloadPicturesService {

	/**
	 * 将需要下载的文件的相关信息填充到response
	 * @param filePathName	文件路径+文件名
	 * @param httpServletResponse	返回给前台的Response
	 * @return
	 */
	public boolean getDownloadPictureResponse(String filePathName, HttpServletResponse httpServletResponse);
}
