package com.webank.dca.store.handler.file;

import com.webank.dca.store.config.SystemConfig;
import com.webank.dca.store.model.FileInfo;
import com.webank.dca.store.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;

/**
 * @author aaronchu
 * @Description
 * @date 2022/04/28
 */
@Component
@ConditionalOnExpression("'${system.fileStorage}'.equals('local')")
public class LocalFileHandler implements IFileHandler {

    @Autowired
    private SystemConfig systemConfig;

    @Override
    public FileInfo saveFile(File tmp){

        String hash = null;
        try {
            hash = HashUtils.sha1(tmp);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileId(tmp.getName());
        fileInfo.setFileHash(hash);
        fileInfo.setFileLength(tmp.length());

        return fileInfo;
    }

    @Override
    public FileInfo saveFile(InputStream file, String key) {
        return null;
    }

    @Override
    public FileInfo downloadFile(String fileId, String fileHash) throws Exception {
        File dir = Paths.get(systemConfig.getFileBufDir(), "upload").toFile();
        dir.mkdirs();
        File tmp = new File(dir, fileId);

        FileInfo fileInfo = new FileInfo();

        fileInfo.setLocal(tmp);
        fileInfo.setFileId(fileId);
        fileInfo.setFileHash(fileHash);
        return fileInfo;
    }


}