package seleniumTest;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestSteps {
	private WebDriver driver;
	
	@Given("^Browser is opened$")
	public void open_browser() {
		System.setProperty("webdriver.chrome.driver","C:\\apache-maven-3.5.4\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Given("^User is at \"(.*)\"$")
	public void userAtPage(String url) {
		driver.get(url);
	}
	
	@Given("^search bar is loaded$")
	public void searchBarLoaded() {
		new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@class='srSearchForm']//input[contains(@class,'srSearchInput')]")));
	}

	
	@When("^User navigates to \"(.*)\"$")
	public void visit_website(String url) {
		driver.get(url);
	}
	
	@Then("^Browser navigates to \"(.*)\" webpage$")
	public void checkTitle(final String titleStartsWith) {
		assertTrue(driver.getTitle().toLowerCase().startsWith(titleStartsWith.toLowerCase()));
	}
	
	@When("^User clicks on \"(.*)\" menu tab$")
	public void click_menuItem(String menuItem) {
		WebElement webMenuItem = driver.findElements(By.xpath("//ul[@id = 'menu-main-menu-1']//li[contains(@class,'menu-item') and .//text() ='Careers']")).get(0);
		webMenuItem.click();
	}
	
	@Then("^Browser will navigate to careers section and display list of available vancancies$")
	public void checkCareerSection() {
		assertTrue(driver.findElements(By.xpath("//h1[contains(@class,'heading--border') and contains(text(),'Current vacancies')]")).size()>0);
	}
	
	@When("^user enters \"(.*)\" in the search menu$")
	public void searchVacancies(String search) {
		WebElement searchInput = driver.findElement(By.xpath("//form[@class='srSearchForm']//input[contains(@class,'srSearchInput')]"));
		searchInput.sendKeys(search);
	}
	
	@When("^press enter$")
	public void enterSearch() {
		WebElement searchInput = driver.findElement(By.xpath("//form[@class='srSearchForm']//input[contains(@class,'srSearchInput')]"));
		searchInput.sendKeys(Keys.RETURN);
	}
	
	@When("^user clicks on location filter$")
	public void click_LocationFilter() {
		WebElement item = driver.findElement(By.xpath("//div[@id='facet_location']"));
		item.click();
	}
	
	@Then("^a dropdown list is displayed$")
	public void checkLocationDropDown() {
		assertTrue(driver.findElement(By.xpath("//div[@id='facet_location']//ul[@class='srSearchOptionList']")).isDisplayed());
	}
	
	@When("^User selects \"(.*)\" from the location dropdown$")
	public void select_Location(String location) {
		WebElement item;
		if (location.trim().isEmpty()) {
			item = driver.findElement(By.xpath("//div[@id='facet_location']"));
		}else {
			item = driver.findElement(By.xpath("//div[@id='facet_location']//ul[@class='srSearchOptionList']//span[text() ='"+location+"']"));
		}
		item.click();
	}
	
	@Then("^Then entries with \"(.*)\" and \"(.*)\" are displayed$")
	public void checkEntries(String nameResult, String locationResult) {
		List<WebElement> jobList = driver.findElements(By.xpath("//tr[@class='srJobListJobOdd' or @class='srJobListJobEven']"));
		if (!nameResult.equals("All")) {
			WebElement title;
			for (int i=0; i<jobList.size(); i++) {
				title = jobList.get(i).findElement(By.xpath(".//td[@class='srJobListJobTitle']"));
				assertTrue(title.getText().contains(nameResult));
			}		
		}
		if (!locationResult.equals("All")) {
			WebElement location;
			for (int i=0; i<jobList.size(); i++) {
				location = jobList.get(i).findElement(By.xpath(".//td[@class='srJobListLocation']"));
				assertTrue(location.getText().contains(locationResult));
			}
		}

	}

	@After()
    public void closeBrowser() {
      driver.quit();
    }
}