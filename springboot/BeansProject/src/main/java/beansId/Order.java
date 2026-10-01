package beansId;

public class Order {

    private  String productName;
    private  String productDescription;
    private  String productPrice;



    @Override
    public String toString(){
        return " Your order has been processed. product name is:" +
                " " + productName +"product description is: " +
                " " + productDescription + "product price is:" +
                " " + productPrice;
    }

    //constructor
    public Order(String productName, String productDescription, String productPrice){
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    // setter method which deserialize incoming data
    public void setProductName(String productName){
        this.productName = productName;
    };
    public void setProductDescription(String productDescription){
        this.productDescription = productDescription;
    }
    public void setProductPrice(String productPrice){
        this.productPrice = productPrice;
    }


    // getter method which deserialize outgoing data
     public String getProductName() {
        return productName;
     }
     public String getProductDescription(){
        return  productDescription;
     }

     public String getProductPrice(){
        return  productPrice;
     }


}

