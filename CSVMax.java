
/**
 * CSVMax is used to find the maximum temperature in a day or any given time from a downloaded 
 * csv file from dukelearntoprogram containing hourly records of temperature daily for 365 days.
 * 
 *N8a
 * @author Dimgba Martha Otisi 
 * @2020,January
 * @martha_samuel_ 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class CSVMax {
public CSVRecord hottestHourInFile (CSVParser parser){
    //Start with largest so far as nothing
    CSVRecord largestSoFar=null;
    //for each row(currentRow) in the csv file
    for(CSVRecord currentRow:parser){
        //if largestsofar is nothing
        /*largestSoFar=getLargestOfTwo(currentRow,largestSoFar);
        } */
        if(largestSoFar==null){
        
        //update largestsofar to be currentRow
        largestSoFar=currentRow;
    }
        //otherwise
        else{
            //do conversion from string type to double type
            double currentTemp =Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp =Double.parseDouble(largestSoFar.get("TemperatureF"));
        
        //check if currentrow temperature>largestsofar temperature
        if(currentTemp>largestTemp){
        //if so update largestsofar to currentrow
        largestSoFar=currentRow;
    }
}
}

            //the largestsofar is our answer
            return largestSoFar;
}

public void testHottestInDay(){
    FileResource fr=new FileResource("2015/weather-2015-01-01.csv");
    CSVRecord largest=hottestHourInFile(fr.getCSVParser());
    System.out.println("Hottest temperature was " + largest.get("TemperatureF")+" at "+largest.get("TimeEST"));
    
}
public CSVRecord hottestInManyDays(){
    CSVRecord largestSoFar=null;
    DirectoryResource dr=new DirectoryResource();
    //iterate over files
    for(File f:dr.selectedFiles()){//the dr returns this file
        FileResource fr=new FileResource(f);
        //use method to get largest in file
        CSVRecord currentRow=hottestHourInFile(fr.getCSVParser());
       
        /*largestSoFar=getLargestOfTwo(currentRow,largestSoFar);
        } */
        if(largestSoFar==null){
            largestSoFar=currentRow;
        }
        else{
       double currentTemp =Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp =Double.parseDouble(largestSoFar.get("TemperatureF"));
        
        //check if currentrow temperature>largestsofar temperature
        if(currentTemp>largestTemp){
        //if so update largestsofar to currentrow
        largestSoFar=currentRow;     
}
}
}
            //the largestsofar is our answer
            return largestSoFar;
}


public void testHottestInManyDays(){
 CSVRecord largest=hottestInManyDays();   
    System.out.println("Hottest temperature was " + largest.get("TemperatureF")+"  at "+largest.get("DateUTC"));
} 

//this section addresses duplicated codes. to use this ,delete the duplicated codes(from
// if(largestSoFar==null) to largestSoFar=currentRow;)  and replace with the code in a comment
//just before it.   

/*public CSVRecord getLargestOfTwo(CSVRecord currentRow,CSVRecord LargestSoFar){
if(largestSoFar==null){
        
        //update largestsofar to be currentRow
        largestSoFar=currentRow;
    }
        //otherwise
        else{
            //do conversion from string type to double type
            double currentTemp =Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp =Double.parseDouble(largestSoFar.get("TemperatureF"));
        
        //check if currentrow temperature>largestsofar temperature
        if(currentTemp>largestTemp){
        //if so update largestsofar to currentrow
        largestSoFar=currentRow;
    }
}
   //the largestsofar is our answer
            return largestSoFar;
}  */  
}
