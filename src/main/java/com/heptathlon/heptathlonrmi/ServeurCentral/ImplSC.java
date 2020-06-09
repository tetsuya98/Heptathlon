package com.heptathlon.heptathlonrmi.ServeurCentral;

import com.heptathlon.heptathlonrmi.Objets.Article;
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

public class ImplSC implements HelloSC{  
    
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
    public void addArticle(String famille, String nom, double prix, int stock) throws Exception {
        String req = "INSERT INTO heptathlon.Articles (famille, nom, prix_unitaire, nb_stock) VALUES ('"
                +famille+"','"+nom+"',"+prix+","+stock+");"; 
        exeUpdate(req);
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
    public void updateArticle(String ref, String prix) throws Exception {
        String req = "UPDATE Articles SET prix_unitaire ="+prix+" WHERE reference="+ref+";";
        exeUpdate(req);
    }
    
    @Override
    public void deleteArticle(String ref) throws Exception {
        String req = "DELETE FROM heptathlon.Articles WHERE reference="+ref+";"; 
        exeUpdate(req);
    }
    
}    