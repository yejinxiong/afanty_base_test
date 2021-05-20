package com.afanty.base.test.ftp.service;

import com.afanty.base.test.ftp.entity.Contact;
import com.afanty.base.test.ftp.entity.ContactDir;
import com.afanty.base.test.ftp.utils.FTPManager;
import com.afanty.base.test.ftp.utils.FTPServerInterface;
import com.afanty.base.test.ftp.utils.FtpConfigurBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

/**
 * <p>
 * ftp业务实现层
 * </p>
 *
 * @Author yejx
 * @Date 2020/5/16
 */
@Service("ftpService")
public class FtpServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(FtpServiceImpl.class);

    @Value("#{${ftpServer.maps}}")
    private Map<String, String> maps;

    /**
     * 下载文件并获取路径
     *
     * @param contact
     * @param path
     * @return
     * @throws Exception
     */
    public ContactDir downloadRecord(Contact contact, String path) {
        String contactId = contact.getContactId().toString();
        String recordFile = contact.getRecordFile().toString();
        String locationId = contact.getLocationId().toString();

        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }

        try {
            FtpConfigurBean ftpConfigurBean = new FtpConfigurBean(maps.get(recordFile.substring(0, 1) + locationId));
            FTPServerInterface ftp = new FTPManager().getFtpConn(ftpConfigurBean);
            recordFile = recordFile.replace(recordFile.substring(0, 2), ftpConfigurBean.getParentPath());
            ftp.download(recordFile, path + File.separator + contactId + ".wav");
        } catch (Exception e) {
            logger.error("录音获取失败：" + e);
            return null;
        }

        ContactDir contactDir = new ContactDir();
        contactDir.setWavDir("contact/" + contactId + ".wav");
        return contactDir;
    }
}
