
package Data.FilePackage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Data.ObjectPackage.*;


public class RequestManager {
  
  // Costruttore vuoto
  public RequestManager() {
  }

  // Esempio di metodo che scrive l'istanza di un Object nel file di testo proprio
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
      int esito = 0;
      Order parameterOrder = (Order)parameterObject;
      DatabaseManager dbManager = new DatabaseManager();
      try {
        esito = dbManager.insertOrder(parameterOrder);
        return esito;
      } catch (SQLException e) {
        return 0;
      }
    }

    return 0;

  }


  public List<String> dbView(int user) throws SQLException {

    // Se l'utente è di tipo 1, allora è un manager del negozio e la cronologia che può vedere è quella degl ordini eseguiti
    if (user == 1) {
      List<String> result = new ArrayList<String>();
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
      List<String> result = new ArrayList<String>();
      DatabaseManager dbManager = new DatabaseManager();
      try {
        result = dbManager.viewMovementHistory(2);
        return result;
      } catch (Exception e) {
        return null;
      }
    }

    return null;

  }
  
}