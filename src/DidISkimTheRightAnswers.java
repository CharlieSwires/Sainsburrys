import static org.junit.Assert.*;

import java.lang.reflect.Type;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class DidISkimTheRightAnswers {

	String resultUnderTest;
	ScraperAndJsonGen sajg;
	ScraperAndJsonGen.Results results;
	@Before
	public void setUp() throws Exception {
		sajg = new ScraperAndJsonGen();
		resultUnderTest = sajg.performScrape();
		
	}

	@Test
	public void test() {
		Gson g = new Gson();
		Assert.assertTrue(resultUnderTest != null);
		results = g.fromJson(resultUnderTest, ScraperAndJsonGen.Results.class);
		// Correct prices
		Assert.assertTrue("Sainsbury\'s Strawberries 400g"+results.getItems().get(0).getUnitPrice(), 						Math.round(results.getItems().get(0).getUnitPrice()*100) == 175);
		Assert.assertTrue("Sainsbury\'s Blueberries 200g"+results.getItems().get(1).getUnitPrice(), 						Math.round(results.getItems().get(1).getUnitPrice()*100) == 175);
		Assert.assertTrue("Sainsbury\'s Raspberries 225g"+results.getItems().get(2).getUnitPrice(), 						Math.round(results.getItems().get(2).getUnitPrice()*100) == 175);
		Assert.assertTrue("Sainsbury\'s Blackberries 150g"+results.getItems().get(3).getUnitPrice(), 						Math.round(results.getItems().get(3).getUnitPrice()*100) == 150);
		Assert.assertTrue("Sainsbury\'s Blueberries 400g"+results.getItems().get(4).getUnitPrice(), 						Math.round(results.getItems().get(4).getUnitPrice()*100) == 325);
		Assert.assertTrue("Sainsbury\'s Blueberries SO Organic 150g"+results.getItems().get(5).getUnitPrice(), 				Math.round(results.getItems().get(5).getUnitPrice()*100) == 200);
		Assert.assertTrue("Sainsbury\'s Raspberries, Taste the Difference 150g"+results.getItems().get(6).getUnitPrice(), 	Math.round(results.getItems().get(6).getUnitPrice()*100) == 250);
		Assert.assertTrue("Sainsbury\'s Cherries 400g"+results.getItems().get(7).getUnitPrice(), 							Math.round(results.getItems().get(7).getUnitPrice()*100) == 250);
		Assert.assertTrue("Sainsbury\'s Blackberries, Tangy 150g"+results.getItems().get(8).getUnitPrice(), 				Math.round(results.getItems().get(8).getUnitPrice()*100) == 150);
		Assert.assertTrue("Sainsbury\'s Strawberries, Taste the Difference 300g"+results.getItems().get(9).getUnitPrice(), 	Math.round(results.getItems().get(9).getUnitPrice()*100) == 250);
		Assert.assertTrue("Sainsbury\'s Cherry Punnet 200g"+results.getItems().get(10).getUnitPrice(), 						Math.round(results.getItems().get(10).getUnitPrice()*100) == 150);
		Assert.assertTrue("Sainsbury\'s Mixed Berries 300g"+results.getItems().get(11).getTitle()+results.getItems().get(11).getUnitPrice(), 						Math.round(results.getItems().get(11).getUnitPrice()*100) == 350);
		Assert.assertTrue("Sainsbury\'s Mixed Berry Twin Pack 200g"+results.getItems().get(12).getTitle()+results.getItems().get(12).getUnitPrice(), 				Math.round(results.getItems().get(12).getUnitPrice()*100) == 275);
		Assert.assertTrue("Sainsbury\'s Redcurrants 150g"+results.getItems().get(13).getUnitPrice(), 						Math.round(results.getItems().get(13).getUnitPrice()*100) == 250);
		Assert.assertTrue("Sainsbury\'s Cherry Punnet, Taste the Difference 200g"+results.getItems().get(14).getUnitPrice(),Math.round(results.getItems().get(14).getUnitPrice()*100) == 250);
		Assert.assertTrue("Sainsbury\'s Blackcurrants 150g"+results.getItems().get(15).getUnitPrice(), 						Math.round(results.getItems().get(15).getUnitPrice()*100) == 175);
		Assert.assertTrue("Sainsbury\'s British Cherry & Strawberry Pack 600g"+results.getItems().get(16).getUnitPrice(), 	Math.round(results.getItems().get(16).getUnitPrice()*100) == 400);
		//Correct order
		Assert.assertTrue("Sainsbury\'s Strawberries 400g"+"["+results.getItems().get(0).getTitle()+"]", results.getItems().get(0).getTitle().replaceAll("Sainsbury\'s","").contains("Sainsbury\'s Strawberries 400g".replaceAll("Sainsbury\'s","")));
		Assert.assertTrue("Sainsbury\'s Blueberries 200g"+"["+results.getItems().get(1).getTitle()+"]", results.getItems().get(1).getTitle().replaceAll("Sainsbury\'s","").contains("Sainsbury\'s Blueberries 200g".replaceAll("Sainsbury\'s","")));
		Assert.assertTrue("Sainsbury\'s Raspberries 225g"+"["+results.getItems().get(2).getTitle()+"]", results.getItems().get(2).getTitle().replaceAll("Sainsbury\'s","").contains("Sainsbury\'s Raspberries 225g".replaceAll("Sainsbury\'s","")));
		Assert.assertTrue("Sainsbury\'s Blackberries, Sweet 150g"+"["+results.getItems().get(3).getTitle()+"]", results.getItems().get(3).getTitle().replaceAll("Sainsbury\'s","").contains("Sainsbury\'s Blackberries, Sweet 150g".replaceAll("Sainsbury\'s","")));
		Assert.assertTrue("Sainsbury\'s Blueberries 400g"+"["+results.getItems().get(4).getTitle()+"]", results.getItems().get(4).getTitle().replaceAll("Sainsbury\'s","").contains("Sainsbury\'s Blueberries 400g".replaceAll("Sainsbury\'s","")));
		Assert.assertTrue("Sainsbury\'s Blueberries, SO Organic 150g"+"["+results.getItems().get(5).getTitle()+"]", results.getItems().get(5).getTitle().replaceAll("Sainsbury\'s","").contains("Sainsbury\'s Blueberries, SO Organic 150g".replaceAll("Sainsbury\'s","")));
		Assert.assertTrue("Sainsbury\'s Raspberries, Taste the Difference 150g"+"["+results.getItems().get(6).getTitle()+"]", results.getItems().get(6).getTitle().replaceAll("Sainsbury\'s","").contains("Sainsbury\'s Raspberries, Taste the Difference 150g".replaceAll("Sainsbury\'s","")));
		Assert.assertTrue("Sainsbury\'s Cherries 400g"+"["+results.getItems().get(7).getTitle()+"]", results.getItems().get(7).getTitle().replaceAll("Sainsbury\'s","").contains("Sainsbury\'s Cherries 400g".replaceAll("Sainsbury\'s","")));
		Assert.assertTrue("Sainsbury\'s Blackberries, Tangy 150g"+"["+results.getItems().get(8).getTitle()+"]", results.getItems().get(8).getTitle().replaceAll("Sainsbury\'s","").contains("Sainsbury\'s Blackberries, Tangy 150g".replaceAll("Sainsbury\'s","")));
		Assert.assertTrue("Sainsbury\'s Strawberries, Taste the Difference 300g"+"["+results.getItems().get(9).getTitle()+"]", results.getItems().get(9).getTitle().replaceAll("Sainsbury\'s","").contains("Sainsbury\'s Strawberries, Taste the Difference 300g".replaceAll("Sainsbury\'s","")));
		Assert.assertTrue("Sainsbury\'s Cherry Punnet 200g"+"["+results.getItems().get(10).getTitle()+"]", results.getItems().get(10).getTitle().replaceAll("Sainsbury\'s","").contains("Sainsbury\'s Cherry Punnet 200g".replaceAll("Sainsbury\'s","")));
		Assert.assertTrue("Sainsbury\'s Mixed Berries 300g"+"["+results.getItems().get(11).getTitle()+"]", results.getItems().get(11).getTitle().replaceAll("Sainsbury\'s","").contains("Sainsbury\'s Mixed Berries 300g".replaceAll("Sainsbury\'s","")));
		Assert.assertTrue("Sainsbury\'s Mixed Berry Twin Pack 200g"+"["+results.getItems().get(12).getTitle()+"]", results.getItems().get(12).getTitle().replaceAll("Sainsbury\'s","").contains("Sainsbury\'s Mixed Berry Twin Pack 200g".replaceAll("Sainsbury\'s","")));
		Assert.assertTrue("Sainsbury\'s Redcurrants 150g"+"["+results.getItems().get(13).getTitle()+"]", results.getItems().get(13).getTitle().replaceAll("Sainsbury\'s","").contains("Sainsbury\'s Redcurrants 150g".replaceAll("Sainsbury\'s","")));
		Assert.assertTrue("Sainsbury\'s Cherry Punnet, Taste the Difference 200g"+"["+results.getItems().get(14).getTitle()+"]", results.getItems().get(14).getTitle().replaceAll("Sainsbury\'s","").contains("Sainsbury\'s Cherry Punnet, Taste the Difference 200g".replaceAll("Sainsbury\'s","")));
		Assert.assertTrue("Sainsbury\'s Blackcurrants 150g"+"["+results.getItems().get(15).getTitle()+"]", results.getItems().get(15).getTitle().replaceAll("Sainsbury\'s","").contains("Sainsbury\'s Blackcurrants 150g".replaceAll("Sainsbury\'s","")));
		Assert.assertTrue("Sainsbury\'s British Cherry & Strawberry Pack 600g"+"["+results.getItems().get(16).getTitle()+"]", results.getItems().get(16).getTitle().replaceAll("Sainsbury\'s","").contains("Sainsbury\'s British Cherry & Strawberry Pack 600g".replaceAll("Sainsbury\'s","")));

	}

}
