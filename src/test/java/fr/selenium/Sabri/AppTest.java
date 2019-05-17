package fr.selenium.Sabri;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.TestCase;

public class AppTest extends TestCase {
 
	private static final String URL = "http://www.fr.jal.co.jp/frl/en/";
	private static final String PATH_CHROME_DRIVER = "C:\\chromedriver.exe";
	public static WebDriver driver;
	
	
	public static void firstTest() {
		System.setProperty("webdriver.chrome.driver", PATH_CHROME_DRIVER);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get(URL);
		
		pause(2);
		
		driver.findElement(By.className("JS_ciBox_submit")).click();
		//driver.findElements(By.cssSelector("#JS_ciBox_contents img")).get(1).click();

		/* Selectionner un billet Nice-Tokyo du 1er Aout au 20 Aout pour 2 adultes */
		Select s = new Select(driver.findElement(By.id("mdlDepLocation1")));
		s.selectByValue("NCE");
		
		Select s1 = new Select(driver.findElement(By.id("mdlArrLocation1")));
		s1.selectByValue("NRT");
		
		Select s2 = new Select(driver.findElement(By.id("DEPARTURE_DATE_1_MONTH")));
		s2.selectByValue("8");
		
		Select s3 = new Select(driver.findElement(By.id("DEPARTURE_DATE_1_DAY")));
		s3.selectByValue("1");
		
		Select s4 = new Select(driver.findElement(By.id("DEPARTURE_DATE_2_MONTH")));
		s4.selectByValue("8");
		
		Select s5 = new Select(driver.findElement(By.id("DEPARTURE_DATE_2_DAY")));
		s5.selectByValue("20");
		
		Select s6 = new Select(driver.findElement(By.id("CFF_1")));
		s6.selectByValue("1WE");
		
		Select s7 = new Select(driver.findElement(By.id("mdlNbAdt")));
		s7.selectByValue("2");
		
		// Validation
		pause(1);
		driver.findElement(By.id("mdlFormSubmit")).click();
		
		
		//ville départ
		String villeDepart = driver.findElement(By.id("bound-departure-0")).getText();
		System.out.println("Ville de départ : "+villeDepart);
				
		//ville arrivée
		String villeArrivee = driver.findElement(By.id("bound-arrival-0")).getText();
		String[] villeArriveChopped = villeArrivee.split(" ");
		villeArrivee = villeArriveChopped[0];
		System.out.println("Ville d'arrivée : "+villeArrivee);
				
		//Jour depart
		String jourDepart = driver.findElement(By.id("departureDateTime-0")).getText();
		System.out.println("Jour : "+jourDepart);
		
		
		WebElement element = driver.findElement((By.cssSelector("#flightNumber-0-0 .flight-identifier")));
		
		try {
			System.out.println(LocalDateTime.now());
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e){
			System.out.println("Element pas trouvé");
		}
		
		
		if(element.isDisplayed()) {
			System.out.println("flight number is display");
		} else {
			System.out.println("flight number is not display");
			driver.findElements(By.className("table-cell")).get(0).click();
		}
		
		try {
			System.out.println(LocalDateTime.now());
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.visibilityOf(element));
			System.out.println("Element trouvé");
		} catch (TimeoutException e){
			System.out.println("Element pas trouvé");
		}
		
		//numero 1er vol
		String flightNumber1 = driver.findElements(By.className("flight-identifier")).get(18).getText();
		System.out.println(flightNumber1);
		/*
		//horaireDepart
		String flightHour = driver.findElements(By.className("screenreader-only")).get(5).getText();
		System.out.println("Heure de départ : "+flightHour);
		*/
		//numero 2eme vol
		String flightNumber2 = driver.findElements(By.className("flight-identifier")).get(19).getText();
		System.out.println(flightNumber2);
			/*	
		//horaireDepart
		String flightHour2 = driver.findElements(By.className("screenreader-only sr-only")).get(3).getText();
		System.out.println("Heure de départ : "+flightHour2);
		*/
			
		//Tarif
		String price = driver.findElement(By.id("sidebarPriceSummaryTotalPrice")).getText();
		System.out.println("tarif : "+price+'\n'+"******");
		
		
		// Validation des vols
		pause(1);
		driver.findElement(By.id("continueButton")).click();
		
		pause(1);
		
		//Page passagers
		
		// Remplissage des champs Passager 1
		Select s8 = new Select(driver.findElement(By.id("0-title")));
		s8.selectByValue("MR");
	
		
		String firstNameP1 = "sabri";
		String lastNameP1 = "taleb";
		driver.findElement(By.id("0-last-name")).sendKeys(lastNameP1);
		driver.findElement(By.id("0-first-name")).sendKeys(firstNameP1);
		
		Select s9 = new Select(driver.findElement(By.id("0-gender")));
		s9.selectByValue("string:MALE");
		
		Select s10 = new Select(driver.findElement(By.id("0-birth-date-day")));
		s10.selectByValue("string:21");
		
		Select s11 = new Select(driver.findElement(By.id("0-birth-date-month")));
		s11.selectByValue("string:07");
		
		Select s12 = new Select(driver.findElement(By.id("0-birth-date-year")));
		s12.selectByValue("string:1987");
		
		Select s13 = new Select(driver.findElement(By.id("0-nationality")));
		s13.selectByValue("string:FR");
		
		
		// Remplissage des champs Passager 2
		Select s14 = new Select(driver.findElement(By.id("1-title")));
		s14.selectByValue("MRS");
	
		String firstNameP2 = "fefe";
		String lastNameP2 = "taleb";
		driver.findElement(By.id("1-last-name")).sendKeys(lastNameP2);
		driver.findElement(By.id("1-first-name")).sendKeys(firstNameP2);
		
		Select s15 = new Select(driver.findElement(By.id("1-gender")));
		s15.selectByValue("string:FEMALE");
		
		Select s16 = new Select(driver.findElement(By.id("1-birth-date-day")));
		s16.selectByValue("string:10");
		
		Select s17 = new Select(driver.findElement(By.id("1-birth-date-month")));
		s17.selectByValue("string:02");
		
		Select s18 = new Select(driver.findElement(By.id("1-birth-date-year")));
		s18.selectByValue("string:1988");
		
		Select s19 = new Select(driver.findElement(By.id("1-nationality")));
		s19.selectByValue("string:FR");
		
	
		// Remplissage des champs phone et email
		Select s20 = new Select(driver.findElement(By.id("phone1-phone-country-0")));
		s20.selectByValue("FRA");
		
		String email = "sabri_taleb@me.com";
		driver.findElement(By.id("phone1-phone-number-0")).sendKeys("635432799");
		driver.findElement(By.id("email-guest-address")).sendKeys(email);
		driver.findElement(By.id("email-confirm-new")).sendKeys(email);
		
		pause(2);
		driver.findElement(By.id("continueButton")).click();
		driver.findElement(By.id("continueButton-PCOF")).click();
		
		
		/*Page selection siege
		driver.findElement(By.id("segment-0-0-pax-0-seat")).sendKeys("");
		driver.findElement(By.id("segment-0-0-pax-1-seat")).sendKeys("");
		driver.findElement(By.id("requestButton-0-0")).click();
		
		driver.findElement(By.id("segment-0-1-pax-0-seat")).sendKeys("");
		driver.findElement(By.id("segment-0-1-pax-1-seat")).sendKeys("");
		driver.findElement(By.id("requestButton-0-1")).click();
		
		driver.findElement(By.id("segment-1-0-pax-0-seat")).sendKeys("");
		driver.findElement(By.id("segment-1-0-pax-1-seat")).sendKeys("");
		driver.findElement(By.id("requestButton-1-0")).click();
		
		driver.findElement(By.id("segment-1-1-pax-0-seat")).sendKeys("");
		driver.findElement(By.id("segment-1-1-pax-1-seat")).sendKeys("");
		driver.findElement(By.id("requestButton-1-1")).click();*/
		
		
		driver.findElement(By.id("seatContinue")).click();
		
		
		// Page de résumé du vol
		
		//ville départ
		String villeDepartExp = driver.findElement(By.id("originLocation-0")).getText();
		System.out.println("Ville de départ : "+villeDepartExp);
		
		//ville arrivée
		String villeArriveeExp = driver.findElement(By.id("destinationLocation-0")).getText();
		System.out.println("Ville d'arrivée : "+villeArriveeExp);
		
		//Jour depart
		String jourDepartExp = driver.findElement(By.id("originDate-0")).getText();
		System.out.println("Jour : "+jourDepartExp);
		
		//numero 1er vol
		String flightNumber1Exp = driver.findElement(By.id("flightNumber-0-0")).getText();
		String[] flightNumber1Chopped = flightNumber1Exp.split("\n");
		flightNumber1Exp = flightNumber1Chopped[1];
		System.out.println(flightNumber1Exp);
		
		//horaireDepart
		String flightHourExp = driver.findElement(By.id("segmentOriginDate-0-0")).getText();
		System.out.println("Heure de départ : "+flightHourExp);
		
		//numero 2eme vol
		String flightNumber2Exp = driver.findElement(By.id("flightNumber-0-1")).getText();
		String[] flightNumber2Chopped = flightNumber2Exp.split("\n");
		flightNumber2Exp = flightNumber2Chopped[1];
		System.out.println(flightNumber2Exp);
				
		//horaireDepart
		String flightHour2Exp = driver.findElement(By.id("segmentOriginDate-0-1")).getText();
		System.out.println("Heure de départ : "+flightHour2Exp);
		
		//Tarif
		String priceExp = driver.findElement(By.id("sidebarPriceSummaryTotalPrice")).getText();
		System.out.println("tarif : "+priceExp);
		
		//Prenom Passager 1
		String lastNameP1Exp = driver.findElement(By.id("travellerName-0")).getText();
		String[] lastNameP1ExpChopped = lastNameP1Exp.split(" ");
		lastNameP1Exp = lastNameP1ExpChopped[1].toLowerCase();
		System.out.println("Prénom P1 : "+lastNameP1Exp);
		
		//Nom Passager 1
		String firstNameP1Exp = driver.findElement(By.id("travellerName-0")).getText();
		String[] firstNameP1ExpChopped = firstNameP1Exp.split(" ");
		firstNameP1Exp = firstNameP1ExpChopped[2].toLowerCase();
		System.out.println("Nom P1 : "+firstNameP1Exp);;
				
		//Prenom Passager 2
		String lastNameP2Exp = driver.findElement(By.id("travellerName-1")).getText();
		String[] lastNameP2ExpChopped = lastNameP2Exp.split(" ");
		lastNameP2Exp = lastNameP2ExpChopped[1].toLowerCase();
		System.out.println("Prénom P2 : "+lastNameP2Exp);
				
		//nom Passager 2
		String firstNameP2Exp = driver.findElement(By.id("travellerName-1")).getText();
		String[] firstNameP2ExpChopped = firstNameP2Exp.split(" ");
		firstNameP2Exp = firstNameP2ExpChopped[2].toLowerCase();
		System.out.println("Nom P2 : "+firstNameP2Exp);;
		
		//email
		String emailExp = driver.findElement(By.id("contactSummaryEmailAddress")).getText();
		System.out.println("tarif : "+emailExp);
		
		
		//Les egalites
		assertEquals(villeDepart, villeDepartExp);
		assertEquals(villeArrivee, villeArriveeExp);
		assertEquals(jourDepart, jourDepartExp);
		assertEquals(flightNumber1, flightNumber1Exp);
		assertEquals(flightNumber2, flightNumber2Exp);
		assertEquals(firstNameP1, firstNameP1Exp);
		assertEquals(lastNameP1, lastNameP1Exp);
		assertEquals(firstNameP2, firstNameP2Exp);
		assertEquals(lastNameP2, lastNameP2Exp);
		assertEquals(email, emailExp);
		assertEquals(price, priceExp);
		
	
		driver.findElement(By.id("purchaseButton")).click();
		
		//Page Paiement
		pause(1);
		
		driver.findElements(By.className("table-cell")).get(2).click();
		
		driver.findElement(By.id("CCnb")).sendKeys("12365478963258");
		
		Select s21 = new Select(driver.findElement(By.id("expiration-month-id")));
		s21.selectByValue("number:4");
		
		Select s22 = new Select(driver.findElement(By.id("expiration-year-id")));
		s22.selectByValue("number:2020");
		
		driver.findElement(By.id("sec-code")).sendKeys("635");
		
		pause(2);
		driver.findElement(By.id("continueButton")).click();
		
	}
	
	private static void pause(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}	
	}
	
	protected void waitForThePage() {
	}
	
}
