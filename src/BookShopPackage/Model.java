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
import java.io.*;
import java.util.ArrayList;

// Model of the MVC architecture.
// Holds and process the Inventory and shopping cart
public class Model
{
    private ArrayList<BookInfo> inventoryList;
    private ArrayList<BookInfo> shoppingCart;
    int numItems;
    int iteration = 1;
    int quantity = 0;

    Model() throws IOException
    {
        // Opens inventory file and parses information.
        FileReader inventory = new FileReader("C:\\Users\\Taylor\\Documents\\NetBeansProjects\\BookShop\\src\\BookShopPackage\\inventory.txt");
        this.inventoryList = parseInventory(inventory);
        shoppingCart = new ArrayList<>();
    }

    public void addToShoppingCart(int bookId, int quantity)
    {
        for (int i = 0; i < inventoryList.size(); i++)
        {
            if (bookId == inventoryList.get(i).bookId)
            {
                BookInfo book = inventoryList.get(i);
                book.quantity = quantity;
                book.discount = calculateDiscounts(book.quantity);
                this.shoppingCart.add(book);
//                numItems--;
                break;
            }
        }
    }

    public void clearShoppingCart()
    {
        this.shoppingCart.clear();
    }

    private static ArrayList<BookInfo> parseInventory(FileReader file) throws IOException
    {
        BufferedReader bufRead = new BufferedReader(file);
        String line;
        ArrayList<BookInfo> booksInventory = new ArrayList<>();

        while (((line = bufRead.readLine()) != null))
        {
            String[] s = line.split(",");
            int bookID =Integer.parseInt(s[0]);
            String bookName = s[1];
            float price = Float.parseFloat(s[2]);
//            System.out.println("Double Value: "+val);
//            BigDecimal bigPrice = new BigDecimal(val);
//            System.out.println("Big Decimal Value: "+bigPrice);
            booksInventory.add(new BookInfo(bookID,bookName,price,0));
        }

        return booksInventory;
    }

    public ArrayList<BookInfo> getInventory()
    {
        return this.inventoryList;
    }

    public ArrayList<BookInfo> getShoppingCart()
    {
        return this.shoppingCart;
    }

    public void setNumItems(int numItems)
    {
        this.numItems = numItems;
    }

    public void setQuantity(int quant)
    {
        this.quantity = quant;
    }

    public BookInfo findBook(int bookId)
    {
        for (int i = 0; i < inventoryList.size(); i++)
        {
            if (bookId == inventoryList.get(i).bookId)
            {
                return inventoryList.get(i);
            }
        }

        return null;
    }

    public int getIteration()
    {
        return this.iteration;
    }

    public void setIteration(int iter)
    {
        this.iteration = iter;
    }

    // Returns the various discounts for buying different quantities.
    public double calculateDiscounts(int quantity)
    {
        double discount = 0;

        if (quantity >= 5 && quantity <= 9)
        {
            discount = .1;
        }
        else if(quantity >= 10 && quantity <= 14)
        {
            discount = .15;
        }
        else if(quantity >= 15)
        {
            discount = .20;
        }

        return discount;
    }

    // Creates the transactions file after clicking the finish order button.
    public void createTransactionsFile(String transID , String date) throws IOException
    {
        FileWriter fileWriter = new FileWriter("transactions.txt",true);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (int i = 0; i < shoppingCart.size(); i++)
        {
            System.out.print("Printing text to transactions file...");

            double subtotal = (shoppingCart.get(i).price) * (shoppingCart.get(i).quantity);
            double discount = shoppingCart.get(i).getDiscount();
            double total = ((subtotal) - (discount*subtotal));
            String totalStr = String.format("%.2f",total);

            String str = transID + ", " + shoppingCart.get(i).getBookId() + ", " +
                    shoppingCart.get(i).getBookTitle() + ", " + shoppingCart.get(i).getPrice()
                    + ", " + shoppingCart.get(i).getQuantity() + ", " + shoppingCart.get(i).getDiscount() +
                    ", " + "$" + totalStr + ", " + date + " EST" + "\n";
            printWriter.println(str);
        }
        printWriter.close();
    }
}

