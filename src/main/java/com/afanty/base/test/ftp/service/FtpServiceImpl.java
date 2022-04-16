package com.afanty.base.test.ftp.service;

import com.afanty.base.test.common.exception.CustomException;
import com.afanty.base.test.ftp.config.FTPConfig;
import com.afanty.base.test.ftp.entity.Contact;
import com.afanty.base.test.ftp.entity.ContactDir;
import com.afanty.base.test.ftp.utils.FTPManager;
import com.afanty.base.test.ftp.utils.FTPServerInterface;
import com.afanty.base.test.ftp.utils.FTPUtil;
import com.afanty.base.test.ftp.utils.FtpConfigurBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    private static final DateTimeFormatter yyyyMMddHHmmssSSSFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    @Autowired
    private FTPConfig ftpConfig;

    @Value("#{${ftpServer.maps}}")
    private Map<String, String> maps;

    @Value("${spring.application.name}")
    private String applicationName;


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

    /**
     * 上传文件至FTP
     *
     * @param file
     * @return
     */
    public Map<String, String> upload2FTP(MultipartFile file) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        // 校验文件
        if (Objects.isNull(file)) {
            throw new CustomException("未检测到文件！");
        }
        // 校验文件名
        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isBlank(originalFilename)) {
            throw new CustomException("文件名不能为空！");
        }
        // 生成新的文件名（原名称-当前时间戳-原后缀名）
        String newFileName = originalFilename.substring(0, originalFilename.lastIndexOf(".")) + "-" + yyyyMMddHHmmssSSSFormatter.format(LocalDateTime.now()) + originalFilename.substring(originalFilename.lastIndexOf("."));
        // 初始化FTP
        FTPUtil ftpUtil = new FTPUtil(ftpConfig.getHost(), ftpConfig.getPort(), ftpConfig.getAccount(), ftpConfig.getPassword());
        // 生成文件输入流
        InputStream inputStream = file.getInputStream();
        // 生成文件存储路径
        String datePath = File.separator + applicationName + File.separator + DateFormatUtils.format(new Date(), "yyyy" + File.separator + "MM" + File.separator + "dd");
        // 上传文件
        boolean uploadFlag = ftpUtil.uploadFile(datePath, newFileName, inputStream);
        // 校验上传结果
        if (uploadFlag) {
            resultMap.put("fileName", newFileName);
            resultMap.put("url", datePath + File.separator + newFileName);
            return resultMap;
        } else {
            return null;
        }
    }
}
