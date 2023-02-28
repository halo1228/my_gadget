package com.lg;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.lg.util.Psf;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author liuga
 * @date 2023/02/01 11:05
 * Description: 代码生成
 * <p>
 *    采用的是3.5.2版本生成器
 * </p>
 */
public class CodeGenerator2 {

    /**
     * 代码生成
     */
    public static void main(String[] args) {

        //输出路径
        Map<OutputFile, String> pathInfo = new LinkedHashMap<>();
        pathInfo.put(OutputFile.xml, Psf.PROJECT_PATH + "/src/main/resources/mapper/" + Psf.MODULE_NAME);

        FastAutoGenerator.create(Psf.JDBC_URL,
                        Psf.JDBC_USERNAME,
                        Psf.JDBC_PASSWORD)

                // 全局配置
                .globalConfig(builder -> {
                    builder
                            //.enableSwagger() // 是否启用swagger注解
                            .author(Psf.AUTHOR) // 作者名称
                            .dateType(DateType.ONLY_DATE) // 时间策略
                            .commentDate("yyyy-MM-dd") // 注释日期
                            .outputDir(Psf.PROJECT_PATH + "/src/main/java") // 输出目录
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir(); // 生成后禁止打开所生成的系统目录
                })

                // 包配置
                .packageConfig(builder -> {
                    builder
                            .parent(Psf.PACKAGE_NAME) // 父包名
                            .moduleName(Psf.MODULE_NAME) // 模块包名
                            .entity("entity") // 实体类包名
                            .service("service") // service包名
                            .serviceImpl("service.impl") // serviceImpl包名
                            .mapper("mapper") // mapper包名
                            //.pathInfo(Collections.singletonMap(OutputFile.xml, PROJECT_PATH + "/src/main/resources/mappers")) // xml位置（还可自定义配置entity，service等位置）
                            .pathInfo(pathInfo)
                            .controller("controller"); // controller包名
                    //.other("model"); // 自定义包名
                })

                // 策略配置
                .strategyConfig(builder -> {
                    builder
                            .addTablePrefix("t_", "sys_") // 增加过滤表前缀
                            .addTableSuffix("_db") // 增加过滤表后缀
                            .addFieldPrefix("t_") // 增加过滤字段前缀
                            .addFieldSuffix("_field") // 增加过滤字段后缀
                            //.addInclude("sys_dept") // 表匹配

                            // Entity 策略配置
                            .entityBuilder()
                            .enableLombok() // 开启lombok
                            .enableChainModel() // 链式
                            .enableRemoveIsPrefix() // 开启boolean类型字段移除is前缀
                            .enableTableFieldAnnotation() //开启生成实体时生成的字段注解
                            .versionColumnName("version") // 乐观锁数据库字段
                            .versionPropertyName("version") // 乐观锁实体类名称
                            .logicDeleteColumnName("is_deleted") // 逻辑删除数据库中字段名
                            .logicDeletePropertyName("deleted") // 逻辑删除实体类中的字段名
                            .naming(NamingStrategy.underline_to_camel) // 表名 下划线 -》 驼峰命名
                            .columnNaming(NamingStrategy.underline_to_camel) // 字段名 下划线 -》 驼峰命名
                            .idType(IdType.ASSIGN_ID) // 主键生成策略 雪花算法生成id
                            .formatFileName("%s") // Entity 文件名称
                            .addTableFills(new Column("create_time", FieldFill.INSERT)) // 表字段填充
                            .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE)) // 表字段填充
                            .enableColumnConstant()//开启生成字段常量
                            .enableActiveRecord()//开启 ActiveRecord 模型

                            // Controller 策略配置
                            .controllerBuilder()
                            .enableRestStyle() // 开启@RestController
                            .formatFileName("%sController")// Controller 文件名称

                            // Service 策略配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService") // Service 文件名称
                            .formatServiceImplFileName("%sServiceImpl") // ServiceImpl 文件名称

                            // Mapper 策略配置
                            .mapperBuilder()
                            .enableMapperAnnotation() // 开启@Mapper
                            .enableBaseColumnList() // 启用 columnList (通用查询结果列)
                            .enableBaseResultMap() // 启动resultMap
                            .formatMapperFileName("%sMapper") // Mapper 文件名称
                            .formatXmlFileName("%sMapper"); // Xml 文件名称

                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                //.templateEngine(new FreemarkerTemplateEngine())
                //指定模板位置
                .templateConfig(builder -> {
                    builder
                            //.disable(TemplateType.ENTITY)//禁用模板
                            .entity("/templates/entity.java")
                            .service("/templates/service.java")
                            .serviceImpl("/templates/serviceImpl.java")
                            .mapper("/templates/mapper.java")
                            .xml("/templates/mapper.xml")
                            .controller("/templates/controller.java")
                            .build();
                })
                .execute(); // 执行
    }

}
