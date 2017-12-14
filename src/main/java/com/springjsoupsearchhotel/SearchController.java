package com.springjsoupsearchhotel;

import java.io.IOException;
import java.util.HashSet;

import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

	@RequestMapping(value = "/searchhotel", method = RequestMethod.GET)
	public String searchHotel(Model model) throws IOException {
		// model.addAttribute("search",search);
		return "search";

	}

	@RequestMapping(value = "/hotels", method = RequestMethod.GET)
	// @ResponseBody
	public ModelAndView printPageHotel(@RequestParam("url") String url) throws IOException {

		ModelAndView mav = new ModelAndView("hotellist");
		// String url="https://www.etstur.com/Antalya-Sehirici-Otelleri";
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		String hotelCategory = "";

		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hotels")));

		Document doc = Jsoup.parse(driver.getPageSource());
		Elements hotels = doc.select("#hotels div a");

		Set<Hotel> hotelList = new HashSet<Hotel>();
		Hotel hotelObj = new Hotel();
		try {

			Elements nameOfCategory = doc.getElementsByClass("loc");
			hotelCategory = nameOfCategory.text();

			for (Element hotel : hotels) {

				hotelObj = new Hotel();
				// hotelObj.setId(i);
				// System.out.println(hotel.getAllElements());
				Elements nameOfHotel = hotel.getElementsByClass("title");
				hotelObj.setHotelName(nameOfHotel.text());
				System.out.println("Hotel ismi : " + nameOfHotel.text());

				Elements dest = hotel.getElementsByClass("destination");
				hotelObj.setHotelLocation(dest.text());
				System.out.println("Hotel yeri : " + dest.text());

				Elements score = hotel.getElementsByClass("val");
				hotelObj.setHotelScore(score.text());
				System.out.println("Hotel puanı : " + score.text());

				Element figureLink = hotel.select("img").first();
				String hotelPicUrl = figureLink.absUrl("src");
				hotelObj.setHotelImageUrl(hotelPicUrl);
				System.out.println("Hotel resmi : " + hotelPicUrl);

				hotelList.add(hotelObj);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		mav.addObject("hotels", hotelList);
		mav.addObject("hotelCategory", hotelCategory);
		return mav;
		// model.addAttribute("hotels", hotelList);
		// return "hotellist";

	}

}
