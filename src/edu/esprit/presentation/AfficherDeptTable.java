/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.presentation;
import edu.esprit.dao.DepotDAO;
import edu.esprit.entities.Depot;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Ssawssen
 */
public class AfficherDeptTable extends AbstractTableModel{
    List <Depot> depots ;
    String columTab [] ={"Id","Adresse"};

    public AfficherDeptTable(){
        DepotDAO depd= new DepotDAO();
        depots= depd.DisplayAllDepots();
        System.out.println(depots);
    }
    

    public int getRowCount() {
        return depots.size();
        
    }

    public int getColumnCount() {
       return columTab.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return depots.get(rowIndex).getId_dep();
            case 1 :
               return  depots.get(rowIndex).getAdresse_depot();
            
            default:
                return null;
        }
    }
    @Override
    public String getColumnName(int column)
    {
        return columTab[column];
    }
}
