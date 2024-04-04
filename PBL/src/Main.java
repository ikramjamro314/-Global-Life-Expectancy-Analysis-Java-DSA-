import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class YearWiseLifeExpectancyQueues{
    YearQueue headQueue;      // Head node of the linked list representing year-wise queues
    int size;
    class YearQueue{
        YearQueue nextQueue;
        int year;      // Year associated with this queue
        CustomQueue countriesDataYearWise;  // CustomQueue object storing countries and their life expectancies for this year
        YearQueue(int year , CustomQueue countriesDataYearWise){
            this.year=year;
            this.countriesDataYearWise=countriesDataYearWise;
        }
    }

    public int getSize() {
        return size;
    }
    // Add Queues yearWise
    public void add(int year , CustomQueue country){
        YearQueue newCountryQueue = new YearQueue(year,country);
        if(headQueue==null){
            headQueue=newCountryQueue;
            ++size;
            return;
        }
        YearQueue tempQueue=headQueue;
        while (tempQueue.nextQueue!=null){
            tempQueue=tempQueue.nextQueue;
        }
        tempQueue.nextQueue=newCountryQueue;
        ++size;
    }
    // Finds the CustomQueue object containing country data for a specific year
    public CustomQueue findYear(int year){
        YearQueue tempQueue=headQueue;
        while (tempQueue!=null){
            if(tempQueue.year==year){
                return tempQueue.countriesDataYearWise;
            }
            tempQueue=tempQueue.nextQueue;
        }
        return null;
    }
//    Prints the countries and their life expectancies for a specific year (delegates to CustomQueue's print method)
    public void print(int year){
        CustomQueue matchingYearQueue = findYear(year);
        if(matchingYearQueue==null){
            System.out.println(" The year does not exist");
        }else {
            matchingYearQueue.print();
        }
    }
}

class YearWiseCountryDataHashTables {

    // Head node of the linked list representing hash tables for each year
     YearHashTable headHashTable;
    private int size;

    class YearHashTable {
        private YearHashTable next;
        private int year;

        // HashTable object storing country data (key-value pairs) for this year
        private HashTable countries_YearWise_Data_HashTable;

        public YearHashTable(int year, HashTable countries_YearWise_Data_HashTable) {
            this.year = year;
            this.countries_YearWise_Data_HashTable = countries_YearWise_Data_HashTable;
        }
    }

    public int getSize() {
        return size;
    }

    // Adds a new hash table with country data for a specific year to the linked list
    public void add(int year, HashTable country) {
        YearHashTable newCountryHashTable = new YearHashTable(year, country);
        if (headHashTable == null) {
            headHashTable = newCountryHashTable;
            size++;
            return;
        }
        YearHashTable tempTable = headHashTable;
        while (tempTable.next != null) {
            tempTable = tempTable.next;
        }
        tempTable.next = newCountryHashTable;
        size++;
    }

    // Finds the HashTable object containing country data for a specific year
    public HashTable findYear(int year) {
        YearHashTable tempTable = headHashTable;
        while (tempTable != null) {
            if (tempTable.year == year) {
                return tempTable.countries_YearWise_Data_HashTable;
            }
            tempTable = tempTable.next;
        }
        return null;
    }

    public void print(int year, ArrayList<String> CountryCodes) {
        HashTable matchingYeartable = findYear(year);
        if (matchingYeartable == null) {
            System.out.println(" The year does not exist");
        } else {
            for (String code : CountryCodes) {
                Country country = (Country) matchingYeartable.get(code);
                System.out.println(country);
            }
        }
    }
}
// Country Class to make objects  Storing the data for each Country
class Country{
    String countryName;
    double lifeExpectancy;
    Country(String countryName , double lifeExpectancy){
        this.countryName=countryName;
        this.lifeExpectancy=lifeExpectancy;
    }
  public String toString(){      // to print the details
        return   String.format("%-30s%-15f", countryName, lifeExpectancy);}
}
public class Main {

    // Function to find country with best life expectancy in specific years
    public static void findHighestLifeExpectancyCountries(YearWiseLifeExpectancyQueues queues, int year1, int year2) {

        CustomQueue q1 = queues.findYear(year1);
        if (q1 == null) {
            return;
        }
        CustomQueue q2 = queues.findYear(year2);
        if (q2 == null) {
            return;
        }
        Country c1 = (Country) q1.countries[q1.getSize() - 1];
        Country c2 = (Country) q2.countries[q2.getSize() - 1];
        if (c1.lifeExpectancy >= c2.lifeExpectancy) {
            System.out.println("The best Life Expectancy during (1962 and 1964) is in the year 1962 : ");
            System.out.printf("%-30s%-15s", " Country Name ", " Life Expectancy \n " + c1);

        }
        System.out.println("The best Life Expectancy during (1962 and 1964) is in the year 1964 : ");
        System.out.printf("%-30s%-15s", " Country Name ", " Life Expectancy \n " + c2);

    }

    // Problem 1 Ends.

    // Displays life expectancy for a specific country code across all years provided
    public static void display_LifeExpectancy_By_CountryCode(YearWiseCountryDataHashTables tables, int[] years, String countryCode) {
        System.out.printf("%-30s%-10s", " Year ", " Life Expectancy \n ");
        for (int year : years) {
            HashTable matchingYear = tables.findYear(year);
            if (matchingYear == null) {
                System.out.println("Wrong input!");
                return;
            }
            Country country = (Country) matchingYear.get(countryCode);
            if (country != null) {
                System.out.println(year + "\t\t" + country.lifeExpectancy);
            } else {
                System.out.println(year + "\t\t" + "Data not found for country code: " + countryCode);
            }
        }
    }

    // Problem 2 Ends...
    public static void SelectionSort(ArrayList<Country> countries) {
        for (int i = 0; i < countries.size() - 1; i++) {
            for (int j = i + 1; j < countries.size(); j++) {
                if (countries.get(i).lifeExpectancy > countries.get(j).lifeExpectancy) {
                    Country temp = countries.get(i);
                    countries.set(i, countries.get(j));
                    countries.set(j, temp);
                }
            }
        }
    }
    // Sorting Ends

    // Function to calculate average of the Life Expectancies of each country for all years
    public static ArrayList<Country> calculateAverageLifeExpectancies(ArrayList<ArrayList<Country>> LifeExpectanciesOfEveryCountryForAllYears) {
        ArrayList<Country> LE_average = new ArrayList<>();
        // iterates through all countries data for every years
        for (ArrayList<Country> countries : LifeExpectanciesOfEveryCountryForAllYears) {
            double sum = 0;
            double avg = 0;
            String CountryName = null;
            // iterate through life expectancy of a single country for all years
            for (Country country : countries) {
                sum += country.lifeExpectancy;
                CountryName = country.countryName;
            }
            avg = (sum / countries.size());
            Country countryObj = new Country(CountryName, avg);
            LE_average.add(countryObj);
        }
        return LE_average;
    }

    // Average Function Ends
    public static boolean postulate(YearWiseCountryDataHashTables hashTables, int[] allYears , ArrayList<String> allCountryCodes) {
            // Initialize a HashMap to store the sum of life expectancies and the count of countries for each initial alphabet letter
            HashMap<Character, Double[]> alphabetExp = new HashMap<>();

            // Initialize the alphabetExp map with all alphabets
            for (char letter = 'A'; letter <= 'Z'; letter++) {
                alphabetExp.put(letter, new Double[]{0.0, 0.0}); // {sum of life expectancy, count of countries}
            }

            // Traverse through each year's hash table
            for (int i = 0; i < allYears.length; i++) {
                HashTable tempTable = hashTables.findYear(allYears[i]);

                if (tempTable != null) { // Ensure that the hash table for the year exists
                    // Calculate the sum of life expectancies and count for each initial alphabet letter
                    for (String country : allCountryCodes) {
                        char initialLetter = Character.toUpperCase(country.charAt(0));
                        Country data = (Country) tempTable.get(country);
                        if (data != null) { // Ensure that the country data exists for the given year
                            double lifeExpectancy2 = data.lifeExpectancy;
                            Double[] expData = alphabetExp.get(initialLetter);
                            if (expData != null) { // Ensure that the array exists in the map
                                expData[0] += lifeExpectancy2;
                                expData[1]++;
                            }
                        }
                    }
                }
            }

            // Calculate the average life expectancy for countries starting with 'A'
            Double[] expDataA = alphabetExp.get('A');
            double averageLifeExpA = (expDataA != null && expDataA[1] != 0.0) ? expDataA[0] / expDataA[1] : 0.0;

            // Calculate the total average life expectancy for countries starting with other letters
            double totalLifeExpOther = 0.0;
            double countOther = 0.0;
            for (char letter = 'B'; letter <= 'Z'; letter++) {
                Double[] expData = alphabetExp.get(letter);
                if (expData != null) { // Ensure that the array exists in the map
                    totalLifeExpOther += expData[0];
                    countOther += expData[1];
                }
            }
            double averageLifeExpOther = (countOther != 0.0) ? totalLifeExpOther / countOther : 0.0;

            // Return true if countries starting with 'A' have a higher average life expectancy than others, false otherwise
            return averageLifeExpA > averageLifeExpOther;
        }

    public static void main(String[] args) {

        String countryName = null;
        String countryCode = null;
        double lifeExpectancy = 0.0F;

        // Dummy Arraylists to store the Countries data from the data set
        ArrayList<String> allCountryNames = null;
        ArrayList<String> allCountryCodes = null;
        int[] allYears;

        // Variable help for data Reading
        String file = "src\\global life expectancy dataset.csv";
        String line = "";
        BufferedReader reader = null;

//        Step 1: Make a hashtable storing countries and their life expectancy in each year given in the data set.

        // LinkedList object that contains all Hastables for all years
        YearWiseCountryDataHashTables hashTables = new YearWiseCountryDataHashTables();
        HashTable yearHashTable;             // Reference Variable for Hashtable
        Country countryData = null;         // Reference Variable for Country Objects
        int numYears;                          // size of the years
        int year = 0;

        // Start file Reading
        try {
            // Open and read the file
            reader = new BufferedReader(new FileReader(file));
            String headerLine = reader.readLine();
            String[] years = headerLine.split(",");
            numYears = years.length - 2;               // size of the years
            allYears = new int[numYears];             // array to store years

            // Iterates through years to store the countries data of every country into the hashtable
            for (int i = 2; i < years.length; i++) {
                year = Integer.parseInt(years[i]);
                yearHashTable = new HashTable();        // create a new hashtable for every year

                reader = new BufferedReader(new FileReader(file));
                reader.readLine();                              // header ignored
                allCountryCodes = new ArrayList<>();
                allCountryNames = new ArrayList<>();

                while ((line = reader.readLine()) != null) {
                    String[] row = line.split(",", -1);
                    countryName = row[0];
                    countryCode = row[1];

                    // Handle empty life expectancy values (convert to 0.0)
                    if (row[i].isEmpty()) {
                        lifeExpectancy = 0.0;
                    } else {
                        try {
                            lifeExpectancy = Double.parseDouble(row[i]);
                        } catch (NumberFormatException e) {
                            lifeExpectancy = 0.0;              // to change the null values of the string to double
                        }
                    }

                    // make every country object for new country data  of every year
                    countryData = new Country(countryName, lifeExpectancy);
                    yearHashTable.put(countryCode, countryData);      // put country data to hashtable of a specific year
                    allCountryCodes.add(countryCode);
                    allCountryNames.add(countryName);
                }
                hashTables.add(year, yearHashTable);      // adding the hashtables having data in hashtable LL
                allYears[i - 2] = year;                    // storing the  countries
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

//        System.out.println(hashTables.getSize());
//        hashTables.print(1960,allCountryCodes);

//        System.out.println("Step1 Ends-----------------------------------------");


//        Step 2: Now make queues (one for each year) from hashtable storing country names based on life
//        expectancy in ascending order.

        // Make a object of Queues LL to store all Queue one by one for every year
        YearWiseLifeExpectancyQueues Queues = new YearWiseLifeExpectancyQueues();
        CustomQueue yearQueue = null;
        ArrayList<Country> countryArrayList;         // storing all countries data of all years

        for (int i = 0; i < numYears; i++) {          // iterate through the years
            HashTable dataOut = hashTables.findYear(allYears[i]);   // extract the hashtable of the year though find year algo
            yearQueue = new CustomQueue(allCountryCodes.size());    // create a Queue of the same size as the data in the hashtable
            countryArrayList = new ArrayList();
            for (String Code : allCountryCodes) {                // iterate through the countries
                Country country = (Country) dataOut.get(Code);    // Storing all data into the Country object
                if (country.lifeExpectancy == 0.0) {
                    continue;                                    // If data is 0 so skip the country
                }
                countryArrayList.add(country);                   // then add the country object into the arraylist of countries
            }

            SelectionSort(countryArrayList);           // Sorting Algorithm to sort the country ArrayList

            for (int j = 0; j < countryArrayList.size(); j++) {
                Country country = new Country(countryArrayList.get(j).countryName, countryArrayList.get(j).lifeExpectancy);
                if (country.lifeExpectancy == 0.0) {
                    continue;
                }
                yearQueue.addCountryDataInQueue(country);      // add all the sorted countries into the Queue
            }
            Queues.add(allYears[i], yearQueue);       // then add all the Queues into the Queue ArrayList
        }

//        System.out.println(Queues.getSize());
//        Queues.print(1960);

//        System.out.println("Step2 Ends-----------------------------------------");

//        Step 3: Make a stack containing the countries names based on the best life expectancy (average of all years
//                given in the dataset) on top using step 3 only.
        // Create a stack to store countries sorted by average life expectancy

        CustomStack stack = new CustomStack();
        ArrayList<Country> CountriesDataCountryWise;
        ArrayList<ArrayList<Country>> LifeExpectanciesOfEveryCountryForAllYears = new ArrayList<>();

        // Process each country code
        for (String code : allCountryCodes) {
            CountriesDataCountryWise = new ArrayList<>();
            // Collect life expectancy values for this country across all years
            for (int i = 0; i < allYears.length; i++) {
                HashTable yearsData = hashTables.findYear(allYears[i]);
                Country country = (Country) yearsData.get(code);
                if (country.lifeExpectancy == 0.0) {
                    continue;
                }
                // Associate the life expectancy values with this country in the stack
                CountriesDataCountryWise.add(country);
            }
            // Add all the ArrayLists of having every country data for all years in a new Arraylist
            LifeExpectanciesOfEveryCountryForAllYears.add(CountriesDataCountryWise);
        }
//         Calculate average life expectancy for each country
        ArrayList<Country> averageLifeExpectancies = calculateAverageLifeExpectancies(LifeExpectanciesOfEveryCountryForAllYears);

        SelectionSort(averageLifeExpectancies);            // Sorting Algorithm
        for (Country avgCountry : averageLifeExpectancies) {         // iterate through average lifeExpectancies for all countries
            stack.push(avgCountry);
        }
//        System.out.println(stack.getSize());
//        stack.print();

//        System.out.println("Step3 Ends-----------------------------------------");


// Problem 1: How to find out which countries were providing best life expectancy during year 1962 and 1964.
//        System.out.println("________________ Find Countries that Providing Best LifeExpectancy in the camparision of two years______________ ");
//        Scanner inputYear = new Scanner(System.in);
//        System.out.println("Enter year1 (From 1960 to 2020) : ");
//        int year1= inputYear.nextInt();
//        if(year1<1960 || year1>2020){
//            System.out.println("Wrong input.....");
//        }
//        System.out.println("Enter year2 (From 1960 to 2020): ");
//        int year2= inputYear.nextInt();
//        if(year2<1960 || year2>2020){
//            System.out.println("Wrong input.....");
//        }
//        findHighestLifeExpectancyCountries(Queues,year1,year2);
//        System.out.println();
//        System.out.println("Problem 1---------------------- ends");
//
//         Problem 2: How to search and display life expectancy of a country in each year using Step 1.
//        System.out.println("___________________ Search and Display the life expectancy of all years based on Code");
//        Scanner code = new Scanner(System.in);
//        System.out.println("Enter Code of the Country to be Display : ");
//        String codeStr =code.nextLine();
//        System.out.println("Life Expectancies for all Years (1960 to 2020) for the Country : " + codeStr);
//        display_LifeExpectancy_By_CountryCode(hashTables,allYears,codeStr);
//        System.out.println();
//        System.out.println("Problem 2---------------------- ends");
//
//       Problem 3:   Which country is providing best, average and worst life expectancy (Use step 3 data structure).
        System.out.println("_____________Best , Average and Worst Life Expectancy for all countries of all years________________");
        stack.best_Average_Worst();
//
//        //Postulate 1: Is it true that the countries whose name start with A are providing better life expectancy than
//        //the rest of the countries.
        System.out.println(postulate(hashTables,allYears,allCountryCodes));


    }
}