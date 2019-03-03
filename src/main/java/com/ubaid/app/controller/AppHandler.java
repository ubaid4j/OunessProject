package com.ubaid.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ubaid.app.model.document.Documents;


//app handler
public class AppHandler
{
	
	
	public AppHandler(Controller controller)
	{
		try
		{
			//getting list of urls
			List<String> urls = getURLList();
			

			//only for one
			for(int i = 0; i < urls.size(); i++)
			{
				controller.getQueue().setIndex(urls.get(i));
				new Scrapper_(controller, urls.get(i));
			}

			System.out.println("Scrapping Completed");
			System.exit(0);
			
			
			
		}
		catch(Exception exp)
		{
			controller.getQueue().setText(exp, controller.getQueue().getErrorIndex());
		}
	}
	
	@SuppressWarnings("unused")
	private String getFileName()
	{
		return generateRandomWords() + ".xlsx";
	}
	
	
	private String generateRandomWords()
	{
	    Random random = new Random();
        char[] word = new char[random.nextInt(16)+5]; 
        for(int j = 0; j < word.length; j++)
        {
            word[j] = (char)('a' + random.nextInt(26));
        }
        
	    return new String(word);
	}

	

	/**
	 * 
	 * @return a list of URLs which are supposed to be retrieved from the websites
	 */
	private List<String> getURLList()
	{
		List<String> list = new ArrayList<>();
		list.add("https://en-saudi.ounass.com/api/women/shoes/boots?sortBy=popularity-asc&facets=0&p=0");
		return list;		
	}
	
	
	
	private class Scrapper_
	{

		Controller controller;
		String url;
		
		public Scrapper_(Controller controller, String url)
		{
			this.controller = controller;
			this.url = url;
			run();
			
		}
		
		
		public void run()
		{
			try
			{
				//creating document 
				new Documents(url, controller);				
			}
			catch(Exception exp)
			{
				controller.getQueue().setText(exp, controller.getQueue().getErrorIndex());
			}
		}
				
	}	
}
