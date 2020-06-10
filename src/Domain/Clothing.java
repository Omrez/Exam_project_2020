package Domain;

public class Clothing {

    String type;
    double price;
    int count = 0;

    public void setPrice(double price) {
        this.price = price;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (this.count + count >= 0) {
            this.count += count;
        } else if (this.count + count <0){
            this.count = 0;

        }
    }



    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }





}
