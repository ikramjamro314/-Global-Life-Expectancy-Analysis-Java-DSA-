import java.util.ArrayList;

public class CustomStack {
        stackCountries FirstCountryAverage;
        int size;
        class stackCountries{
            Country country;
            stackCountries nextCountryAverage;
            stackCountries( Country c , stackCountries nextCountryAverage){
                this.country=c;
                this.nextCountryAverage=nextCountryAverage;
            }
        }
        public int  getSize(){return size;}
       public void push(Country c){
              FirstCountryAverage=new stackCountries(c,FirstCountryAverage);
              ++size;
       }
       public void print(){
           stackCountries tempCountryAverage = FirstCountryAverage;
            while (tempCountryAverage!=null){
                System.out.println(tempCountryAverage.country);
                tempCountryAverage=tempCountryAverage.nextCountryAverage;
            }
       }
       public void best_Average_Worst(){
           stackCountries tempCountryAverage =FirstCountryAverage;
           System.out.println("The Best life Expectancy : \t "+FirstCountryAverage.country);
           int count=0;
           while (tempCountryAverage!=null){
               if(count==(getSize()-1)/2){
                   System.out.println("The Average life Expectancy : " + tempCountryAverage.country);
                   break;
               }
               tempCountryAverage = tempCountryAverage.nextCountryAverage;
               ++count;
           }

           stackCountries tempLastCountryAverage=FirstCountryAverage;
           while (tempLastCountryAverage.nextCountryAverage!=null){
               tempLastCountryAverage=tempLastCountryAverage.nextCountryAverage;
            }
           System.out.println("The Worst life Expectancy : "+tempLastCountryAverage.country);
       }
    }
