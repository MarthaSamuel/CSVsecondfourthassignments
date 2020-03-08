
/**
 * Write a description of n here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class WeatherParser {
	
	/***
	 * This method returns the CSVRecord with the coldest temperature in the file
	 * and thus all the information about that coldest temperature, such as the hour of the
	 * coldest temperature
	 * @param parser
	 * @return
	 */
	public CSVRecord coldestHourInFile(CSVParser parser){
		
		CSVRecord ColdestSoFar = null;
		for(CSVRecord currentRow : parser){
			 if(ColdestSoFar == null){
				 ColdestSoFar = currentRow;
			 }
			 else
			 {
				 double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				 double Coldest = Double.parseDouble(currentRow.get("TemperatureF"));
				 if(currentTemp < Coldest){
					 ColdestSoFar = currentRow; 
				 }
			 }
		}
		
		
		return ColdestSoFar;
		
	
	}
	
	
	/***
	 * Write the method fileWithColdestTemperaturethat has no parameters. This
method should return a String that is the name of the file from selected files that has the
coldest temperature.
	 */
	public String fileWithColdestTemperature(){
		DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestRecord = null;
        File coldestFile = null;
        
	    for(File f : dr.selectedFiles()){
	           FileResource fil = new FileResource(f);
	           CSVParser rt = fil.getCSVParser();
	           CSVRecord x = coldestHourInFile(rt);
	          
	           if(coldestRecord == null){
	        	   coldestRecord = x;
	           }
	           double Coldest = Double.parseDouble(coldestRecord.get("TemperatureF"));
	           double current = Double.parseDouble(x.get("TemperatureF"));
	           

        	   coldestRecord = x;
        	   coldestFile = f;

	           
	           if(Coldest > current){
	        	   coldestRecord = x;
	        	   coldestFile = f;
	           }		
	    }
	    
	    System.out.println(String.format("Coldest day was in file %s", coldestFile.getName()));
		System.out.println(String.format("Coldest temperature on that day was %s", coldestRecord.get("TemperatureF")));
		
		System.out.println("All the Temperatures on the coldest day were:");
		
		FileResource fil = new FileResource(coldestFile);
        CSVParser rt = fil.getCSVParser();
		for(CSVRecord currentRow : rt){

			System.out.println(String.format("%s %s: %s", currentRow.get("DateUTC"), currentRow.get("TimeEST"), currentRow.get("TemperatureF")));
		}
		
		
		
		return coldestFile.getName();
	}
	
	
	/***
	 * Write a method named lowestHumidityInFile that has one parameter, a CSVParser named parser. This method returns the CSVRecord that has the lowest humidity. 
	 * If there is a tie, then return the first such record that was found.
Note that sometimes there is not a number in the Humidity column but instead there is the string “N/A”. This only happens very rarely.
 You should check to make sure the value you get is not “N/A” before converting it to a number.
Also note that the header for the time is either TimeEST or TimeEDT, depending on the time of year. 
You will instead use the DateUTC field at the right end of the data file to get both the date and time of a temperature reading.
	 */
	public CSVRecord lowestHumidityInFile(CSVParser parser)
	{
		CSVRecord lowestHumidityRecord = null;
		double lowestHumidity = 0.0;
		for(CSVRecord currRecord:parser){
			if (currRecord.get("Humidity") == "N/A"){
				continue;
			}
			double currentHumidity = Double.parseDouble(currRecord.get("Humidity"));
			if(lowestHumidity == currentHumidity){
				continue;
			}
			if(lowestHumidity < currentHumidity){
				lowestHumidity = currentHumidity;
				
				lowestHumidityRecord = currRecord;
			}
		}		
		
		return lowestHumidityRecord;
	}	
	/***
	 *  Write the method averageTemperatureInFile that has one parameter, a CSVParser named parser. T
	 *  his method returns a double that represents the average temperature in the file. 
	 *  You should also write a void method named testAverageTemperatureInFile() to test this method. 
	 *  When this method runs and selects the file for January 20, 2014, the method should print out
	 * 
	 */
	public double averageTemperatureInFile(CSVParser parser){
		double mainTemp = 0.0;
		double finalTemp = 0.0;
		int ctr = 0;
		for(CSVRecord tempRecord : parser){
			ctr++;
			double currentTemp = Double.parseDouble(tempRecord.get("TemperatureF"));
			finalTemp = mainTemp + currentTemp;
		}
		double finale = finalTemp/ctr;
		return finale; 
	}

	/***
	 * Write the method averageTemperatureWithHighHumidityInFile that has two parameters, a CSVParser named parser and an integer named value. T
	 * his method returns a double that represents the average temperature of only those temperatures when the humidity was greater than or equal to value. 
	 * You should also write a void method named testAverageTemperatureWithHighHumidityInFile() to test this method. 
	 * When this method runs checking for humidity greater than or equal to 80 and selects the file for January 20, 2014, the method should print out
	 */
	public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
		double finTemp = 0.0;
		int ctr = 0;
		for(CSVRecord record: parser){
			double currentHumidity = Double.parseDouble(record.get("Humidity"));
			if(currentHumidity >= value){
				ctr++;
				double destrop = Double.parseDouble(record.get("TemperatureF"));
				if(finTemp != 0.0){
					finTemp = finTemp + destrop;
				}
				else{
					finTemp = destrop;
				}
				
			}
			else{
				continue;
			}
		}
		if(finTemp == 0.0){
			return 0.0;
		}
		
		return finTemp/ctr;
	}
	
	
	/***
	 * You should also write a void method named testLowestHumidityInFile() to test this method that starts with these lines:
and then prints the lowest humidity AND the time the lowest humidity occurred. For example, for the file weather-2014-01-20.csv, the output should be:
	 */
	public void testaverageTemperatureWithHighHumidityInFile(){
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		double answer = averageTemperatureWithHighHumidityInFile(parser, 80);
		if(answer == 0.0){
			System.out.println("No temperatures with that humidity");
			
		}
		System.out.println(String.format("Average Temp when high Humidity is %s", answer));
	}
	public void testaverageTemperatureInFile(){
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		
		System.out.println(String.format("Average temperature in file is %s", averageTemperatureInFile(parser)));
	}
	public void testLowestHumidityInFile(){
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		CSVRecord csv = lowestHumidityInFile(parser);
		
		String tprint = "Lowest Humidity was %s at %s";
		System.out.println(String.format(tprint, csv.get("Humidity"),  csv.get("DateUTC")));
	}
	
	/***
	 * You should also write a void method named
testFileWithColdestTemperature()to test this method. Note that after
determining the filename, you could call the method coldestHourInFileto
determine the coldest temperature on that day.
	 */
	public void testFileWithColdestTemperature(){
		fileWithColdestTemperature();
	}
	
	
	public void printme(String toprint){
		System.out.println(toprint);
		
		
	}
	
	
	
	/***
	 * test this method and print out information about that
	 * coldest temperature such as the time of its occurrence.
	 * 
	 * NOTE: Sometimes there was not a valid reading at a specific hour, so the temperature
	 * field says ­9999. You should ignore these bogus temperature values when calculating
	 * the lowest temperature
	 */
	public void testColdestHourInFile(){
		
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		
		CSVRecord csvrecord = coldestHourInFile(parser);
		System.out.println(csvrecord.get("TimeEST"));
		
		
		
		
	}
}

	

