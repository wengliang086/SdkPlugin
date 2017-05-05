package com.my.test.sdk.util;

import com.google.gson.Gson;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.my.test.sdk.model.Options;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OptionsHelper {

    private static final Logger log = Logger.getInstance(OptionsHelper.class);
    private static final String subPath = "app" + File.separator + "src" + File.separator + "main" + File.separator + "assets" + File.separator + "sdkConfDir";
    private static final String saveFileName = "sdkPackageConfig.json";

    private static String getPathName(Project project) {
        String path = project.getBasePath() + File.separator + subPath;
        if (new File(path).exists()) {
            log.info("path=" + path);
            return path;
        } else {
            if (new File(path).mkdirs()) {
                log.info("目录 dir=" + path + " 创建成功");
                return path;
            } else {
                log.info("目录 dir=" + path + " 创建失败");
            }
            log.info("path=" + project.getBasePath());
            return project.getBasePath();
        }
    }

    public static void mkDir(Project project, String dir) {
        File file = new File(getPathName(project) + File.separator + dir);
        if (file.exists()) {
            return;
        }
        if (file.mkdir()) {
            log.info("目录 dir=" + dir + " 创建成功");
        } else {
            log.info("目录 dir=" + dir + " 创建失败");
        }
    }

    public static void save(Project project, Options options) {
        Path path = Paths.get(getPathName(project) + File.separator + saveFileName);
        try {
            String json = new Gson().toJson(options);
            Files.write(path, json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    public static Options load(Project project) {
        String path = getPathName(project) + File.separator + saveFileName;
        File file = new File(path);
        if (!file.exists()) {
            log.warn(path + " 不存在");
            return null;
        }
        FileReader fr = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            log.error(e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Gson().fromJson(sb.toString(), Options.class);
    }
}
