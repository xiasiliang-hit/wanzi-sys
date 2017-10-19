package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.MultipartFormData.FilePart;
import util.ImageUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileController extends Controller {
    public static String imgDir = "public/upload/images/";

    /**
     * 处理未裁剪图片 ajaxFileUpload
     * @return
     */
    public static Result onUploadImage(){
        List<FilePart> files = request().body().asMultipartFormData().getFiles();
        if (files != null && files.size() > 0) {
            File img = files.get(0).getFile();
            String fileName = files.get(0).getFilename();
            String newFileName = saveImage(img, fileName);
            Map<String, Object> data = new HashMap<>();
            data.put("name", newFileName);
            data.put("code", 1000);
            return ok(Json.toJson(data));
        } else {
            return badRequest();
        }
    }

    public static Result onUploadThumbImage(){
        Map<String, String[]> formData = request().body().asFormUrlEncoded();
        String name = formData.get("img")[0];
        String x = formData.get("x")[0];
        String y = formData.get("y")[0];
        String w = formData.get("w")[0];
        String h = formData.get("h")[0];
        Map<String, Object> data = new HashMap<>();
        if (!(w.equals("0") && h.equals("0"))) {
            String type = name.substring(name.lastIndexOf("."));
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
            String newFileName = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSS"));
            newFileName += type;
            ImageUtil.cutPic(imgDir + name, imgDir + newFileName, Double.valueOf(x).intValue(), Double.valueOf(y).intValue(), Double.valueOf(w).intValue(), Double.valueOf(h).intValue());
            data.put("name", newFileName);
            data.put("code", 1000);
        } else {
            data.put("name", name);
            data.put("code", 1000);
        }
        return ok(Json.toJson(data));
    }

    private static String saveImage(File img, String fileName){
        String type = fileName.substring(fileName.lastIndexOf("."));
        Path tempFile = Paths.get(img.toURI());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        String newFileName = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSS"));
        newFileName += type;
        Path toFile = Paths.get(imgDir, newFileName);
        try {
            Files.copy(tempFile, toFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFileName;
    }

}
