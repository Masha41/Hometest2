package persdata;

import cities.ICityData;
import cities.RussCityData;
import com.github.javafaker.Faker;
import components.popups.AuthPooups;
import components.popups.Header;
import components.popups.PersonalDataPage;
import data.english.EnglishLevelData;
import data.fielddata.InputFieldData;
import data.gender.GenderData;
import data.workformat.WorkFormatData;
import factory.impl.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;



public class PersonalDataPageTest {



    private WebDriver driver = null;

    protected Faker faker = new Faker();
    String fakerName;
    String fakerNamelatin;
    String fakerLname;
    String fakerLnamelatin;


    @BeforeEach

    public void init() {
        driver = new DriverFactory().create();

    }

    @AfterEach

    public void stopDriver() {
        if (driver != null) {
            driver.quit();
        }

    }

    @Test
    @Order(1)

    public void savePersonalData() {

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        Header header = new Header(driver);
        header.waitMarkerTelNumber();
        header.waitSignInBtnIsPresent();
        header.waitSignInBtnToBeClicable();

        AuthPooups authPopups = new AuthPooups(driver);
        authPopups.popupShouldNotBeVisible();


        header.clickSignInButton();
        authPopups.popupShouldBeVisible();

        authPopups.enterDataEmail();
        authPopups.enterDataPassword();
        authPopups.clickSignInBtnPopups();

        header.checkLogoUser();
        header.clickPersonalArea();


        PersonalDataPage personalData = new PersonalDataPage(driver);

        personalData.clearFieldsData(InputFieldData.FNAME);
        personalData.clearFieldsData(InputFieldData.FNAMELATIN);
        personalData.clearFieldsData(InputFieldData.LNAME);
        personalData.clearFieldsData(InputFieldData.LNAMELATIN);
        personalData.clearFieldsData(InputFieldData.BLOGNAME);
        personalData.clearFieldsData(InputFieldData.DATEOFBRTH);


        personalData.addNewDataFields(InputFieldData.FNAME, faker.name().firstName());
        personalData.addNewDataFields(InputFieldData.FNAMELATIN, faker.name().lastName());
        personalData.addNewDataFields(InputFieldData.LNAME, faker.name().firstName());
        personalData.addNewDataFields(InputFieldData.LNAMELATIN, faker.name().firstName());
        personalData.addNewDataFields(InputFieldData.BLOGNAME, faker.name().lastName());
        personalData.addNewDataFields(InputFieldData.DATEOFBRTH,
                faker.date().birthday().toInstant().atZone(ZoneId.
                        systemDefault()).toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));


        ICityData[] cityData = RussCityData.values();
        ICityData city = faker.options().nextElement(cityData);

        personalData.selectCountry(city);
        personalData.selectCity(city);


        personalData.selectEnglishLevel(EnglishLevelData.BEGINNER);


        personalData.switchRelocate(true);


        personalData.switchWorkFormat(true, WorkFormatData.FULL);


        personalData.selectContactsOne("telegram", "3333");

        personalData.selectContactsTwo("habr", "7777");


        personalData.selectGender(GenderData.FEMALE);


        personalData.addNewDataFields(InputFieldData.COMPANY, faker.company().name());
        personalData.addNewDataFields(InputFieldData.POSITION, faker.company().profession());

        personalData.clickSavePersonalData();
        driver.close();

    }

   @Test
   @Order(2)

    public   void checkPersonalDataTest() {
       new MainPage(driver).open();


       Header header = new Header(driver);
        header.waitMarkerTelNumber();
        header.waitSignInBtnIsPresent();
        header.waitSignInBtnToBeClicable();


        AuthPooups authPopups = new AuthPooups(driver);
        authPopups.popupShouldNotBeVisible();

        header.clickSignInButton();
        authPopups.popupShouldBeVisible();

        authPopups.enterDataEmail();
        authPopups.enterDataPassword();
        authPopups.clickSignInBtnPopups();



        header.checkLogoUser();
        header.clickPersonalArea();

        PersonalDataPage personalData = new PersonalDataPage(driver);
        personalData.assertFieldsData(InputFieldData.FNAME);;
        personalData.assertFieldsData(InputFieldData.FNAMELATIN);
        personalData.assertFieldsData(InputFieldData.LNAME);
        personalData.assertFieldsData(InputFieldData.LNAMELATIN);
        personalData.assertFieldsData(InputFieldData.BLOGNAME);
        personalData.assertFieldsData(InputFieldData.DATEOFBRTH);


        personalData.checkFieldsDataIsNotEmpty();


    }
}
