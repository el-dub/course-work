package com.github.el_dub.vacancies.service.impl;

import com.github.el_dub.vacancies.dto.VacancyData;
import com.github.el_dub.vacancies.model.*;
import com.github.el_dub.vacancies.repository.*;
import com.github.el_dub.vacancies.service.ScrapingService;
import com.github.el_dub.vacancies.shared.constants.Constants;
import com.github.el_dub.vacancies.shared.enums.CategoryType;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScrapingServiceImpl implements ScrapingService {

    private final SourceRepository sourceRepository;

    private final CategoryRepository categoryRepository;

    private final CompanyRepository companyRepository;

    private final VacancyRepository vacancyRepository;

    private final TitleRepository levelRepository;

    private final LocationRepository cityRepository;

    @Value("${DOU_URL}")
    private String DOU_URL;

    @Value("${DJINNI_URL}")
    private String DJINNI_URL;

    private static final int VACANCIES_PER_PAGE = 15;

    private static final String PAGE_PARAM = "?page=";

    private final Map<String, Integer> months = new HashMap<>();

    {
        months.putAll(Map.of("січня", 1,
                "лютого", 2,
                "березня", 3,
                "квітня", 4,
                "травня", 5,
                "червня", 6,
                "липня", 7,
                "серпня", 8,
                "вересня", 9,
                "жовтня", 10));
        months.putAll(Map.of(
                "листопада", 11,
                "грудня", 12));
    }

    //    private String currentHandle;
    private String startPageHandle;

    private ChromeDriver driver;

    public void scrapeDouVacancies() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-gpu", "--no-sandbox");
//        options.setBinary("selenium/chromedriver.exe");
//        driver = new ChromeDriver(options);
        Resource resource = new ClassPathResource("selenium/chromedriver.exe");
        String filePath = null;
        try {
            filePath = resource.getFile().getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty("webdriver.chrome.driver", filePath);
        driver = new ChromeDriver();
        List<VacancyData> vacancies = getVacancies();
        vacancies.forEach(System.out::println);
        saveVacancies(vacancies, "dou.ua");

        driver.quit();
    }

    @Override
    public void scrapeDjinniVacancies() {
        Map<String, String> categoryNameLinkMap = new HashMap<>();
        Arrays.stream(CategoryType.values()).forEach(categoryType -> {
            String name = categoryType.getCategoryName();
            categoryType.getLinks().forEach(link -> categoryNameLinkMap.put(link, name));
        });
        categoryNameLinkMap.entrySet()
//                .parallelStream()
                .forEach(entry -> processCategory(entry.getKey(), entry.getValue()));
    }

    public void processCategory(String link, String name) {
        String categoryLink = DJINNI_URL + link;
        System.out.println(categoryLink);
        List<String> vacancyLinks = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(categoryLink).get();
            int vacanciesNumber = Integer.parseInt(doc.selectFirst("h1>span.text-muted").text());
            int pagesNumber = (int) Math.ceil((double) vacanciesNumber / VACANCIES_PER_PAGE);
            vacancyLinks.addAll(doc.select(".list-jobs__title>a")
                    .eachAttr("href").stream()
                    .map(vLink -> "https://djinni.co" + vLink)
                    .collect(Collectors.toList()));
            for (int i = 2; i <= pagesNumber; i++) {
                doc = Jsoup.connect(categoryLink + PAGE_PARAM + i).get();
                vacancyLinks.addAll(doc.select(".list-jobs__title>a")
                        .eachAttr("href").stream()
                        .map(vLink -> "https://djinni.co" + vLink)
                        .collect(Collectors.toList()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        saveVacancies(vacancyLinks.stream()
                .map(url -> getVacancy(name, url))
                .collect(Collectors.toList()), "djinni.co");
    }

    private VacancyData getVacancy(String categoryName, String vacancyUrl) {
        try {
            Document doc;
            try {
                doc = Jsoup.connect(vacancyUrl).get();
                String name = doc.selectFirst("h1").text();
                String description = doc.select(".profile-page-section").stream()
                        .map(Element::text)
                        .collect(Collectors.joining("\n"));
                Element companyElement = doc.selectFirst(".profile-page-section>h4");
                String companyName = "";
                if (Objects.nonNull(companyElement) && companyElement.text().split("Про компанію ").length >= 2) {
                    companyName = companyElement.text().split("Про компанію ")[1];
                } else {
                    companyElement = doc.selectFirst(".list-jobs__details__info :nth-child(2)");
                    if (Objects.nonNull(companyElement)) {
                        companyName = companyElement.text();
                    }
                }

                Element locationElement = doc.selectFirst(".location-text");
                List<String> locationNames = new ArrayList<>();
                if (Objects.nonNull(locationElement)) {
                    locationNames = Arrays.asList(locationElement.text().split(", "));
                } else {
                    locationNames.add("віддалено");
                }
                String placementDate = doc.selectFirst("p.text-muted").text()
                        .split("Вакансія опублікована ")[1]
                        .split(" \"")[0];
                return VacancyData.builder()
                        .name(name)
                        .category(Constants.categoryNameMap.get(categoryName))
                        .placementDate(placementDate)
                        .company(companyName)
                        .description(description)
                        .locations(locationNames)
                        .link(vacancyUrl)
                        .build();
            } catch (SocketTimeoutException e) {
                System.out.println(vacancyUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return VacancyData.builder().build();
    }


    private List<WebElement> scrapeCategories() {
        driver.get(DOU_URL);
        startPageHandle = driver.getWindowHandle();
        return driver.findElements(By.cssSelector("a.cat-link"));
    }

    private List<VacancyData> scrapeVacancies(List<WebElement> categoryLinks) {
        Map<String, List<WebElement>> vacanciesByCategories = new HashMap<>();
        List<VacancyData> vacancyData = new ArrayList<>();
        categoryLinks.stream()
                .filter(category -> Constants.categoryNameMap.containsKey(category.getText()))
                .forEach(category -> {
                    String categoryName = category.getText();
                    openNewTab(category);
                    switchToTab(1);
                    int vacanciesNum = Integer.parseInt(driver.findElement(By.cssSelector(".b-inner-page-header h1")).getText().split(" ")[0]);
//            category.click();
                    WebElement moreBtn;
                    try {
                        moreBtn = driver.findElement(By.cssSelector(".more-btn a"));
                        int current = 20;
                        while (current < vacanciesNum) {
//                openNewTab(moreBtn.get(0));
                            moreBtn.click();
                            current += 40;
                            try {
                                moreBtn = driver.findElement(By.cssSelector(".more-btn a"));
                            } catch (NoSuchElementException e) {
                                break;
                            }
                        }
                    } catch (NoSuchElementException e) {
//                        e.printStackTrace();
                    }
                    List<WebElement> vacancyLinks = driver.findElements(By.cssSelector("a.vt"));
                    vacancyData.addAll(scrapeVacancies(vacancyLinks, categoryName));
//            vacanciesByCategories.put(categoryName, vacancyLinks);
//            driver.navigate().back();
                    changeTab();
                });
        return vacancyData;
    }

    private List<VacancyData> getVacancies() {
        List<WebElement> categories = scrapeCategories();
//        Map<String, List<WebElement>> vacanciesByCategories = scrapeVacancyLists(categories);
        List<VacancyData> allVacancies = new ArrayList<>();
//        vacanciesByCategories.entrySet()
//                .stream().map(entry -> {
//                    List<VacancyData> vacancyData = scrapeVacancies(entry.getValue());
//                    vacancyData.forEach(vacancy -> vacancy.setCategory(entry.getKey()));
//                    return vacancyData;
//                })
//                .forEach(allVacancies::addAll);
        return scrapeVacancies(categories);
    }

    private List<VacancyData> scrapeVacancies(List<WebElement> vacancies, String categoryName) {
        String categoryTab = driver.getWindowHandle();
        return vacancies.stream().map(vacancy -> {
            openNewTab(vacancy);
            switchToTab(2);
//            vacancy.click();
            String name = driver.findElementByCssSelector(".l-vacancy>h1.g-h2").getText();
            String placementDate = driver.findElementByCssSelector("div.date").getText();
            String place;
            try {
                place = driver.findElementByCssSelector("span.place").getText();
            } catch (NoSuchElementException e) {
                place = null;
            }
            String description = driver.findElementByCssSelector(".vacancy-section").getText();
            String link = driver.findElement(By.xpath("//meta[@property='og:url']"))
                    .getAttribute("content");
            String company = driver.findElementByCssSelector(".info .l-n a").getText();
//            driver.navigate().back();
            changeTab(categoryTab);
            return VacancyData.builder()
                    .name(name)
                    .placementDate(placementDate)
                    .locations(place != null ?
                            Arrays.asList(place.split(", ")) :
                            new ArrayList<>())
                    .description(description)
                    .link(link)
                    .category(categoryName)
                    .company(company)
                    .build();
        }).collect(Collectors.toList());
    }

    private void changeTab() {
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(startPageHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }

        driver.switchTo().window(startPageHandle);
    }

    private void changeTab(String currentHandle) {
        driver.close();

        switchToTab(1);
    }

    private void switchToTab(int number) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(number));
    }

    private void openNewTab(WebElement elemToClick) {
        new Actions(driver).keyDown(Keys.LEFT_CONTROL)
                .click(elemToClick)
                .keyUp(Keys.LEFT_CONTROL)
                .build()
                .perform();
    }

    private void saveVacancies(List<VacancyData> vacancyDataList, String sourceName) {
        Source source = sourceRepository
                .findByName(sourceName)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Source with name %s was not found", sourceName)));
        List<Vacancy> vacancies = vacancyDataList.stream().map(vacancyData -> Vacancy.builder()
                        .description(vacancyData.getDescription())
                        .name(vacancyData.getName())
                        .placementTime(parseDate(vacancyData.getPlacementDate()))
                        .source(source)
                        .link(vacancyData.getLink())
                        .category(categoryRepository.existsByName(vacancyData.getCategory()) ?
                                categoryRepository.findByName(vacancyData.getCategory()).get()
                                : saveCategory(vacancyData.getCategory()))
                        .company(companyRepository.existsByName(vacancyData.getCompany()) ?
                                companyRepository.findByName(vacancyData.getCompany()).get()
                                : saveCompany(vacancyData.getCompany()))
                        .level(getLevelFromDescription(source.getName(), vacancyData.getDescription()))
                        .locations(vacancyData.getLocations().stream()
                                .map(location -> Constants.locationMap.getOrDefault(location, "за кордоном"))
                                .map(location ->
                                cityRepository.existsByName(location) ?
                                        cityRepository.findByName(location).get()
                                        : cityRepository.save(Location.builder().name(location).build())).collect(Collectors.toSet()))
                        .build())
                .filter(vacancy -> !vacancyRepository.existsByLinkAndCategoryName(vacancy.getLink(), vacancy.getCategory().getName()))
                .collect(Collectors.toList());
        vacancyRepository.saveAll(vacancies);
    }

    private Title getLevelFromDescription(String name, String description) {
        String level;
        if (name.toLowerCase().contains("senior") || name.contains("Sr")) {
            level = "Senior";
        } else if (name.toLowerCase().contains("lead")) {
            level = "Lead";
        } else if (name.toLowerCase().contains("junior")) {
            level = "Junior";
        } else if (name.toLowerCase().contains("trainee")) {
            level = "Trainee";
        } else {
            if (description.toLowerCase().contains("senior")) {
                level = "Senior";
            } else if (description.toLowerCase().contains("lead")) {
                level = "Lead";
            } else if (description.toLowerCase().contains("junior")) {
                level = "Junior";
            } else if (description.toLowerCase().contains("trainee")) {
                level = "Trainee";
            } else {
                level = "Middle";
            }
        }
        return levelRepository.existsByName(level) ?
                levelRepository.findByName(level).get() :
                levelRepository.save(Title.builder().name(level).build());
    }

    private LocalDateTime parseDate(String date) {
//        Pattern pattern = Pattern.compile("([0-3][0-9])\\s*(\\w+)\\s*(2\\d+)");
//        Matcher matcher = pattern.matcher(date);
//
//        String day;
//        String month;
//        String year;
//        if (matcher.matches()) {
//            day = matcher.group(1);
//            month = matcher.group(2);
//            year = matcher.group(3);
//            return LocalDateTime.of(Integer.parseInt(year), parseMonth(month), Integer.parseInt(day), 0, 0);
//        }
        String[] dateParts = date.split(" ");
        return LocalDateTime.of(Integer.parseInt(dateParts[2]), months.get(dateParts[1].trim()), Integer.parseInt(dateParts[0]), 0, 0);
    }

    private int parseMonth(String month) {
        if (month.contains("ciч")) {
            return 1;
        } else if (month.contains("лют")) {
            return 2;
        } else if (month.contains("бер")) {
            return 3;
        } else if (month.contains("квіт")) {
            return 4;
        } else if (month.contains("трав")) {
            return 5;
        } else if (month.contains("черв")) {
            return 6;
        } else if (month.contains("лип")) {
            return 7;
        } else if (month.contains("серп")) {
            return 8;
        } else if (month.contains("верес")) {
            return 9;
        } else if (month.contains("жовт")) {
            return 10;
        } else if (month.contains("лист")) {
            return 11;
        } else {
            return 12;
        }
    }

    @Transactional
    public Category saveCategory(String name) {
        return categoryRepository.save(Category.builder().name(name).build());
    }

    @Transactional
    public Company saveCompany(String name) {
        return companyRepository.save(Company.builder().name(name).build());
    }
}
