package service;

import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicateResolver {


	public Path resolver(Path targetPath) {
		if(!Files.exists(targetPath)) {
			return targetPath;
		}

		Path pathFolder = targetPath.getParent();
		String originalFileName = targetPath.getFileName().toString();

		String nameWithoutExt =  getFileNameWithoutExtension(originalFileName);
		String nameExtension = getFileExtension(originalFileName);

		//falta un do while 
		return pathFolder;
	}


	private String getFileNameWithoutExtension(String FileName) {
		int dotIndex = FileName.lastIndexOf('.');
		if(dotIndex == -1) {
			return FileName;
		}
		return FileName.substring(0, dotIndex);
	}

	private String getFileExtension(String FileName) {
		int dotIndex = FileName.lastIndexOf('.');
		if(dotIndex == -1) {
			return "";
		}
		return FileName.substring(dotIndex);
	}
}
