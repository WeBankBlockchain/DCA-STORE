package com.webank.dca.store.handler.file;

import com.webank.dca.store.model.FileInfo;

import java.io.File;
import java.io.InputStream;

/**
 * @author aaronchu
 * @Description
 * @date 2022/04/28
 */
public interface IFileHandler {

	FileInfo saveFile(File file);

	FileInfo saveFile(InputStream file, String key);

	FileInfo downloadFile(String fileId, String fileHash) throws Exception;


}