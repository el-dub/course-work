package com.github.el_dub.vacancies.shared.enums;

import java.util.List;

public enum CategoryType {

    JAVA("Java", List.of("keyword-java/")),
//    DOTNET(".NET", List.of("keyword-dotnet/")),
    PHP("PHP", List.of("keyword-php/")),
    EMBEDDED("C++ / Embedded", List.of("keyword-cplusplus/")),
    QA("QA", List.of("keyword-qa-automation/", "keyword-qa/")),
    PROJECT_MANAGER("Project Manager", List.of("keyword-project-manager/")),
    PRODUCT_MANAGER("Product Manager", List.of("keyword-product-manager/")),
    ANALYST("Analyst", List.of("keyword-business-analyst/", "keyword-data-analyst/")),
    PYTHON("Python", List.of("keyword-python/")),
    RUBY("Ruby", List.of("keyword-ruby/")),
    IOS("iOS/macOS", List.of("keyword-ios/")),
    ANDROID("Android", List.of("keyword-android")),
    FRONT_END("Front End", List.of("keyword-javascript/?keywords=front-end")),
    DESIGN("Design", List.of("keyword-design/", "keyword-artist/")),
    MARKETING("Marketing", List.of("keyword-marketing/")),
    DEVOPS("DevOps", List.of("keyword-devops/")),
    ARCHITECT("Lead / Architect / CTO", List.of("keyword-lead/")),
    DATA_ENGINEER("Data engineer", List.of("keyword-data-engineer/")),
    DATA_SCIENCE("Data Science", List.of("keyword-data-science/")),
    DBA("DBA", List.of("keyword-sql/")),
    GOLANG("Golang", List.of("keyword-golang/")),
    HR("HR", List.of("keyword-hr/")),
    NODE_JS("Node.js", List.of("keyword-node.js/")),
    RUST("Rust", List.of("?keywords=rust")),
    SALES("Sales", List.of("keyword-sales/")),
    SALESFORCE("Salesforce", List.of("?keywords=salesforce")),
    SCALA("Scala", List.of("keyword-scala/")),
    SECURITY("Security", List.of("keyword-security/")),
    SEO("SEO", List.of("keyword-seo/")),
    SUPPORT("Support", List.of("keyword-support/")),
    SYS_ADMIN("SysAdmin", List.of("keyword-sysadmin/")),
    TECHNICAL_WRITER("Technical Writer", List.of("keyword-technical-writing/")),
    GAMEDEV("Gamedev / Unity", List.of("keyword-unity/"));

    private String categoryName;

    private List<String> links;

    CategoryType(String categoryName, List<String> links) {
        this.categoryName = categoryName;
        this.links = links;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}
