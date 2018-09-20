
package Data.FilePackage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Data.ObjectPackage.*;


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

        // Faccio il controllo per vedere se ci sono abbastanza elementi in magazzino, ma non faccio alcuna azione sulle tuple.
        esitoPrep = dbManager.checkWarehouse(parameterOrder.getOrderItemList());

        // Se esitoPrep è positivo, inserisco l'ordine nel database degli ordini, ma in stato di sospensione, ossia codice 1.
        if (esitoPrep == 1) {
          
          int esitoOrdine = dbManager.insertOrder(parameterOrder);

            if (esitoOrdine == 1) {
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

    if (parameterObject instanceof Restock) {

      int esitoInsert = 0;
      int esitoWarehouse = 0;
      Restock parameterRestock = (Restock)parameterObject;
      DatabaseManager dbManager = new DatabaseManager();

      try {
        esitoInsert = dbManager.insertRestock(parameterRestock);
      } catch (Exception e) {
        return 0;
      }



      if (esitoInsert == 1) {

        try {
          esitoWarehouse = dbManager.insertIntoWarehouse(parameterRestock.getRestockItems());
        } catch (Exception e) {
          e.printStackTrace();
          return 0;
        }

        if (esitoWarehouse == 1) {
          return 1;
        } else {
          return 0;
        }

      } else {
        return 0;
      }

    }

    return 0;

  }


  // 1 = MANAGER, 2 = SEGRETERIA, 3 = MAGAZZINIERE
  // Choice, invece, serve per distinguere movimenti in entrata o movimenti in uscita, di default è 0, quindi non lo utilizzo, lo utilizzo in segreteria e in magazziniere.

  public List<String[]> dbView(int user, int choice) throws SQLException {

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

        result = dbManager.viewMovementHistory(choice); // Cambiare in qualche modo, cosicchè si riesca con un solo parametro a decidere quale voglio vedere, entrata o uscita
        return result;

      } catch (Exception e) {
        return null;
      }
      
    }

    // Se l'utente è di tipo 3, allora fa parte dello staff del magazzino e può vedere la lista di oggetti nel magazzino.
      // Questo metodo serve principalmente per scegliere un oggetto nel database da spostare.
    if (user == 3) {
      
      DatabaseManager dbManager = new DatabaseManager();

      if (choice == 1) {

        List<String[]> result = new ArrayList<String[]>();
        try {
          result = dbManager.viewWarehouse();
          return result;
        } catch (Exception e) {
          return null;
        }

      } else if (choice == 2) {

        try {

          // Questo metodo fa vedere tutte gli ordini che non sono ancora stati eseguiti, quindi sospesi.
          List<String[]> orderStringList = new ArrayList<String[]>(); // In caso da cambiare solo al codice id dell'ordine. Ho messo le tuple intere in modo da far vedere ai magazzinieri il tipo di ordine.
          orderStringList = dbManager.viewOrderList();

          return orderStringList;

        } catch (Exception e) {
          return null;
        }

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


  // Metodo per evadere gli ordini non ancora fatti

  public int completeOrder(Order evadOrder) {

    // Prendo gli oggetti di tipo ordine a partire dalla loro chiave primaria.
    Order makeOrder = new Order();
    ObjectManager oManager = new ObjectManager();
    DatabaseManager dbManager = new DatabaseManager();

    // In questo punto devo prendere la lista di ordini ed evaderli. Ossia cambiare il loro stato da 1 a 2, rimuovere gli elementi dal magazzino e mettere tutto per iscritto nella tabella magazzino.

    int code = evadOrder.getOrderCode();
    makeOrder = oManager.getOrderInstance(code);

    // Togliere dal magazzino
    int removeResult = dbManager.removeFromWarehouse(makeOrder.getOrderItemList());
    int changeResult = 0;
    int exitResult = 0;

    if (removeResult == 1) {

      // Cambiare lo stato della riga
      changeResult = dbManager.changeOrderStatus(makeOrder);

      // Scrivere l'uscita dal magazzino
      exitResult += dbManager.insertWarehouseExit(makeOrder);

      if (changeResult == 1 && exitResult == 1) {
        return 1;
      } else {
        return 0;
      }
    }

    return 0;

  }

}