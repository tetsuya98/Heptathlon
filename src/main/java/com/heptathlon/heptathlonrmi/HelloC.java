/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heptathlon.heptathlonrmi;

import com.heptathlon.heptathlonrmi.Objets.Article;
import com.heptathlon.heptathlonrmi.Objets.Facture;
import java.util.List;
import java.rmi.Remote;

/**
 *
 * @author Josselin
 */
public interface HelloC extends Remote {
    
    public List<Article> getArticles() throws Exception;
    
    public List<Article> getArticle(String champs, String value) throws Exception;
    
    public void updateArticles(int stock, String ref) throws Exception;
    
    public void addFacture(String ref, String ref_article, String famille, String nom, String quantite, String prix, String montant) throws Exception;
    
    public List<Facture> getFacture(int ref) throws Exception;
    
    public List<Facture> getFactures() throws Exception;
    
    public void addPaiement(String ref, String montant, String date, String moyen) throws Exception;
    
    public int getNumero(String table) throws Exception;
    
    public double getCA(String debut, String fin) throws Exception;

    
    
}
