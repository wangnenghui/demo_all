package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.utils.FileUploadUtils;

@RestController
public class FileController {

	@RequestMapping(value="upload/pictures",method=RequestMethod.POST)
    private String fildUpload(@RequestParam(value="file",required=false) MultipartFile[] file,
            HttpServletRequest request)throws Exception{
		String filepath=FileUploadUtils.uploadPictures(file, request);
		return filepath;
	}
	@RequestMapping(value="upload/getFile",method=RequestMethod.POST)
	private String fildUpload(HttpServletResponse response,String fileName)throws Exception{
		FileUploadUtils.getAppFile(response, fileName);
		return null;
	}

}