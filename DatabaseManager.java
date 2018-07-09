
package Data.FilePackage;

import Data.ObjectPackage.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class DatabaseManager {

  // Costruttore della classe
  public DatabaseManager() {
  }

  // Metodi della classe DatabaseManager


  // 1 - Questo metodo inserisce nella tabella Order un oggetto, serve al responsabile del negozio per fare un log dei suoi ordini.

  public int insertOrder(Order parameterOrder) throws SQLException {

    // Mi connetto al database
    try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/Elio")) {

      // Faccio l'inserimento
      try (PreparedStatement pst = con.prepareStatement("INSERT INTO listaOrdini VALUES(?,?,?,?,?,?)")) {

        int orderLength = parameterOrder.getOrderItemList().size();
        Date orderDate = Date.valueOf(parameterOrder.getOrderDate());
        int esito = 0;

        for (int i = 0; i < orderLength; i++) {

          // Aggiungo i parametri

          pst.clearParameters();
          pst.setInt(1, parameterOrder.getOrderCode());
          pst.setDate(2, orderDate);
          pst.setString(3, parameterOrder.getOrderSource());
          ItemListComponent item = new ItemListComponent();
          item = parameterOrder.getOrderItemList().get(i);
          String orderedItem = item.getItemType();
          pst.setString(4, orderedItem);
          int itemQuantity = item.getItemQuantity();
          pst.setInt(5, itemQuantity);
          pst.setDouble(6, parameterOrder.getOrderCost());

          // Eseguo il comando
          esito += pst.executeUpdate();

        }

        if (esito > 0) {
          esito = 1;
          return esito;
        } else {
          return 0;
        }

      } catch (SQLException e) {

        System.out.println( "Errore durante inserimento: " + e.getMessage());
        return 0;

      } finally {
        con.close();
      }
      
    } catch (SQLException e) {

      System.out.println( "Errore durante connessione: " + e.getMessage());
      return 0;
    
    }

  }


  // 2 - Questo metodo inserisce nella tabella Item un oggetto, serve alla segreteria per aggiungere tipi di articolo.

  public int insertItem(Item parameterItem) throws SQLException {

    // Mi connetto al database
    try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/Elio")) {
      
      // Faccio l'inserimento
      try (PreparedStatement pst = con.prepareStatement("INSERT INTO Item VALUES(?, ?, ?, ?)")) {

        // Ottengo i parametri da inserire
        pst.clearParameters();
        pst.setString(1, parameterItem.getName());
        pst.setString(2, parameterItem.getDescription());
        pst.setString(3, parameterItem.getSport());
        pst.setString(4, parameterItem.getMaterial());

        // Svolgo l'inserimento
        int esito = pst.executeUpdate();

        // Ritorno il numero di righe inserite, dovrebbe essere sempre 1
        return esito;

      } catch (SQLException e) {

        System.out.println( "Errore durante estrazione dati: " + e.getMessage());
        return 0;
        
      } finally {
        con.close();
      }

    } catch (SQLException e) {

      System.out.println("Errore durante connessione: " + e.getMessage());
      return 0;
    
    }

  }


  // 3 - Ritorna tutti gli itemType degli ordini, serve al responsabile del negozio.

  public List<String[]> viewOrderHistory() {

    // Mi connetto al database
    try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/Elio")) {

      try (PreparedStatement pst = con.prepareStatement("SELECT * FROM listaOrdini")) {
        
        // Inizializzo l'insieme dei risultati ed eseguo il comando
        ResultSet result = null;
        result = pst.executeQuery();

        // Inizializzo una lista di stringhe, mi serve per salvarmi i risultati
        List<String[]> resultList = new ArrayList<String[]>();
        String[] resultArray = new String[2];

        int i = 0;
        while (result.next()) {
          for (int j = 3; j < 5; j++) {
            // 4 è la colonna degli itemType
            String temp = result.getString(j);
            resultArray[j-3] = temp;
          }
          resultList.add(i, resultArray);
          i++;
        }
        
        return resultList;

      } catch (SQLException e) {

        return null;

      } finally {
        con.close();
      }

    } catch (SQLException e) {
      return null;
    }

  }


  // 4 - Questo metodo serve per visualizzare tutti i movimenti del magazzino, lo utilizza la segreteria.

  public List<String> viewMovementHistory(int choice) {


    try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/Elio")) {


      // Il parametro della scelta serve per capire se voglio vedere i movimenti di entrata o uscita dal magazzino. La scelta 1 sta per vedere quelli in uscita, mentre tutto il resto vale per i movimenti in entrata.

      if (choice == 1) {
      
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM UscitaMagazzino")) {
          
          // Eseguo il comando e salvo i risultati
          ResultSet rs = null;
          rs = pst.executeQuery();

          // Passo tutto in una lista di stringhe
          List<String> resultList = new ArrayList<String>();
          int i = 0;
          while (rs.next()) {
            resultList.add(i, rs.getString(2));
            i++;
          }

          return resultList;


        } catch (SQLException e) {

          System.out.println("Errore in DatabaseManager:");
          System.out.println(e.getMessage());
          return null;

        } finally {
          con.close();
        }

      } else {

        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM EntrataMagazzino")) {

          // Eseguo il comando e salvo i risultati
          ResultSet rs = null;
          rs = pst.executeQuery();

          // Salvo tutto il ResultSet in una lista di stringhe
          List<String> resultList = new ArrayList<String>();
          int i = 0;
          while (rs.next()) {
            resultList.add(i, rs.getString(2));
            i++;
          }

          return resultList;

          
        } catch (SQLException e) {

          System.out.println("Errore in DatabaseManager:");
          System.out.println(e.getMessage());
          return null;

        } finally {
          con.close();
        }

      }

    } catch (SQLException e) {

      System.out.println("Errore in DatabaseManager:");
      System.out.println(e.getMessage());
      return null;

    }

  }


  // 5 - Questo metodo serve per spostare un articolo da un settore ad un altro del magazzino.

  public int changeWarehouseSector(WarehouseItem wItem, int newSection) {

    try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/Elio")) {

      try (PreparedStatement pst = con.prepareStatement("UPDATE warehouse SET itemwarehousesector = ? WHERE itemcode = ?")){
        
        // Aggiungo i parametri
        pst.clearParameters();
        pst.setInt(1, newSection);
        pst.setString(2, wItem.getWarehouseItemCode());

        // Eseguo il comando
        int esito = pst.executeUpdate();
        return esito;

      } catch (SQLException e) {

        System.out.println("Errore in DatabaseManager:");
        System.out.println(e.getMessage());
        return 0;

      } finally {
        con.close();
      }
      
    } catch (SQLException e) {
      System.out.println("Errore in DatabaseManager:");
      System.out.println(e.getMessage());
      return 0;
    }

  }


  // 6 - Questo metodo serve per rimuovere articoli dal magazzino, lo uso quando ho un ordine e devo rimuovere dal magazzino. Aggiorna automaticamente quando un responabile fa un ordine che va a buon fine.

  public int removeFromWarehouse() {
    return 0;
  }


  // 7 - Questo metodo serve per aggiungere articoli in magazzino, lo uso quando ho un ingresso in magazzino.

  public int insertIntoWarehouse() {
    return 0;
  }


  // 8 - Questo metodo serve per eseguire l'autenticazione, viene chiamato appena si apre l'applicazione. Ritorna una stringa che verrà processata in authentication Manager.

  public User getCredentials(String userCode) throws SQLException {
    
    try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/Elio")) {
      
      try (PreparedStatement pst = con.prepareStatement("SELECT * FROM UserList WHERE userCode = ?")) {
        
        // Preparo la query
        pst.clearParameters();
        pst.setString(1, userCode);

        // Eseguo il comando
        ResultSet rs = null;
        rs = pst.executeQuery();

        // Preparo il risultato
        User userInfo = new User();

        while(rs.next()) {
          userInfo.setUserCode(rs.getString(1));
          userInfo.setUserFirstName(rs.getString(2));
          userInfo.setUserLastName(rs.getString(3));
          userInfo.setUserPassword(rs.getString(4));
          userInfo.setUserRole(rs.getInt(5));
        }

        return userInfo;

      } catch (SQLException e) {
        System.out.println("Errore:");
        System.out.println(e.getMessage());
        return null;
      } finally {
        con.close();
      }

    } catch (SQLException e) {
      System.out.println("Errore:");
      System.out.println(e.getMessage());
      return null;
    }

  }


  // 9 - Ritorna il numero dell'ordine che dovrò fare. Esegue una ricerca sul log degli ordini e ritorna l'ultimo utilizzato.

  public int getLastOrderNumber() {
    
    try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/Elio")) {

      try (PreparedStatement pst = con.prepareStatement("SELECT orderCode FROM listaOrdini ORDER BY orderCode DESC LIMIT 1")) {
        
        // Eseguo il comando
        ResultSet rs = pst.executeQuery();
        int lastOrderCode = 0;
   
        // Salvo il risultato
        while (rs.next()) {
          lastOrderCode = rs.getInt(1);
        }

        return lastOrderCode;

      } catch (Exception e) {

        System.out.println("Errore:");
        System.out.println(e.getMessage());
        return 0;

      } finally {
        con.close();
      }
      
    } catch (Exception e) {

      System.out.println("Errore:");
      System.out.println(e.getMessage());
      return 0;
      
    }

  }

}