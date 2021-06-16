package com.study.myself.xycommon.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @program: xy-parent
 * @description: 配置文件转换工具类
 * @author: wxy
 * @create: 2021-06-16 15:52
 **/
public class YamlUtils {

    public static String yamlToJson(String file) {
        String json = "";
        try {
            FileInputStream fis = new FileInputStream(file);
            Yaml yaml = new Yaml();
            json = yaml.dump(yaml.load(fis));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    public static String convertYamlToJson(String yaml) {
        ObjectMapper jsonWriter = new ObjectMapper();
        String content = "";
        try {
            ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
            Object obj = yamlReader.readValue(yaml, Object.class);
            content = jsonWriter.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static void createYmlFile(String url, Map<String, String> data) {
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(dumperOptions);

        File dumpfile = new File(url);
        //文件存在则删掉重新创建
        if (!dumpfile.exists()) {
            dumpfile.delete();
            dumpfile = new File(url);
        }
        try (FileWriter writer = new FileWriter(dumpfile)) {
            yaml.dump(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 將json轉化為yaml格式并生成yaml文件
     *
     * @param yamlUrl    写入的文件地址
     * @param jsonString json内容
     */
    public static void createYaml(String yamlUrl, String jsonString) {
        // parse JSON
        JsonNode jsonNodeTree = null;
        // save it as YAML
        String jsonAsYaml = null;
        try {
            //读取 JSON 字符串
            jsonNodeTree = new ObjectMapper().readTree(jsonString);
            //转换成 YAML 字符串
            jsonAsYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);
            System.out.println("jsonAsYaml-----》" + jsonAsYaml);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Yaml yaml = new Yaml();
        Map<String, Object> map = (Map<String, Object>)yaml.load(jsonAsYaml);
        createYamlFile(yamlUrl, map);
    }

    /**
     * 将数据写入yaml文件
     *
     * @param url  yaml文件路径
     * @param data 需要写入的数据
     */
    public static void createYamlFile(String url, Map<String, Object> data) {
        Yaml yaml = new Yaml();
        FileWriter writer;
        try {
            writer = new FileWriter(url);
            yaml.dump(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
