/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heptathlon.heptathlonrmi;

import com.heptathlon.heptathlonrmi.Objets.Article;
import com.heptathlon.heptathlonrmi.Objets.Facture;
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
public class ImplC implements HelloC{
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
    
    private void exeUpdate(String requete) {
        try { 
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement(); 
            int x = stmt.executeUpdate(requete); 
            
        } catch (SQLException e) {
            e.printStackTrace();
        }     
    }
    
    @Override
    public List<Article> getArticles() throws Exception {   
        List<Article> list = new ArrayList<>();
        try (ResultSet rs = exeQuery("SELECT * FROM heptathlon.Articles;")) {
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
    
    @Override
    public List<Facture> getFactures() throws Exception {   
        List<Facture> list = new ArrayList<>();
        try (ResultSet rs = exeQuery("SELECT * FROM heptathlon.Articles;")) {
            while(rs.next()) {
                Facture facture = new Facture();
                facture.setNumero(rs.getInt("numero"));
                facture.setRef_article(rs.getInt("ref_article"));
                facture.setFamille(rs.getString("famille"));
                facture.setNom(rs.getString("nom"));
                facture.setQuantite(rs.getInt("quantite"));
                facture.setPrix_unitaire(rs.getDouble("prix_unitaire"));
                facture.setMontant(rs.getDouble("montant"));
                list.add(facture);
            } 
        } 
        return list;       
    }
    
    @Override
    public void updateArticles(int stock, String ref) throws Exception {
        String req = "UPDATE Articles SET nb_stock="+stock+" WHERE reference="+ref+";";
        exeUpdate(req);
    }

    @Override
    public Article getArticlePourFacture(String requete) throws Exception {
        
        Article article = new Article();   
        try { 
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(); 

            try (ResultSet rs = stmt.executeQuery(requete)) {
                    if (rs.first()){                    
                        article.setReference(rs.getInt("reference"));
                        article.setFamille(rs.getString("famille"));
                        article.setNom(rs.getString("nom"));
                        article.setPrix_unitaire(rs.getDouble("prix_unitaire"));
                        article.setNb_stock(rs.getInt("nb_stock"));
                    } 
            } 
            stmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return article;   
    }
  
    @Override
    public int getNumeroFacture(String requete) throws Exception {
        int numero = 0;
        try { 
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                numero = stmt.executeUpdate(requete);      
            } catch (Exception e){
                e.printStackTrace();
            }
        
        return numero;
    }  

    @Override
    public void addFacture(String requete) throws Exception {
        exeUpdate(requete);
    }

    @Override
    public List<Facture> getFacture(int ref) throws Exception {
        List<Facture> list = new ArrayList<>(); 
        String requete = "SELECT * FROM Factures WHERE numero ="+ref+";";

        try (ResultSet rs = exeQuery(requete)) {
            while(rs.next()) {
                Facture facture = new Facture();
                facture.setNumero(rs.getInt("numero"));
                facture.setRef_article(rs.getInt("ref_article"));
                facture.setFamille(rs.getString("famille"));
                facture.setNom(rs.getString("nom"));
                facture.setQuantite(rs.getInt("quantite"));
                facture.setPrix_unitaire(rs.getDouble("prix_unitaire"));
                facture.setMontant(rs.getDouble("montant"));
                list.add(facture);
            } 
        } 
        return list;        
    }
    
    @Override
    public List<Article> getArticle(String champs, String value) throws Exception {
        String requete = "";
        List<Article> list = new ArrayList<>(); 
        if ("ref".equals(champs))
            requete = "SELECT * FROM Articles WHERE reference = "+value+";";
        if ("famille".equals(champs))
            requete = "SELECT * FROM Factures WHERE famille = "+value+";";
        try (ResultSet rs = exeQuery(requete)) {
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

    @Override
    public int getLastFacture(String requete) throws Exception {
        int num_last_facture =0;
        try {
            Class.forName("com.mysql.jdbc.Driver"); 
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(); 
            
        } catch (Exception e){
            e.printStackTrace();
        }

        try (ResultSet rs = stmt.executeQuery(requete)) {
            if (rs.first()){
                num_last_facture = rs.getInt("MAX(numero)");
            } 
            rs.close();
        } 
        stmt.close();

        return num_last_facture;   
    }

    @Override
    public void addPaiement(String requete) throws Exception {
        exeUpdate(requete);     
    }

    @Override
    public double getCA(String requete) throws Exception {
       double CA = 0 ;
        try {
            Class.forName("com.mysql.jdbc.Driver"); 
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(); 
            try (ResultSet rs = stmt.executeQuery(requete)) {
                if (rs.first()){                    
                    CA = rs.getDouble("SUM(montant)");
                }
                rs.close();
            } 
            
        } catch (SQLException e) {
            e.printStackTrace();
        }      
        stmt.close();
        
        return CA;
    }
    
}
