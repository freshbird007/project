
package com.leaves.smalltiger.common.utils;

import java.io.File;
import java.io.FileInputStream;
import com.qiniu.util.*;
import okhttp3.*;
public class Put64ToQiniu {
    String ak = "AICrsCmtGe-dWL0Rpy8ad3--pL9OUbzSNlrZbAHS";
    String sk = "CWZ4koN9H5vkdQ0Aptedn8YvRD01mdsYEOVYHu_Q";    // 密钥配置
    Auth auth = Auth.create(ak, sk);    // TODO Auto-generated constructor stub
    String bucketname = "loveleaves";    //空间名
    String key = "shark";    //上传的图片名
    public String getUpToken() {
        return auth.uploadToken(bucketname, null, 3600, new StringMap().put("insertOnly", 1));
    }
    public void put64image() throws Exception {
        String file = "D:\\Program Files\\test\\aa.jpg";//图片路径
        FileInputStream fis = null;
        int l = (int) (new File(file).length());
        byte[] src = new byte[l];
        fis = new FileInputStream(new File(file));
        fis.read(src);
        String file64 = Base64.encodeToString(src, 0);
        String url = "http://q1ziatbln.bkt.clouddn.com" + l+"/key/"+ UrlSafeBase64.encodeToString(key);
        //非华东空间需要根据注意事项 1 修改上传域名
        RequestBody rb = RequestBody.create(null, file64);
        Request request = new Request.Builder().
                url(url).
                addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + getUpToken())
                .post(rb).build();
        System.out.println(request.headers());
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);
    }
//    public static void main(String[] args) throws Exception {
//        new Put64ToQiniu().put64image();
//    }
}



