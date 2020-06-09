/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heptathlon.heptathlonrmi.Objets;

/**
 *
 * @author Josselin
 */
public class Article implements java.io.Serializable {
    
    private int reference, nb_stock;
    private double prix_unitaire;
    private String famille, nom;
    private boolean payer;

    public boolean isPayer() {
        return payer;
    }

    public void setPayer(boolean payer) {
        this.payer = payer;
    }
    
public int getReference() { 
      return reference; 
   } 
   public String getFamille() { 
      return famille; 
   } 
   public String getNom() { 
      return nom; 
   } 
   public double getPrix_unitaire() { 
      return prix_unitaire; 
   } 
   public int getNb_stock() { 
      return nb_stock; 
   } 
   public void setReference(int reference) { 
      this.reference = reference; 
   } 
   public void setFamille(String famille) { 
      this.famille = famille; 
   } 
   public void setNom(String nom) { 
      this.nom = nom; 
   } 
   public void setPrix_unitaire(double prix_unitaire) { 
      this.prix_unitaire = prix_unitaire; 
   } 
   public void setNb_stock(int nb_stock) { 
      this.nb_stock = nb_stock; 
   } 
}
