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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        try (ResultSet rs = exeQuery("SELECT * FROM Factures;")) {
            while(rs.next()) {
                Facture facture = new Facture();
                facture.setNumero(rs.getInt("numero"));
                facture.setRef_facture(rs.getInt("ref_facture"));
                facture.setRef_article(rs.getInt("ref_article"));
                facture.setFamille(rs.getString("famille"));
                facture.setNom(rs.getString("nom"));
                facture.setQuantite(rs.getInt("quantite"));
                facture.setPrix_unitaire(rs.getDouble("prix_unitaire"));
                facture.setMontant(rs.getDouble("montant"));
                facture.setClient(rs.getString("client"));
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
    public void addFacture(String ref, String ref_article, String famille, String nom, String quantite, String prix, String montant, String client) throws Exception {
        //int num = getNumero("facture") + 1;
        String req = "INSERT INTO Factures (ref_facture, ref_article, famille, nom, quantite, prix_unitaire, montant, client, payer) VALUES "
                                    + "("+ref+","+ref_article+",'"+famille+"','"+nom
                                    +"',"+quantite+","+prix+","+montant+",'"+client+"',"+0+");";
        exeUpdate(req);
    }

    @Override
    public List<Facture> getFacture(int ref) throws Exception {
        System.out.println("ici");
        List<Facture> list = new ArrayList<>(); 
        String requete = "SELECT * FROM Factures WHERE ref_facture ="+ref+";";
        System.out.println(requete);
        try (ResultSet rs = exeQuery(requete)) {
            while(rs.next()) {
                Facture facture = new Facture();
                facture.setNumero(rs.getInt("numero"));
                facture.setRef_facture(rs.getInt("ref_facture"));
                facture.setRef_article(rs.getInt("ref_article"));
                facture.setFamille(rs.getString("famille"));
                facture.setNom(rs.getString("nom"));
                facture.setQuantite(rs.getInt("quantite"));
                facture.setPrix_unitaire(rs.getDouble("prix_unitaire"));
                facture.setMontant(rs.getDouble("montant"));
                facture.setClient(rs.getString("client"));
                list.add(facture);
            } 
        } 
        return list;        
    }
    
    @Override
    public boolean isPaid(String ref) throws Exception {
        String requete = "SELECT payer FROM Factures WHERE ref_facture ="+ref+";";
        try (ResultSet rs = exeQuery(requete)) {
            if (rs.first()){                    
                if (rs.getInt("payer") == 0)
                    return false;
                else
                    return true;
            }
        }
        return false;
    }
    
    @Override
    public List<Article> getArticle(String champs, String value) throws Exception {
        String requete = "";
        List<Article> list = new ArrayList<>(); 
        if ("ref".equals(champs))
            requete = "SELECT * FROM Articles WHERE reference = "+value+";";
        if ("famille".equals(champs))
            requete = "SELECT * FROM Articles WHERE famille = '"+value+"';";
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
    public int getNumero(String table) throws Exception {
        String req = "";
        if ("facture".equals(table))
            req = "SELECT MAX(ref_facture) as num FROM Factures;";
        if ("article".equals(table))
            req = "SELECT MAX(numero) as num FROM Articles;";
        int num = 1;
        try {
            ResultSet res = exeQuery(req);
            if (res.first()){
                num = res.getInt("num");
            } 
        } catch (Exception e){
            e.printStackTrace();
        }
        return num;   
    }

    
    

    @Override
    public void addPaiement(String ref, String montant, String date, String moyen) throws Exception {
        String req ="INSERT INTO Paiements (num_facture, montant, date, mode) VALUES ("+ref+","+montant+",'"+date+"','"+moyen+"');";
        exeUpdate(req);     
    }

    @Override
    public double getCA(String debut, String fin) throws Exception {
        String req = "SELECT SUM(montant) FROM Paiements WHERE date BETWEEN '"+debut+"' AND '"+fin+"';";
        double CA = 0 ;
        try (ResultSet rs = exeQuery(req)) {
            if (rs.first()){                    
                CA = rs.getDouble("SUM(montant)");
            }
        }
        return CA;
    }
    

    @Override
    public void updateFacture(String ref) throws Exception {
        String req = "UPDATE Factures SET payer="+1+" WHERE ref_facture="+ref+";";
        exeUpdate(req);
    }
    
}
