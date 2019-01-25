/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookShopPackage;

import java.io.IOException;

/**
 *
 * @author Taylor
 */
public class main
{
    public static void main(String[] args) throws IOException
    {
        Model model = new Model();
        GUIcreator gui = new GUIcreator();

        Controller eStore = new Controller(model,gui);
    }
    
}
