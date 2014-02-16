/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.tests;

import edu.esprit.dao.DepotDAO;
import edu.esprit.entities.Depot;

/**
 *
 * @author Karray
 */
public class TestAddDepot {


    public static void main(String[] args) {
        Depot depot = new Depot();
        DepotDAO depotDAO = new DepotDAO();
        depot.setAdresse_depot("La Soukra");
        depotDAO.insertDepot(depot);
    }
}
