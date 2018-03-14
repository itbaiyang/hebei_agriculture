package com.zrodo.agriculture.controller;

import com.zrodo.agriculture.util.FtpFileUtil;
import com.zrodo.agriculture.util.Tool;
import com.zrodo.agriculture.util.json.JsonMapUtils;
import com.zrodo.agriculture.util.json.JsonStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Api(value = "文件上传", description = "文件上传")
@RestController
@Component
public class FileController {
    @PostMapping(value = "/upload")
    public String uploadImg(@RequestParam("file") MultipartFile file,
                            @ApiParam(name = "type", value = "图片分类")
                            @RequestParam(required = false, defaultValue = "1") Integer type,
                            HttpServletRequest request) throws IOException {

        String json;
        try {
            String fileName = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            String filePath = null;

            Boolean flag = FtpFileUtil.uploadFile(fileName, inputStream, type);
            if (!flag) {
                return JsonStatus.failure();
            }
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("fileName", fileName);
            json = Tool.getJsonFromObect(map);
        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }
}
