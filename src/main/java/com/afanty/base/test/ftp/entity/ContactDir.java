package com.afanty.base.test.ftp.entity;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author yejx
 * @date 2020/4/20
 */
@Data
public class ContactDir implements Serializable {

    /**
     * 录音文件夹
     */
    private String wavDir;

    /**
     * 图片文件夹
     */
    private String imgDir;

    public ContactDir() {
    }
}
