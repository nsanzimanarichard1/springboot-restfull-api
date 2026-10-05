package beansId;

public class Order {

    private  String productName;
    private  String productDescription;
    private  double productPrice;



    @Override
    public String toString(){
        return " Your order has been processed. product name is:" +
                " " + productName +"product description is: " +
                " " + productDescription + "product price is:" +
                " " + productPrice;
    }

    //constructor
    public Order(String productName, String productDescription, double productPrice){
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
    };


    public void setProductPrice(double productPrice){
        this.productPrice = productPrice;
    }


    // getter method which deserialize outgoing data
     public String getProductName() {

        return productName;
     }

     public String getProductDescription(){

        return  productDescription;
     }

     public double getProductPrice(){

        return  productPrice;
     }


}

