package com.webank.dca.store.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class FileHashExistException extends RuntimeException{
    private String msg;
}
