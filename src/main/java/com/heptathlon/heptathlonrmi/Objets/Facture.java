/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heptathlon.heptathlonrmi.Objets;

import java.sql.Date;

/**
 *
 * @author Josselin
 */
public class Facture implements java.io.Serializable {
    
    private int numero, ref_article, quantite;
    private double prix_unitaire, montant;
    private String famille, nom;

    public int getNumero() {
        return numero;
    }

    public int getRef_article() {
        return ref_article;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getPrix_unitaire() {
        return prix_unitaire;
    }

    public double getMontant() {
        return montant;
    }

    public String getFamille() {
        return famille;
    }

    public String getNom() {
        return nom;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setRef_article(int ref_article) {
        this.ref_article = ref_article;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPrix_unitaire(double prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
    


   
}
