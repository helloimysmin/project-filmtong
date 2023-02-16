package info.filmtong.movie.mapper;

import java.util.List;

import info.filmtong.movie.vo.FileInfoVO;

public interface FileInfoMapper {

	List<FileInfoVO> selectFileInfos();
	int insertFileInfo(FileInfoVO fileInfo);
}