/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookShopPackage;

/**
 *
 * @author Taylor
 */
public class BookInfo 
{
/*
    This is the book class that holds all the information about a particular book.
    It is used for the Inventory ArrayList and the Shopping cart ArrayList
*/


    int bookId;
    String bookTitle;
    float price;
    int quantity;
    double discount;


    BookInfo(int bookID,String bookTitle,float price, int quant)
    {
        this.bookId = bookID;
        this.bookTitle = bookTitle;
        this.price = price;
        this.quantity = quant;
    }

    // Getters and Setters for class Book.

    public int getBookId()
    {
        return this.bookId;
    }

    public String getBookTitle()
    {
        return this.bookTitle;
    }

    public float getPrice()
    {
        return this.price;
    }

    public int getQuantity()
    {
        return this.quantity;
    }

    public double getDiscount()
    {
        return this.discount;
    }

    public void setDiscount(double discount)
    {
        this.discount = discount;
    }
    
}
