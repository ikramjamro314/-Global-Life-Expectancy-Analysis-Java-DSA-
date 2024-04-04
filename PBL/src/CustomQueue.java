
public class CustomQueue {
       Object[] countries;
        int size=0;
        CustomQueue(int capacity){
            countries=new Object[capacity];
        }
        public int getSize(){return size;}
        public boolean isEmpty(){
            return getSize() == 0;
        }
    public void addCountryDataInQueue(Country country){
        countries[size++] = country;
    }

    public void print(){
        try {
            System.out.printf("%-25s%-20s\n", "Country Name", "Life Expectancy");
        } catch (Exception e) {
            System.out.println("Error occurred while printing: " + e.getMessage());
        }
            for(int i=0;i<getSize();i++){
                System.out.println(countries[i]);
            }
    }
    }