package com.leaves.smalltiger.common.utils;

import ch.qos.logback.core.util.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.Base64;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@Slf4j
public class QiniuCloudUtil {
//    设置七牛云的云账号的AK和SDK
    private static final String ACCESS_KEY = "AICrsCmtGe-dWL0Rpy8ad3--pL9OUbzSNlrZbAHS";
    private static final String SECRET_KEY = "CWZ4koN9H5vkdQ0Aptedn8YvRD01mdsYEOVYHu_Q";
//    要上传的空间bucket
    private static final String BUCKET = "loveleaves";
//    外链接地址(测试域名，只有30天有效期)
    private static final String DOMAIN = "http://q1ziatbln.bkt.clouddn.com";
// 图片允许的后缀扩展名
    public static String[] IMAGE_FILE_EXTD = new String[] { "png", "bmp", "jpg", "jpeg","pdf" };
// 密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
// 构造一个带指定Zone对象的配置类,不同的七云牛存储区域调用不同的zone
    Configuration cfg = new Configuration(Zone.zone2());
// ...其他参数参考类注释
    UploadManager uploadManager = new UploadManager(cfg);

// 测试域名，只有30天有效期
    private static String QINIU_IMAGE_DOMAIN = "http://q1xyc31b0.bkt.clouddn.com";

// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(BUCKET);
    }

    public String saveImage(MultipartFile file) throws IOException {
        try {
            int dotPos = file.getOriginalFilename().lastIndexOf(".");
            if (dotPos < 0) {
                return null;
            }
            String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
//              判断是否是合法的文件后缀
            if (!QiniuCloudUtil.isFileAllowed(fileExt)) {
                return null;
            }

            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
//              调用put方法上传
            Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());
//              打印返回的信息
            if (res.isOK() && res.isJson()) {
//              返回这张存储照片的地址
                String imgAddress  =DOMAIN +"/" + JSONObject.parseObject(res.bodyString()).get("key");
                log.info("上传的图片地址"+imgAddress);
                return imgAddress;
            } else {
                log.error("七牛异常:" + res.bodyString());
                return null;
            }
        } catch (QiniuException e) {
//           请求失败时打印的异常的信息
            log.error("七牛异常:" + e.getMessage());
            return null;
        }

    }

    /**
     * 图片格式
     * @param fileName
     * @return
     */
    public static boolean isFileAllowed(String fileName) {
        for (String ext : IMAGE_FILE_EXTD) {
            if (ext.equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    /**
     *  获取七牛云的Token
     * @return
     */
    public static String getupToken(){
//        密钥上传的凭证
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        StringMap putPolicy = new StringMap();
//        上传成功后是返回这个json数据      fileUrl就是地址
        putPolicy.put("returnBody","{\"fileUrl\":\""+DOMAIN+"$(key)\"}");
//        过期时间
        long expireSeconds = 3600;

        String upToken = auth.uploadToken(BUCKET,null,expireSeconds, putPolicy);
        log.info("七牛云的upToken======"+upToken);
        return upToken;
    }

    /**
     * base64上传
     * @param base64
     * @param key
     * @return
     * @throws Exception
     */
    public static String uploadFileBase64(byte[] base64, String key) throws Exception{
    String file64 = Base64.encodeToString(base64, 0);
    Integer l = base64.length;
    String uploadUrl = "http://up-z2.qiniup.com/putb64/" + l + "/key/"+ UrlSafeBase64.encodeToString(key);

    RequestBody rb = RequestBody.create(null, file64);
    Request request = new Request.Builder().
    url(uploadUrl)
    .addHeader("Content-Type", "application/octet-stream")
    .addHeader("Authorization", "UpToken " + getupToken())
    .post(rb).build();
    //System.out.println(request.headers());
    OkHttpClient client = new OkHttpClient();
    okhttp3.Response response = client.newCall(request).execute();
    System.out.println(response);
    return DOMAIN + key;
    }

    /**
     * 字节上传
     * @param uploadBytes
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String uploadFileBytes(byte[] uploadBytes, String fileName) throws Exception{

        StringBuffer fileUrl  = new StringBuffer(DOMAIN);


        Configuration cfg = new Configuration(Zone.zone2());

        UploadManager uploadManager = new UploadManager(cfg);

        String key = fileName;


        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET);

        try {

            Response response = uploadManager.put(uploadBytes, key, upToken);

            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            fileUrl.append(putRet.key);
            //MyPutRet myPutRet=response.jsonToObject(MyPutRet.class);
            //System.out.println(myPutRet);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return fileUrl.toString();
    }


    /**
    public static String uploadFile(){
//        文件的外链地址
        StringBuffer fileUrl = new StringBuffer(DOMAIN);
//        构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
//        如果是Windows情况下，格式  D:\\qiniu\\test.png


        return null;
    }*/
}
