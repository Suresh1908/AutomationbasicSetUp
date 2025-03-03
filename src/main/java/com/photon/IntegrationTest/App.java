package com.photon.IntegrationTest;

import com.microsoft.playwright.APIRequest.NewContextOptions;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.JSHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		try {
			
			String subject = "Subject : Edit Note";
			
			System.out.println(subject.split(":")[1].trim());
			
			Playwright playwright = Playwright.create();
			Browser browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();
			page.waitForLoadState(LoadState.DOMCONTENTLOADED);
			page.navigate("https://www.tutorialspoint.com/selenium/practice/login.php");
			/*
			 * JSHandle formhandle =
			 * page.evaluateHandle("document.forms[0].elements.email");
			 * page.evaluateHandle("([form, text]=>form.innerHTML=text})",
			 * Arrays.asList(formhandle, "Hello"));
			 */
			String text = page.frames().get(0).locator("//a[@title='back to Selenium Tutorial']").textContent();
			System.out.println("loc text " + text);

			// javascript

//		System.out.println("value - " + page.evalOnSelector("#navMenus>li:nth-of-type(1)>a", "e1=>e1.href"));

			String expression = """
					async()=>{
					const res = await fetch('https://www.tutorialspoint.com/selenium/practice/text-box.php');
					const code = await res.status;
					return code;
					}
						""";

			System.out.println("status code " + page.evaluate(expression));

			Map<String, String> object = new HashMap<>();
			object.put("name", "suresh");

			System.out.println(page.evaluate("([a1,a2])=>a1+a2", Arrays.asList(10, 20)));
			System.out.println(page.evaluate("obj=>obj.name", object));

			System.out.println(page.evaluate("document.title"));
			
			page.evaluate("data=>document.forms[0].elements.password.value=data","password");
			page.evaluate("document.forms[0].elements.email.value='data'");
			
			sleep(10);
           
			/*
			 * Map<String, String> headers = new HashMap<>(); headers.put("Accpet",
			 * "application/json"); NewContextOptions apiContextOptions = new
			 * NewContextOptions(); // https://fake-json-api.mock.beeceptor.com/users
			 * apiContextOptions.setBaseURL("https://fake-json-api.mock.beeceptor.com");
			 * apiContextOptions.setExtraHTTPHeaders(headers);
			 * 
			 * APIRequestContext apicontext =
			 * playwright.request().newContext(apiContextOptions); APIResponse response =
			 * apicontext.get("/users"); System.out.println("response " + response.text());
			 */ /*
				 * page.frames().forEach(frame->{ System.out.println(frame.title()); });
				 */
			System.out.println("page title " + page.title());
///IntegrationTest/src/main/java/com/photon/IntegrationTest/mocks/reload.js

			// page.addInitScript(Paths.get("//IntegrationTest//mocks//reload.js"));

			context.close();
			System.exit(1);
		} catch (Exception e) {
			System.exit(1);
		}

		/*
		 * page.close(); context.close();
		 */
	}

	static void swithToWindow(BrowserContext context, Page page) {
		Page subpage = context.waitForPage(() -> {
			page.click("//button[text()='New Window']");
		});
		System.out.println("sub page title " + subpage.title());
//		String headingText = subpage.locator("(//h1)[1]").textContent();
//		System.out.println("header " + headingText);
		int anchor = subpage.locator("//a").all().size();
		System.out.println("anchor tags " + anchor);
		subpage.close();
	}

	static void sleep(int n) {
		try {
			Thread.sleep(n * 1000);
		} catch (Exception e) {

		}
	}

	static void handleAlert(Page page) {
		page.onDialog((dialog) -> {
			System.out.println("dialog in");
			dialog.accept();
		});
	}
}