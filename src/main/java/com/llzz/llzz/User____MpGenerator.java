package com.llzz.llzz;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import net.bytebuddy.asm.Advice.This;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author cth
 * @date 2019/06/03
 */
@Profile("test")
public class User____MpGenerator {
	
	 /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;              
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
	
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        String prefix = "User";
        String tableName = scanner("表名");
        String table=tableName.substring(0);
        table=underline_to_camel(table);

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        // 生成文件的输出目录
        gc.setOutputDir(projectPath + "/src/main/java");
        // 作者
        gc.setAuthor("jgl");
        // 生成后打开目录
        gc.setOpen(false);
        // 是否开启二级缓存
        gc.setEnableCache(false);
        // 设置时间类型使用哪个包下的
        gc.setDateType(DateType.ONLY_DATE);
        // 文件命名方式
        gc.setMapperName(prefix + table + "Dao");
        gc.setXmlName(prefix + table + "Dao");
        gc.setEntityName( table + "PO");
        gc.setControllerName(prefix + table + "Controller");
        gc.setServiceName(prefix + table + "Service");
        gc.setServiceImplName(prefix + table + "ServiceImpl");
        // 主键策略
        gc.setIdType(IdType.INPUT);
        //实体属性 Swagger2 注解
        gc.setSwagger2(true);
        
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3307/zz?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.llzz.llzz.api")
                .setEntity("model.po")
                .setMapper("dao")
                .setXml("dao")
                .setController("controller.user")
                .setService("service")
        .setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);

        //配置自定义属性注入
        InjectionConfig injectionConfig = new InjectionConfig() {
            //.vm模板中，通过${cfg.abc}获取属性
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
//                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                map.put("abc", "自定义属性描述");
                this.setMap(map);
            }
        };
        mpg.setCfg(injectionConfig);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 使用自定义模板，不想要生成就设置为null,如果不设置null会使用默认模板
//        templateConfig.setEntity("templates/entity2.java");
//        templateConfig.setController("templates/controller2.java");
//        templateConfig.setMapper("templates/mapper2.java");
//        templateConfig.setService("templates/service2.java");
//        templateConfig.setServiceImpl(null);
//        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setSuperEntityClass("com.llzz.llzz.api.utility.BaseEntity");
        // 使用lombok
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperControllerClass("com.llzz.llzz.api.utility.BaseController");
        strategy.setInclude(tableName);
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
//        strategy.setFieldPrefix("ggwl_","");//  去掉前缀
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
    
    
    private static String underline_to_camel(String s) {
    	
        String[] split = s.split("_");//按空格分隔成数组
        String s2="";
        for (int i = 0; i <split.length ; i++) {
            s2 += split[i].substring(0, 1).toUpperCase()+split[i].substring(1);
        }
        System.out.print(s2+"=============");
        return s2;
    }

}


