import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

public class ScraperAndJsonGen {
	//	{
	//		  "results": [
	//		    {
	//		      "title": "Sainsbury's Strawberries 400g",
	//		      "kcal_per_100g": 33,
	//		      "unit_price": 1.75,
	//		      "description": "by Sainsbury's strawberries"
	//		    },
	//		    {
	//		      "title": "Sainsbury's Blueberries 200g",
	//		      "kcal_per_100g": 45,
	//		      "unit_price": 1.75,
	//		      "description": "by Sainsbury's blueberries"
	//		    },
	//		    {
	//		      "title": "Sainsbury's Cherry Punnet 200g",
	//		      "kcal_per_100g": 52,
	//		      "unit_price": 1.5,
	//		      "description": "Cherries"
	//		    }
	//
	//		  ],
	//		  "total": 5.00
	//		}
	public class Item {
		private String title;
		private double kcal_per_100g;
		private double unit_price;
		private String description;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public double getKcalPer100g() {
			return kcal_per_100g;
		}
		public void setKcalPer100g(double kcalPer100g) {
			this.kcal_per_100g = kcalPer100g;
		}
		public double getUnitPrice() {
			return unit_price;
		}
		public void setUnitPrice(double unitPrice) {
			this.unit_price = unitPrice;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	}

	public class Results{
		private List<Item> items;
		private double total;
		public List<Item> getItems() {
			return items;
		}
		public void setItems(List<Item> items) {
			this.items = items;
		}
		public double getTotal() {
			return total;
		}
		public void setTotal(double total) {
			this.total = total;
		}
	}
	public String performScrape() throws IOException {
		Results rslt = new Results();
		List<Item> items = new ArrayList<>();

		Document doc = Jsoup.connect("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html").get();
		Elements gridItems = doc.getElementsByClass( "gridItem");
		double total = 0.0;
		for (Element gridItem : gridItems) {//loop through gridItems where the fruits are
			Item itm = new Item();
			try{
				List<Element> prices = gridItem.getElementsByClass("pricePerUnit"); //find the price
				int index = 0;
				for (Element title: gridItem.getElementsByTag("a")){ //<a href
					Element price = prices.get(index++);
					if (!title.text().contains("Klip")){//Just in case Klip has sneaked in
						setDescriptionAndEnergy(itm,title.attr("href").replaceAll("\\.\\./", ""));//get rid of ../../.. look up child HTML

						itm.setTitle(title.text());//set title from the <a href			

						if (prices.size() != 0){// get price convert to double
							itm.setUnitPrice(Double.parseDouble(price.text().replaceAll("£", "").replaceAll("/unit", "")));					
							total += itm.getUnitPrice(); //tot up
						}
						items.add(itm);//add itm to list
					}
				}

			}catch (Exception e){//catch exceptions which are generated when nothing is found
				//				System.out.println(e.getMessage());
				//			e.printStackTrace();

			}
		}	
		rslt.setItems(items);//top level object set children
		rslt.setTotal(total);//set the total
		Gson g = new Gson();
		String jsonString = g.toJson(rslt);//convert to JSON
		return jsonString;
	}
	private void setDescriptionAndEnergy(Item itm, String attr) {
		try {
			Document doc = Jsoup.connect("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/"+attr).get();

			Elements descriptionTitles = doc.getElementsByTag("h3"); //Description tag
			int i;
			for (i = 0; i <descriptionTitles.size();i++){//find description
				if (descriptionTitles.get(i).text().contains("Description")){
					break;
				}
			}
			itm.setDescription(descriptionTitles.get(i).siblingElements().get(0).text());//first sibling is the Description

			Elements rowTitles = doc.getElementsByTag("th");//find table heading Energy
			for (i = 0; i <rowTitles.size();i++){
				if (rowTitles.get(i).text().contains("Energy")){
					break;
				}
			}
			itm.setKcalPer100g(Double.parseDouble(rowTitles.get(i).siblingElements().get(0).text().replaceAll("kJ","")));//first sibling is the kJ

		}catch (Exception e){//if thing aren't found
			//			System.out.println(e.getMessage());
			//			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		ScraperAndJsonGen sajg = new ScraperAndJsonGen();

		System.out.println(sajg.performScrape());
	}

}
