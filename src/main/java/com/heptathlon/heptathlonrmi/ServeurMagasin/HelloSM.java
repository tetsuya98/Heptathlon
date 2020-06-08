/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heptathlon.heptathlonrmi.ServeurMagasin;

import com.heptathlon.heptathlonrmi.Objets.Article;
import com.heptathlon.heptathlonrmi.Objets.Facture;
import java.rmi.Remote;
import java.util.List;

/**
 *
 * @author Josselin - FONCTIONS DU SYSTEME INFORMATIQUE
 */
public interface HelloSM extends Remote {   
    public List<Article> getArticles() throws Exception; 
}
