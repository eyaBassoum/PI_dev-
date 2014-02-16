/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.dao;

import edu.esprit.entities.Depot;
import edu.esprit.entities.Stock;
import edu.esprit.util.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Karray
 */
public class StockDAO {


    public void insertStock(Stock st){
           // DepotDAO depdao=new DepotDAO();

         String requete = "insert into stock (type_vet_stock,nombre_articles,id_depot) values (?,?,?)";
        try { //dep=depdao.findDepotById(st.getDepot().getId_dep());
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, st.getType_vet_stock());
            ps.setInt(2, st.getNombre_articles());
            ps.setInt(3,st.getDepot().getId_dep() );
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion "+ex.getMessage());
        }
    }

    public void updateStock(Stock st){
        String requete = "update stock set type_vet_stock=?, nombre_articles=?, id_depot=? where numero_stock=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, st.getType_vet_stock());
            ps.setInt(2, st.getNombre_articles());
            ps.setInt(3, st.getDepot().getId_dep());
            ps.setInt(4, st.getNumero_stock());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour "+ex.getMessage());
        }

    }

     public void deleteStock(int num){

          String requete = "delete from stock where numero_stock=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, num);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
    }


      public Stock findStockByNum(int num){

        String requete = "select * from stock where numero_stock=?";

        try{
        PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
        ResultSet resultat = ps.executeQuery();
        DepotDAO depotDAO = new DepotDAO();
        Stock stock = new Stock();
        while (resultat.next()){

            stock.setNumero_stock(resultat.getInt(1));
            stock.setType_vet_stock(resultat.getString(2));
            stock.setNombre_articles(resultat.getInt(3));
            stock.setDepot(depotDAO.findDepotById(resultat.getInt(4)));
        }
        return stock;
        }
        catch(SQLException ex){
            System.out.println("erreur lors du chargement"+ex.getMessage());
            return null;
        }
    }

       public List<Stock> DisplayAllStocks (){


        List<Stock> listedepots = new ArrayList<Stock>();

        String requete = "select * from stock";
        try {
           Statement statement = MyConnection.getInstance()
                   .createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            DepotDAO depotDAO = new DepotDAO();
            while(resultat.next()){
                Stock stock =new Stock();
                stock.setNumero_stock(resultat.getInt(1));
                stock.setType_vet_stock(resultat.getString(2));
                stock.setNombre_articles(resultat.getInt(3));
                stock.setDepot(depotDAO.findDepotById(resultat.getInt(4)));

                listedepots.add(stock);
            }
            return listedepots;
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des stocks "+ex.getMessage());
            return null;
        }
    }

}
