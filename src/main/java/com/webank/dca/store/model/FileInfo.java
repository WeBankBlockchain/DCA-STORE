package com.webank.dca.store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

/**
 * @author yuzhichu
 */
@Data
@NoArgsConstructor
public class FileInfo {


    private String fileId;

    private String fpsId;

    private int fileType;

    private String fileHash;

    private String fileUrl;

    private long fileLength;

    @JsonIgnore
    private File local;

}
