package com.study.myself.xycommon.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @program: xy-parent
 * @description: 远程调用请求
 * @author: wxy
 * @create: 2021-05-10 15:14
 **/
@Slf4j
public class HttpUtils {
    private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType UPLOAD_FILE = MediaType.parse("application/octet-stream");

    /**
     * 请求第三方http调用
     *
     * @param param     请求参数
     * @param url       请求url
     * @param appId     应用id
     * @param signature signature
     * @return 请求第三方http调用
     */
    public static String postToken(String param, String url, String appId, String signature) {
        String result = "";
        try {
            RequestBody requestBody = RequestBody.create(JSON_TYPE, param);
            //设置链接超时
            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                // 设置写数据超时
                .writeTimeout(10, TimeUnit.SECONDS)
                // 设置读数据超时
                .readTimeout(30, TimeUnit.SECONDS).build();
            Request request = new Request.Builder().url(url).post(requestBody).addHeader("appId", appId)
                .addHeader("signature", signature).build();
            Response response = client.newCall(request).execute();
            result = !ObjectUtils.isEmpty(response.body()) ? response.body().string() : "";
        } catch (IOException e) {
            log.error("post-json请求", e);
        }
        return result;
    }

    /**
     * 请求第三方http调用
     *
     * @param param 请求参数
     * @param url   请求url
     * @return 请求第三方http调用
     */
    public static String post(String param, String url) {
        String result = "";
        try {
            RequestBody requestBody = RequestBody.create(JSON_TYPE, param);
            //设置链接超时
            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                // 设置写数据超时
                .writeTimeout(10, TimeUnit.SECONDS)
                // 设置读数据超时
                .readTimeout(30, TimeUnit.SECONDS).build();
            Request request = new Request.Builder().url(url).post(requestBody).build();
            Response response = client.newCall(request).execute();
            result = !ObjectUtils.isEmpty(response.body()) ? response.body().string() : "";
        } catch (IOException e) {
            log.error("post-json请求", e);
        }
        return result;
    }

    /**
     * 发送文件
     *
     * @param url       请求接口地址
     * @param uploadDir 文件路径
     * @return 接口返回值
     * <p>
     * 该方法前端以formData格式传入，包括文件和参数，可一起传入。
     */

    public String uploadFilePost(String url, String uploadDir) {
        File temporaryFile = new File(uploadDir);
        if (!temporaryFile.exists()) {
            return "";
        }
        RequestBody requestBody = new MultipartBody.Builder()
            .addFormDataPart("file", temporaryFile.getName(), RequestBody.create(UPLOAD_FILE, temporaryFile)).build();
        Request requestOk = new Request.Builder().url(url).post(requestBody).build();
        Response response;
        String result = "";
        try {
            response = new OkHttpClient().newCall(requestOk).execute();
            result = !ObjectUtils.isEmpty(response.body()) ? response.body().string() : "";
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;

    }

    /**
     * 发送文件
     *
     * @param url         请求接口地址
     * @param uploadDir   参数上传目录
     * @param baseFileUrl 文件保存基准路径
     * @param relativeUrl 文件保存的相对路径
     * @return 接口返回值
     * <p>
     * 该方法前端以formData格式传入，包括文件和参数，可一起传入。
     */

    public String uploadFilePost(String url, String uploadDir, String baseFileUrl, String relativeUrl) {
        String result = "";
        File temporaryFile = new File(baseFileUrl + relativeUrl);
        if (!temporaryFile.exists()) {
            return "";
        }
        RequestBody requestBody = new MultipartBody.Builder()
            //参数一，可注释掉
            .addFormDataPart("uploadDir", uploadDir)
            //参数二，可注释掉
            .addFormDataPart("fileUrl", relativeUrl)
            //文件一
            .addFormDataPart("file", temporaryFile.getName(), RequestBody.create(UPLOAD_FILE, temporaryFile))
            .build();
        Request requestOk = new Request.Builder().url(url).post(requestBody).build();
        Response response;
        try {
            response = new OkHttpClient().newCall(requestOk).execute();
            result = !ObjectUtils.isEmpty(response.body()) ? response.body().string() : "";
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;

    }
}
