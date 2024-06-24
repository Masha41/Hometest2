package components.popups;

import cities.ICityData;
import common.AbsCommon;
import data.english.EnglishLevelData;
import data.fielddata.InputFieldData;
import data.gender.GenderData;
import data.workformat.WorkFormatData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PersonalDataPage extends AbsCommon {

    public String english;
    public String selectCountry;
    public String city;
    public PersonalDataPage(WebDriver driver)
    {
        super(driver);
    }

    public void clearFieldsData(InputFieldData... inputFieldData) {
        for (InputFieldData fieldData : inputFieldData) {
            driver.findElement(By.cssSelector(String.format("input[name='%s']", fieldData.getName()))).clear();
        }
    }

    public void addNewDataFields(InputFieldData inputFieldData, String data) {
        driver.findElement(By.cssSelector(String.format("input[name='%s']", inputFieldData.getName())))
                .sendKeys(data);
    }

    public void selectCountry(ICityData cityData) {
        WebElement russiaSelectElement = driver.findElement(By.cssSelector("[data-slave-selector='.js-lk-cv-dependent-slave-city']"));
        russiaSelectElement.click();

        selectCountry = cityData.getCountriesData().getNamecountry();

        WebElement countryListContainer = russiaSelectElement
                .findElement(By.xpath(".//*[contains(@class, 'js-custom-select-presentation')]"));
        waitTools.waitForCondition(ExpectedConditions.not(ExpectedConditions
                .attributeContains(countryListContainer, "class", "hide")));

        driver.findElement(By.cssSelector(String.format("[title*='%s']",
                cityData.getCountriesData().getNamecountry()))).click();

        waitTools.waitForCondition(ExpectedConditions
                .attributeContains(countryListContainer, "class", "hide"));
    }

    public void selectCity(ICityData cityData) {
        WebElement citySelectElement = driver.findElement(By.xpath("//*[contains(@class, 'js-lk-cv-dependent-slave-city')]"));
        citySelectElement.click();

        city = cityData.getName();
        WebElement cityListContainer = citySelectElement
                .findElement(By.xpath(".//*[contains(@class, 'js-custom-select-options-container')]"));
        waitTools.waitForCondition(ExpectedConditions.not(ExpectedConditions
                .attributeContains(cityListContainer, "class", "hide")));

        driver.findElement(By.cssSelector(String.format("[title*='%s']", cityData.getName()))).click();

        waitTools.waitForCondition(ExpectedConditions.attributeContains(cityListContainer, "class", "hide"));

    }

    public void selectEnglishLevel(EnglishLevelData englishLevel) {
        WebElement englishLevelSelectElement = driver.findElement(By
                .xpath("//input[@name='english_level']/ancestor:: div[contains(@class, 'js-lk-cv-custom-select')]"));
        englishLevelSelectElement.click();

        english = englishLevel.getNameEnglishLevel();
        WebElement levelListContainer = englishLevelSelectElement
                .findElement(By.xpath(".//*[contains(@class, 'js-custom-select-options-container')]"));
        waitTools.waitForCondition(ExpectedConditions.not(ExpectedConditions
                .attributeContains(levelListContainer, "class", "hide")));

        driver.findElement(By.cssSelector(String.format("[title*='%s']", englishLevel.getNameEnglishLevel()))).click();

    }
    public void selectGender(GenderData genderData) {
        driver.findElement(By.id("id_gender")).click();
        driver.findElement(By.cssSelector(String.format("option[value='f']", genderData.getName()))).click();

    }

    public void switchRelocate(boolean isSelected) {
        String relocate = isSelected ? "Да" : "Нет";
        driver.findElement(By.xpath(String.format("//span[@class=\"radio__label\" and text()=\"%s\"]", relocate))).click();
    }

    public void switchWorkFormat(boolean isSelected, WorkFormatData... workGrafs) {
        for (WorkFormatData workGraf : workGrafs) {
            String selector = "input[title='%s']";
            WebElement checkBoxSelected = driver.findElement(By.cssSelector(String.format(selector, workGraf.getName())));
            //boolean isSelected = checkBoxSelected.isSelected();
            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", inputSelect);

            waitTools.waitForCondition(ExpectedConditions.elementToBeClickable(By.cssSelector(String.format(selector, workGraf.getName()))));

            if (isSelected == false) {
                checkBoxSelected.click();



            }
        }
    }


    public void selectContactsOne(String contactType, String contactValue) {
        driver.findElement(By.xpath("//button[@type='button' and text()='Добавить']")).click();

        WebElement commMethodSelectElement = driver.findElement(By.xpath("//span[@class=\"placeholder\" and text()=\"Способ связи\"]"));
        waitTools.waitForCondition(ExpectedConditions.elementToBeClickable(commMethodSelectElement));commMethodSelectElement.click();
        driver.findElement(By.xpath(String.format("//div[@class='lk-cv-block__select-options lk-cv-block__select-options_left" + " js-custom-select-options-container']/div/button[@data-value='%s']", contactType))).click();
        driver.findElement(By.xpath("//*[@id=\"id_contact-1-value\"]")).sendKeys(contactValue);
        driver.findElement(By.cssSelector("button.lk-cv-block__action.lk-cv-block__action_md-no-spacing.js-formset-add" + ".js-lk-cv-custom-select-add")).click();

    }
    public void selectContactsTwo(String contactType, String contactValue) {
        driver.findElement(By.xpath("//span[@class='placeholder']")).click();
        driver.findElement(By.xpath(String.format("//div[@class='lk-cv-block__select-options lk-cv-block__select-options_left" +
                " js-custom-select-options-container']/div/button[@data-value='%s']", contactType))).click();
        driver.findElement(By.xpath("//*[@id=\"id_contact-1-value\"]")).sendKeys(contactValue);
    }


    public void clickSavePersonalData() {
        driver.findElement(By.cssSelector("button[name='continue']")).click();
    }

   public void assertFieldsData(InputFieldData inputFieldData) {
        Assertions
                .assertTrue(!driver.findElement
                                (By.cssSelector(String.format("input[name='%s']", inputFieldData.getName())))
                        .getAttribute("value").isEmpty());


    }

    public void checkFieldsDataIsNotEmpty() {
        Assertions.assertTrue(!driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)")).getText().isEmpty());
        Assertions.assertTrue(!driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)")).getText().isEmpty());
        Assertions.assertTrue(!driver.findElement(By.xpath("//input[@name='english_level']/ancestor:: div[contains(@class, 'js-lk-cv-custom-select')]")).getText().isEmpty());
        Assertions.assertTrue(driver.findElement(By.xpath("//input[@id='id_ready_to_relocate_1']")).isSelected());
        Assertions.assertTrue(driver.findElement(By.cssSelector("input[title='Полный день']")).isSelected());
        Assertions.assertTrue(!driver.findElement(By.id("id_contact-0-value")).getAttribute("value").isEmpty());
        Assertions.assertTrue(!driver.findElement(By.id("id_contact-1-value")).getAttribute("value").isEmpty());
        Assertions.assertTrue(!driver.findElement(By.id("id_gender")).getAttribute("value").isEmpty());
    }

        public void assertFieldsNameData(String inputDNames, String fackerName) {
            String fakerValue = driver.findElement
                    (By.cssSelector(String.format("input[name='%s']", inputDNames))).getAttribute("value");

            Assertions.assertEquals(fakerValue, fackerName);
        }


        /*public void assertFieldsData() {
            Assertions.assertEquals(selectCountry, driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > " +
                    "label:nth-child(1) > div:nth-child(2)")).getText());

            Assertions.assertEquals(city, driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city >" +
                    " label:nth-child(1) > div:nth-child(2)")).getText());

            Assertions.assertEquals(english, driver.findElement(By.xpath("//input[@name='english_level']/" +
                    "ancestor:: div[contains(@class, 'js-lk-cv-custom-select')]")).getText());

            Assertions.assertTrue(driver.findElement(By.xpath("//input[@id='id_ready_to_relocate_1']")).isSelected());

            Assertions.assertTrue(driver.findElement(By.cssSelector("input[title='Удаленно']")).isSelected());**/


    }


