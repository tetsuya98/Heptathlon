/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heptathlon.heptathlonrmi.ServeurMagasin;

import com.heptathlon.heptathlonrmi.Objets.Article;
import com.heptathlon.heptathlonrmi.Objets.Facture;
import com.heptathlon.heptathlonrmi.ServeurMagasin.HelloSM;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josselin
 */
public class ImplSM implements HelloSM{
  
    String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
    String DB_URL = "jdbc:mysql://127.0.0.1:8889/heptathlon?characterEncoding=utf8&useUnicode=true";
    String USER = "root"; 
    String PASS = "root"; 
    Connection conn = null; 
    Statement stmt = null;    
    
    private ResultSet exeQuery(String requete) {
        try { 
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement(); 
            return stmt.executeQuery(requete); 
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }     
    }
    
    @Override
    public List<Article> getArticles() throws Exception {   
        List<Article> list = new ArrayList<>();
        try (ResultSet rs = exeQuery("SELECT * FROM Articles;")) {
            while(rs.next()) {
                Article article = new Article();
                article.setReference(rs.getInt("reference"));
                article.setFamille(rs.getString("famille"));
                article.setNom(rs.getString("nom"));
                article.setPrix_unitaire(rs.getDouble("prix_unitaire"));
                article.setNb_stock(rs.getInt("nb_stock"));
                list.add(article);
            } 
        } 
        return list;       
    }
}