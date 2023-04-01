package com.fran.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MysqlGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/service-driver-user?characterEncoding=utf-8&serverTimezone=GMT%2B8"
                ,"root","123456")
                .globalConfig(builder -> {
                    builder.author("fran")
                            .outputDir("E:\\works\\online-taxi-public\\service-driver-user\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.fran")
                            .pathInfo(Collections.singletonMap(OutputFile.mapper,"E:\\works\\online-taxi-public\\service-driver-user\\src\\main\\java\\com\\fran\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("car");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
