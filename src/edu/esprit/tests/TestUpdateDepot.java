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
public class TestUpdateDepot {

    public static void main(String[] args) {

        Depot depot = new Depot();
        DepotDAO depotDAO = new DepotDAO();
        depot = depotDAO.findDepotById(1);
        depot.setAdresse_depot("Allemagne");
        depotDAO.updateDepot(depot);

    }
}
