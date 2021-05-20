package com.afanty.base.test.ftp.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 接触记录
 */
@Data
public class Contact implements Serializable {

    /**
     * 接触ID、主键ID
     */
    private Object contactId;

    /**
     * 录音在服务器的路径
     */
    private Object recordFile;

    /**
     * 盘符编号
     */
    private Object locationId;

    public Contact() {
    }

    public Contact(Object contactId, Object recordFile, Object locationId) {
        this.contactId = contactId;
        this.recordFile = recordFile;
        this.locationId = locationId;
    }
}
