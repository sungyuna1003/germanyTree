package com.germanyTree.persistence;

import java.util.List;

import com.germanyTree.domain.LectureVO;

public interface LectureDAO {
	public void insert(LectureVO lecture) throws Exception;
	public LectureVO selectMaxNum(int ctNum) throws Exception;
	public LectureVO selectMinNum(int ctNum) throws Exception;
	public List<LectureVO> selectTitle(int ctNum) throws Exception;
	public LectureVO select(int lcNum) throws Exception;
	public void delete(int lcNum) throws Exception;
	public void update(LectureVO lecture) throws Exception;
	public void updateReadCount(int lcNum) throws Exception;
}
