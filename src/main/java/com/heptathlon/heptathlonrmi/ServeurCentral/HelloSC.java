/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heptathlon.heptathlonrmi.ServeurCentral;

import com.heptathlon.heptathlonrmi.Objets.Article;
import java.rmi.Remote;
import java.util.List;

/**
 *
 * @author Josselin
 */
public interface HelloSC extends Remote {
    public void addArticle(String famille, String nom, double prix, int stock) throws Exception;
    public List<Article> getArticles() throws Exception;
    public void updateArticle(String ref, String prix) throws Exception;
    public void deleteArticle(String ref) throws Exception;
}
