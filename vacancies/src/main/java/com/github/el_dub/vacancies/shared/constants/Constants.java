package com.github.el_dub.vacancies.shared.constants;

import com.github.el_dub.vacancies.shared.enums.CategoryType;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public final static Map<String, String> categoryNameMap;
    public final static Map<String, String> locationMap;


    static {
        categoryNameMap = new HashMap<>();
        locationMap = new HashMap<>();
        initLocationMap();
        initCategoryMap();
    }

    private static void initCategoryMap() {
        categoryNameMap.put("Java", CategoryType.JAVA.getCategoryName());
//        categoryNameMap.put(".NET", CategoryType.DOTNET.getCategoryName());
        categoryNameMap.put("PHP", CategoryType.PHP.getCategoryName());
        categoryNameMap.put("QA", CategoryType.QA.getCategoryName());
        categoryNameMap.put("Embedded", CategoryType.EMBEDDED.getCategoryName());
        categoryNameMap.put("C++", CategoryType.EMBEDDED.getCategoryName());
        categoryNameMap.put("Project Manager", CategoryType.PROJECT_MANAGER.getCategoryName());
        categoryNameMap.put("Product Manager", CategoryType.PRODUCT_MANAGER.getCategoryName());
        categoryNameMap.put("Analyst", CategoryType.ANALYST.getCategoryName());
        categoryNameMap.put("Python", CategoryType.PYTHON.getCategoryName());
        categoryNameMap.put("Ruby", CategoryType.RUBY.getCategoryName());
        categoryNameMap.put("iOS/macOS", CategoryType.IOS.getCategoryName());
        categoryNameMap.put("Android", CategoryType.ANDROID.getCategoryName());
        categoryNameMap.put("Front End", CategoryType.FRONT_END.getCategoryName());
        categoryNameMap.put("Design", CategoryType.DESIGN.getCategoryName());
        categoryNameMap.put("Marketing", CategoryType.MARKETING.getCategoryName());
        categoryNameMap.put("DevOps", CategoryType.DEVOPS.getCategoryName());
        categoryNameMap.put("Lead / Architect / CTO", CategoryType.ARCHITECT.getCategoryName());
        categoryNameMap.put("Big Data", CategoryType.DATA_ENGINEER.getCategoryName());
        categoryNameMap.put("Blockchain", "Blockchain");
        categoryNameMap.put("Data Science", CategoryType.DATA_SCIENCE.getCategoryName());
        categoryNameMap.put("DBA", CategoryType.DBA.getCategoryName());
        categoryNameMap.put("Flutter", "Flutter");
        categoryNameMap.put("Finances", "Finances");
        categoryNameMap.put("ERP/CRM", "ERP/CRM");
        categoryNameMap.put("Golang", CategoryType.GOLANG.getCategoryName());
        categoryNameMap.put("HR", CategoryType.HR.getCategoryName());
        categoryNameMap.put("Node.js", CategoryType.NODE_JS.getCategoryName());
        categoryNameMap.put("React Native", "React Native");
        categoryNameMap.put("Rust", CategoryType.RUST.getCategoryName());
        categoryNameMap.put("Sales", CategoryType.SALES.getCategoryName());
        categoryNameMap.put("Salesforce", CategoryType.SALESFORCE.getCategoryName());
        categoryNameMap.put("Scala", CategoryType.SCALA.getCategoryName());
        categoryNameMap.put("Security", CategoryType.SECURITY.getCategoryName());
        categoryNameMap.put("SEO", CategoryType.SEO.getCategoryName());
        categoryNameMap.put("Support", CategoryType.SUPPORT.getCategoryName());
        categoryNameMap.put("SysAdmin", CategoryType.SYS_ADMIN.getCategoryName());
        categoryNameMap.put("Technical Writer", CategoryType.TECHNICAL_WRITER.getCategoryName());
        categoryNameMap.put("Unity", CategoryType.GAMEDEV.getCategoryName());
    }

    private static void initLocationMap() {
        locationMap.put("Львів", "Львів");
        locationMap.put("віддалено", "віддалено");
        locationMap.put("Київ", "Київ");
        locationMap.put("Дніпро", "Дніпро");
        locationMap.put("Одеса", "Одеса");
        locationMap.put("Запоріжжя", "Запоріжжя");
        locationMap.put("Миколаїв", "Миколаїв");
        locationMap.put("Харків", "Харків");
        locationMap.put("Чернігів", "Чернігів");
        locationMap.put("Івано-Франківськ", "Івано-Франківськ");
        locationMap.put("Чернівці", "Чернівці");
        locationMap.put("Ужгород", "Ужгород");
        locationMap.put("Суми", "Суми");
        locationMap.put("Вінниця", "Вінниця");
        locationMap.put("Житомир", "Житомир");
        locationMap.put("Тернопіль", "Тернопіль");
        locationMap.put("Черкаси", "Черкаси");
        locationMap.put("Хмельницький", "Хмельницький");
        locationMap.put("Сумы", "Суми");
        locationMap.put("Херсон", "Херсон");
        locationMap.put("Ровно", "Рівне");
        locationMap.put("Рівне", "Рівне");
        locationMap.put("Луцк", "Луцьк");
        locationMap.put("Луцьк", "Луцьк");
        locationMap.put("Полтава", "Полтава");
        locationMap.put("Бердянськ", "Бердянськ");
        locationMap.put("Кропивницький", "Кропивницький");
        locationMap.put("Sumy", "Суми");
        locationMap.put("Україна", "віддалено");
        locationMap.put("Мариуполь", "Маріуполь");
        locationMap.put("Rivne", "Рівне");
        locationMap.put("Кривой рог", "Кривий Ріг");
        locationMap.put("Poltava", "Полтава");
        locationMap.put("Переїзд до Києва", "Київ");
        locationMap.put("Бедянськ", "Бердянськ");
        locationMap.put("Cуми", "Суми");
        locationMap.put("All Ukraine", "віддалено");
        locationMap.put("Кировоград", "Кіровоград");
        locationMap.put("Маріуполь", "Маріуполь");
        locationMap.put("Кривий Ріг", "Кривий Ріг");
        locationMap.put("Other ukrainian cities", "віддалено");
        locationMap.put("Chernihiv", "Чернігів");
        locationMap.put("Кам'янець-Подільський", "Кам'янець-Подільський");
        locationMap.put("Кропивницкий", "Кропивницький");
        locationMap.put("Умань", "Умань");
        locationMap.put("Only Lviv", "Львів");
        locationMap.put("Краматорск", "Краматорськ");
        locationMap.put("Кременчуг", "Кременчук");
        locationMap.put("Будь-яке", "віддалено");
        locationMap.put("Кременчук", "Кременчук");
        locationMap.put("Ukraine", "віддалено");
        locationMap.put("Kharkiv (Remote)", "Харків");
        locationMap.put("Kyiv (Remote)", "Київ");
        locationMap.put("Lviv (Remote)", "Львів");
        locationMap.put("Remote from anywhere", "віддалено");
        locationMap.put("Ірпінь", "Ірпінь");
        locationMap.put("луцьк", "Луцьк");
        locationMap.put("Суммы", "Суми");
        locationMap.put("ровно", "Рівне");
        locationMap.put("Кам'янець-подільський", "Кам'янець-Подільський");
        locationMap.put("Бориспіль", "Бориспіль");
        locationMap.put("Борисполь", "Бориспіль");
        locationMap.put("полтава", "Полтава");
        locationMap.put("Біла Церква", "Біла Церква");
        locationMap.put("Рiвне", "Рiвне");
        locationMap.put("Краматорськ", "Краматорськ");
        locationMap.put("краматорськ", "Краматорськ");
        locationMap.put("Кривой Рог", "Кривий Ріг");
        locationMap.put("Сумы/ Sumy", "Суми");
        locationMap.put("Kryvyy Rih", "Кривий Ріг");

    }
}
