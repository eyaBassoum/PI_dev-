/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.dao;

import edu.esprit.entities.Depot;
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
public class DepotDAO {

     public void insertDepot(Depot d){

        String requete = "insert into depot (adresse_depot) values (?)";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, d.getAdresse_depot());
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion "+ex.getMessage());
        }
    }


    public void updateDepot(Depot d){
        String requete = "update depot set adresse_depot=? where id_depot=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, d.getAdresse_depot());
            ps.setInt(2, d.getId_dep());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour "+ex.getMessage());
        }
    }

    public void deleteDepot(int id){
        String requete = "delete from pays where id=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Pays supprimée");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
    }


    public Depot findDepotById(int id){
    Depot depot = new Depot();
     String requete = "select * from depot where id_depot=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next())
            {
                depot.setId_dep(resultat.getInt(1));
                depot.setAdresse_depot(resultat.getString(2));
            }
            return depot;

        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot "+ex.getMessage());
            return null;
        }
    }

    public Depot findDepotByAdresse(String adr){
    Depot depot = new Depot();
     String requete = "select * from depot where adresse_depot = ?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, adr);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next())
            {
                depot.setId_dep(resultat.getInt(1));
                System.out.println("testttttttt"+depot.getId_dep());
                depot.setAdresse_depot(resultat.getString(2));
                System.out.println(resultat.getString(2));
            }
            return depot;

        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot "+ex.getMessage());
            return null;
        }
    }

    public List<Depot> DisplayAllDepots (){


        List<Depot> listedepots = new ArrayList<Depot>();

        String requete = "select * from depot";
        try {
           Statement statement = MyConnection.getInstance()
                   .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while(resultat.next()){
                Depot depot =new Depot();
                depot.setId_dep(resultat.getInt(1));
                depot.setAdresse_depot(resultat.getString(2));

                listedepots.add(depot);
            }
            return listedepots;
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots "+ex.getMessage());
            return null;
        }
    }

}
