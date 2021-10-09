package com.afanty.base.test.ftp.rest;

import com.afanty.base.test.common.web.ResponseResult;
import com.afanty.base.test.common.web.StatusCode;
import com.afanty.base.test.ftp.entity.Contact;
import com.afanty.base.test.ftp.entity.ContactDir;
import com.afanty.base.test.ftp.service.FtpServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

/**
 * <p>
 * ftp控制器层
 * </p>
 *
 * @Author yejx
 * @Date 2020/5/16
 */
@RestController
@RequestMapping(value = "/ftp")
@Api(value = "ftp", description = "FTP接口")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "请求已完成"),
        @ApiResponse(code = 201, message = "资源成功被创建"),
        @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
        @ApiResponse(code = 401, message = "未授权客户机访问数据"),
        @ApiResponse(code = 403, message = "服务器接受请求，但是拒绝处理"),
        @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
        @ApiResponse(code = 500, message = "服务器出现异常")})
public class FtpRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FtpRest.class);

    @Resource(name = "ftpService")
    private FtpServiceImpl ftpService;

    @Value("${ftpServer.saveDir}")
    private String saveDir;

    @ApiOperation(value = "加载录音", notes = "加载录音", response = ResponseResult.class)
    @RequestMapping(value = "/getRecordDir", method = RequestMethod.GET)
    public ResponseResult getRecordDir(@RequestParam Map<String, Object> param, HttpServletRequest request) {
        ResponseResult rr = new ResponseResult();
        String contactId = MapUtils.getString(param, "contactId");
        String recordFile = MapUtils.getString(param, "recordFile");
        if (StringUtils.isEmpty(contactId) || StringUtils.isEmpty(recordFile)) {
            rr.setStatus(StatusCode.CODE_1000.getKey());
            rr.setMsg(StatusCode.CODE_1000.getDesc());
            return rr;
        }

        String locationId = MapUtils.getString(param, "locationId");
        if (StringUtils.isEmpty(locationId)) {
            locationId = "";
        }
        Contact contact = new Contact(contactId, recordFile, locationId);

        String path = request.getSession().getServletContext().getRealPath("/") + File.separator + saveDir;
        File mp3File = new File(path + File.separator + contactId + ".wav");
        LOGGER.info("查找录音路径：" + mp3File.getAbsolutePath());
        ContactDir contactDir;
        if (mp3File.exists() && mp3File.length() > 0) {
            contactDir = new ContactDir();
            contactDir.setWavDir("contact/" + contactId + ".wav");
        } else {
            contactDir = ftpService.downloadRecord(contact, path);
        }
        rr.setData(contactDir);
        return rr;
    }

    @ApiOperation(value = "通用的不符合导入规则的数据下载", notes = "通用的不符合导入规则的数据下载")
    @RequestMapping(value = "/invalid/download", method = RequestMethod.GET)
    public void downloadInvalidData(@RequestParam String filename, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String path = request.getSession().getServletContext().getRealPath("/") + File.separator + saveDir;
        //因目前没有分布式文件存储系统，暂时只完成功能，只支持单节点，等有文件系统后将文件推送到文件系统后进行下载
        File tempDir = new File(path);
        File tmpFile = new File(tempDir, filename);
        this.download(response, new FileInputStream(tmpFile), filename, request);
    }

    /**
     * 描  述: 流形式下载文件
     * 参  数:response
     * 参  数:in
     * 参  数:fileName
     * 返回值: void
     * 创建时间: 2018/5/25
     */
    public void download(HttpServletResponse response, InputStream in, String fileName, HttpServletRequest request) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String header = request.getHeader("User-Agent").toUpperCase();
        if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
            fileName = URLEncoder.encode(fileName, "utf-8");
            fileName = fileName.replace("+", "%20");    //IE下载文件名空格变+号问题
        } else {
            fileName = new String(fileName.getBytes(), "ISO8859-1");
        }

//        String attachName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(in);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (IOException e) {
            LOGGER.error("流形式下载文件：{}", e.getMessage());
        } finally {
            if (null != bis) {
                try {
                    bis.close();
                } catch (IOException e) {
                    LOGGER.error("缓冲输入流关闭异常：{}", e.getMessage());
                }
            }
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    LOGGER.error("缓冲输出流关闭异常：{}", e.getMessage());
                }
            }
        }
    }

}
