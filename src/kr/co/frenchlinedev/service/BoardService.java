package kr.co.frenchlinedev.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.frenchlinedev.beans.ContentBean;
import kr.co.frenchlinedev.beans.UserBean;
import kr.co.frenchlinedev.dao.BoardDao;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {

	@Value("${path.upload}")
	private String path_upload;
	
	@Autowired
	private BoardDao boardDao;
	
	@Resource(name = "loginUserBean")
	UserBean loginUserBean;
	
	/**
	 * 파일 저장 메소드
	 */
	private String saveUploadFile(MultipartFile upload_file) {
		
		// 업로드 되는 파일명 : 파일명이 중복되면 업로드 시 기존 파일을 덮어쓰므로 파일이름 앞에 현재시간을 구해서 붙여준다
		String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();
		
		// 파일 저장
		try {
			upload_file.transferTo(new File(path_upload + "/" + file_name));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//업로드 파일명 반환
		return file_name;
	}
	
	// 게시글 작성
	public void addContentInfo(ContentBean writeContentBean) {
		
//		System.out.println(writeContentBean.getContent_subject());
//		System.out.println(writeContentBean.getContent_text());
//		System.out.println(writeContentBean.getUpload_file().getSize());
		
		MultipartFile upload_file = writeContentBean.getUpload_file();
		
		//파일 첨부는 선택사항
		if (upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			writeContentBean.setContent_file(file_name);
		}
		
		//작성자 정보는 현재 세션에 로그인된 사용자
		writeContentBean.setContent_writer_idx(loginUserBean.getUser_idx());
		
		boardDao.addContentInfo(writeContentBean);
	}
	
	// 게시판 이름
	public String getBoardInfoName(int board_info_idx) {
		return boardDao.getBoardInfoName(board_info_idx);
	}
	
	// 게시글 목록
	public List<ContentBean> getContentList(int board_info_idx) {
		return boardDao.getContentList(board_info_idx);
	}
	
	// 게시글 정보
	public ContentBean getContentInfo(int content_idx) {
		return boardDao.getContentInfo(content_idx);
	}
}
