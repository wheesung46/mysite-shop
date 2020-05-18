package com.bit.myshop.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bit.myshop.exception.ProductUploadException;
import com.bit.myshop.repository.ProductDao;
import com.bit.myshop.vo.ProductVo;

@Service
public class ProductService {
	private static final String SAVE_PATH
		= "D:\\web-workspace\\spring-shop\\webapp\\upload"; 
	public static final String BASE_URL = "/upload";
	
	@Autowired
	ProductDao productDao;
	
	public List<ProductVo> getProductList(){
		return productDao.getList();
	}
	
	public void store(
			ProductVo productVo,
			MultipartFile multipartfile
			) throws ProductUploadException {
		try {
			if(multipartfile.isEmpty()==true) {
				throw new ProductUploadException("MultipartFile is Empty...");
			}
			
			String orgFileName
				= multipartfile.getOriginalFilename();
			String fileExtName
				= orgFileName.substring(orgFileName.lastIndexOf('.') + 1,
										orgFileName.length());
			String saveFileName
				= generateSaveFileName(fileExtName);
			Long fileSize
				= multipartfile.getSize();
		
			writeFile(multipartfile, saveFileName);
		
			productVo.setOrgFileName(orgFileName);
			productVo.setSaveFileName(saveFileName);
			productVo.setFileExtName(fileExtName);
			productVo.setFileSize(fileSize);
			productDao.insert(productVo);
		}catch (IOException ex){
			throw new ProductUploadException("Save File Uploaded....");
		}
	}
	
	private String generateSaveFileName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH + 1);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);
		
		return fileName;
	}
	
	private void writeFile(
			MultipartFile multipartfile,
			String saveFileName
			) throws IOException{
		byte[] fileData = multipartfile.getBytes();
		FileOutputStream fos
			= new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		fos.write(fileData);
		fos.close();
	}
	
	public boolean deleteProduct(Long userNo, Long no) {
		ProductVo productVo = new ProductVo();
		productVo.setUserNo(userNo);
		productVo.setNo(no);
		
		return productDao.delete(productVo) == 1;
	}
	
	public ProductVo updateForm(Long no) {
		return productDao.get(no);
	}
	
	public void update(ProductVo vo) {
		productDao.update(vo);
	}
}
