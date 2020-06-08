/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heptathlon.heptathlonrmi.Objets;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Josselin
 */
public class Client {
    
    private Client() {}
    
    public static void main(String[] args) {
        
        //String host = (args.length < 1) ? null : args[0];
        
        /*try{
            Registry registry = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registry.lookup("Hello");
            
            String txt = JOptionPane.showInputDialog("What is your name ?");

            
            String response = stub.sayHello(txt);
            System.out.println(response + " <- Reponse ");
        } catch (Exception e){
            System.out.println("Client - client");
        }
*/
/*
        try { 
         // Getting the registry 
         Registry registry = LocateRegistry.getRegistry(null); 
    
         // Looking up the registry for the remote object 
         Hello stub = (Hello) registry.lookup("Hello"); 
    
         // Calling the remote method using the obtained object 
         List<Article> list = stub.getArticles(); 
         for (Article s:list) { 
            
            // System.out.println("bc "+s.getBranch()); 
            System.out.println("Reference: " + s.getReference()); 
            System.out.println("Famille: " + s.getFamille()); 
            System.out.println("Nom: " + s.getNom()); 
            System.out.println("Prix unitaire: " + s.getPrix_unitaire()); 
            System.out.println("Nb stock: " + s.getNb_stock()); 
         }  
      // System.out.println(list); 
      } catch (Exception e) { 
         System.err.println("Client exception: " + e.toString()); 
      } 
*/
    }

    
}

