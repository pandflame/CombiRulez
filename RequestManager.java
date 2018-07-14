
package Data.FilePackage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Data.ObjectPackage.*;
import jdk.nashorn.internal.ir.ReturnNode;


public class RequestManager {
  
  // Costruttore vuoto
  public RequestManager() {
  }

  // Inserimento di una tupla nella propria tabella

  public int dbAction(Object parameterObject) throws IOException, SQLException {

    // Caso di parametro Item
    if (parameterObject instanceof Item) {
      int esito = 0;
      Item parameterItem = (Item)parameterObject;
      DatabaseManager dbManager = new DatabaseManager();
      try {
        esito = dbManager.insertItem(parameterItem);
        return esito;
      } catch(SQLException e) {
        return 0;
      }

    }

    // Caso di parametro Order
    if (parameterObject instanceof Order) {

      int esitoPrep = 0;
      Order parameterOrder = (Order)parameterObject;
      DatabaseManager dbManager = new DatabaseManager();

      try {

        // Faccio il controllo per vedere se ci sono abbastanza elementi in magazzino
        esitoPrep = dbManager.removeFromWarehouse(parameterOrder.getOrderItemList());

        // Se esitoPrep è positivo, inserisco l'ordine
        if (esitoPrep == 1) {
          
          int esitoOrdine = dbManager.insertOrder(parameterOrder);
          int esitoInserimentoOrdine = dbManager.insertWarehouseExit(parameterOrder);

            if ((esitoOrdine + esitoInserimentoOrdine) == 2) {
              return 1;
            } else {
              return 0;
            }

        } else {
          return 0;
        }

      } catch (SQLException e) {
        return 0;
      }

    }

    return 0;

  }


  // 1 = MANAGER, 2 = SEGRETERIA, 3 = MAGAZZINIERE

  public List<String[]> dbView(int user) throws SQLException {

    // Se l'utente è di tipo 1, allora è un manager del negozio e la cronologia che può vedere è quella degl ordini eseguiti
    if (user == 1) {
      List<String[]> result = new ArrayList<String[]>();
      DatabaseManager dbManager = new DatabaseManager();

      try {
        result = dbManager.viewOrderHistory();
        return result;
      } catch (Exception e) {
        return null;
      }
    }

    // Se l'utente è di tipo 2, allora fa parte della segreteria e la cronologia che può vedere è quella dei movimenti del magazzino
    if (user == 2) {
      List<String[]> result = new ArrayList<String[]>();
      DatabaseManager dbManager = new DatabaseManager();

      try {
        result = dbManager.viewMovementHistory(2); // Cambiare in qualche modo, cosicchè si riesca con un solo parametro a decidere quale voglio vedere, entrata o uscita
        return result;
      } catch (Exception e) {
        return null;
      }
      
    }

    // Se l'utente è di tipo 3, allora fa parte dello staff del magazzino e può vedere la lista di oggetti nel magazzino. Questo metodo serve principalmente per scegliere un oggetto nel database da spostare.
    if (user == 3) {
      List<String[]> result = new ArrayList<String[]>();
      DatabaseManager dbManager = new DatabaseManager();

      try {
        result = dbManager.viewWarehouse();
        return result;
      } catch (Exception e) {
        return null;
      }
    }

    return null;

  }
  

  // Quando il cambiamento del settore va a buon fine, ritorno 1 
  public int changeItemSector(WarehouseItem wItem, int newSection) {

    DatabaseManager dbManager = new DatabaseManager();

    try {
      int esito = dbManager.changeWarehouseSector(wItem, newSection);
      return esito;
    } catch (Exception e) {
      return 0;
    }

  }

}