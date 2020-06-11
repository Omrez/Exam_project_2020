package Domain;

/**
 * The Clothing class is used to create Clothing objects.
 */
public class Clothing {

    String type;
    String price;
    String clothingID;
    int count = 0; //count is used to keep track of how many items of this type is added when creating a new order.

    public String getClothingID() {
        return clothingID;
    }

    public void setClothingID(String clothingID) {
        this.clothingID = clothingID;
    }


    public void setPrice(String price) {
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

    public String getPrice() {
        return price;
    }

}
