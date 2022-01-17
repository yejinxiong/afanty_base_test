package com.afanty.base.test.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @Author yejx
 * @Date 2022/1/16
 */
public class CodeGenerator {

    private static String url = "jdbc:mysql://127.0.0.1:3306/afanty_test?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    private static String username = "root";
    private static String password = "root";
    private static String tableName = "tbl_sys_dict";
    private static String parentPackage = "com.afanty.base.test.system";
    private static String moduleName = "dict";
    private static String outputDir = System.getProperty("user.dir") + "/src/main/java";
    private static String mapperXmlDir = System.getProperty("user.dir") + "/src/main/resources/mapper";

    public static void main(String[] args) {

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> builder.fileOverride()
                        .disableOpenDir()
                        .outputDir(outputDir)
                        .author("yejx")
//                            .enableKotlin()
                        .enableSwagger()
                        .dateType(DateType.TIME_PACK)
                        .commentDate("yyyy-MM-dd HH:mm:ss"))
                .packageConfig(builder -> builder.parent(parentPackage)
                        .moduleName(moduleName)
                        .entity("entity")
//                            .service("service")
                        .serviceImpl("service")
                        .mapper("mapper")
                        .controller("rest")
//                            .other("other")
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, mapperXmlDir)))
//                .templateConfig((Consumer<TemplateConfig.Builder>) TemplateConfig.Builder::disable)
                .strategyConfig(builder -> builder.enableCapitalMode()
                        .enableSkipView()
                        .disableSqlFilter()
                        .likeTable(new LikeTable(tableName))
//                            .notLikeTable(new LikeTable(""))
                        .addInclude(tableName)
//                            .addExclude("")
                        .addTablePrefix("tbl_sys_")
//                            .addTableSuffix("")
//                            .addFieldPrefix("")
//                            .addFieldSuffix("_flag")

                        .entityBuilder()
//                        .superClass(BaseEntity.class)
//                            .disableSerialVersionUID()
                        .enableChainModel()
                        .enableLombok()
                        .enableRemoveIsPrefix()
                        .enableTableFieldAnnotation()
                        .enableActiveRecord()
//                            .versionColumnName("version")
//                            .versionPropertyName("version")
                        .logicDeleteColumnName("delete_flag")
                        .logicDeletePropertyName("deleteFlag")
                        .naming(NamingStrategy.underline_to_camel)
                        .columnNaming(NamingStrategy.underline_to_camel)
//                        .addSuperEntityColumns("create_user", "create_name", "create_time", "update_user", "update_name", "update_time")
//                            .addIgnoreColumns("age")
//                            .addTableFills(new Column("create_time", FieldFill.INSERT))
//                            .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                        .idType(IdType.ASSIGN_ID)
//                            .formatFileName("%sEntity"))

                        .controllerBuilder()
//                            .superClass(BaseController.class)
//                            .enableHyphenStyle()
                        .enableRestStyle()
                        .formatFileName("%sRest")

                        .serviceBuilder()
//                            .superServiceClass(BaseService.class)
//                            .superServiceImplClass(BaseServiceImpl.class)
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImp")

                        .mapperBuilder()
                        .superClass(BaseMapper.class)
//                        .enableMapperAnnotation()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
//                            .cache(MyMapperCache.class)
                        .formatMapperFileName("%sMapper")
                        .formatXmlFileName("%sMapper"))
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();


//        // 1. 数据库配置
//        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(url, username, password)
//                .dbQuery(new MySqlQuery())
//                .schema("mybatis-plus")
//                .typeConvert(new MySqlTypeConvert())
//                .keyWordsHandler(new MySqlKeyWordsHandler())
//                .build();
//
//        // 2. 全局配置
//        GlobalConfig globalConfig = new GlobalConfig.Builder()
//                .fileOverride()
////                .disableOpenDir()
//                .outputDir(outputDir)
//                .author("yejx")
//                .enableKotlin()
//                .enableSwagger()
//                .dateType(DateType.TIME_PACK)
//                .commentDate("yyyy-MM-dd HH:mm:ss")
//                .build();
//
//        // 3. 包配置
//        PackageConfig packageConfig = new PackageConfig.Builder()
//                .parent("com.afanty.base.test.system")
//                .moduleName("codetype")
//                .entity("entity")
////                .service("service")
//                .serviceImpl("service")
//                .mapper("mapper")
//                .controller("rest")
////                .other("other")
//                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, mapperXmlDir))
//                .build();
//
//        // 4. 模板配置
//        TemplateConfig templateConfig = new TemplateConfig.Builder()
//                .disable()
////                .disable(TemplateType.ENTITY)
////                .entity("/templates/entity.java")
////                .service("/templates/service.java")
////                .serviceImpl("/templates/serviceImpl.java")
////                .mapper("/templates/mapper.java")
////                .mapperXml("/templates/mapper.xml")
////                .controller("/templates/controller.java")
//                .build();
//
//        // 5. 策略配置
//        StrategyConfig strategyConfig = new StrategyConfig.Builder()
//                .enableCapitalMode()
//                .enableSkipView()
//                .disableSqlFilter()
//                .likeTable(new LikeTable("tbl_sys_code_type"))
////                .notLikeTable(new LikeTable(""))
//                .addInclude("tbl_sys_code_type")
////                .addExclude("")
////                .addTablePrefix("t_", "c_")
////                .addTableSuffix("")
////                .addFieldPrefix("")
////                .addFieldSuffix("_flag")
//                .build();
//
//        // 5.1. 策略配置-entity
//        StrategyConfig entityStrategyConfig = new StrategyConfig.Builder()
//                .entityBuilder()
//                .superClass(BaseEntity.class)
////                .disableSerialVersionUID()
//                .enableChainModel()
//                .enableLombok()
//                .enableRemoveIsPrefix()
//                .enableTableFieldAnnotation()
//                .enableActiveRecord()
////                .versionColumnName("version")
////                .versionPropertyName("version")
//                .logicDeleteColumnName("delete_flag")
//                .logicDeletePropertyName("deleteFlag")
//                .naming(NamingStrategy.underline_to_camel)
//                .columnNaming(NamingStrategy.underline_to_camel)
//                .addSuperEntityColumns("create_user", "create_name", "create_time", "update_user", "update_name", "update_time")
////                .addIgnoreColumns("age")
////                .addTableFills(new Column("create_time", FieldFill.INSERT))
////                .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
//                .idType(IdType.ASSIGN_ID)
////                .formatFileName("%sEntity")
//                .build();
//
//        // 5.1. 策略配置-controller
//        StrategyConfig restStrategyConfig = new StrategyConfig.Builder()
//                .controllerBuilder()
////                .superClass(BaseController.class)
////                .enableHyphenStyle()
//                .enableRestStyle()
//                .formatFileName("%sRest")
//                .build();
//
//        // 5.1. 策略配置-service
//        StrategyConfig serviceStrategyConfig = new StrategyConfig.Builder()
//                .serviceBuilder()
////                .superServiceClass(BaseService.class)
////                .superServiceImplClass(BaseServiceImpl.class)
//                .formatServiceFileName("%sService")
//                .formatServiceImplFileName("%sServiceImp")
//                .build();
//
//        // 5.1. 策略配置-mapper
//        StrategyConfig mapperStrategyConfig = new StrategyConfig.Builder()
//                .mapperBuilder()
//                .superClass(BaseMapper.class)
//                .enableMapperAnnotation()
//                .enableBaseResultMap()
//                .enableBaseColumnList()
////                .cache(MyMapperCache.class)
//                .formatMapperFileName("%sMapper")
//                .formatXmlFileName("%sMapper")
//                .build();

    }
}
