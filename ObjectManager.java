
package Data.FilePackage;

import Data.ObjectPackage.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class ObjectManager {

  // Costruttore della classe
  public ObjectManager() {
  }


  // I metodi definiti in ObjectManager mi servono per ricavare dal database tutti gli oggetti che voglio. Ci sarà un metodo per ogni tipo di oggetto. Ogni metodo ritornerà uno e un solo oggetto di quella classe. Ogni metodo funziona nella stessa maniera, come parametro ho il valore della chiave primaria nel database e questa sarà utilizzata per ritornare quella tupla.

  // 1 - Metodo per un oggetto di tipo Item

  public Item getItemInstance(String itemName) {

    try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/Elio")) {
      
      try (PreparedStatement pst = con.prepareStatement("SELECT * FROM Item WHERE itemName = ?")) {
        
        // Passo il parametro
        pst.clearParameters();
        pst.setString(1, itemName);

        // Eseguo il comando
        ResultSet rs = pst.executeQuery();
        Item resultItem = new Item();

        // Costruisco un oggetto di tipo Item con i valori recuperati e lo ritorno
        while (rs.next()) {
          resultItem.setName(rs.getString(1));
          resultItem.setDescription(rs.getString(2));
          resultItem.setSport(rs.getString(3));
          resultItem.setMaterial(rs.getString(4));
        }

        return resultItem;

      } catch (SQLException e) {

        System.out.println("Errore in ObjectManager:");
        System.out.println(e.getMessage());
        return null;
      
      } finally {
        con.close();
      }

    } catch (SQLException e) {

      System.out.println("Errore in ObjectManager:");
      System.out.println(e.getMessage());
      return null;

    }

  }


  // 2 - Metodo per un oggetto di tipo WarehouseItem

  public WarehouseItem getWarehouseItemInstance(String wItemCode) {

    try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/Elio")) {
      
      try (PreparedStatement pst = con.prepareStatement("SELECT * FROM Warehouse WHERE itemCode = ?")) {
        
        // Setto i parametri
        pst.clearParameters();
        pst.setString(1, wItemCode);

        // Eseguo il comando
        ResultSet rs = pst.executeQuery();
        WarehouseItem resultWItem = new WarehouseItem();

        // Costruisco un oggetto di tipo Item con i valori recuperati e lo ritorno
        while (rs.next()) {
          resultWItem.setWarehouseItemCode(rs.getString(1));
          resultWItem.setWarehouseItemType(rs.getString(2));
          resultWItem.setWarehouseItemCost(rs.getDouble(3));
          resultWItem.setWarehouseItemDate(rs.getDate(4).toLocalDate());
          resultWItem.setWarehouseItemSector(rs.getInt(5));
        }

        return resultWItem;


      } catch (SQLException e) {

        System.out.println("Errore in ObjectManager:");
        System.out.println(e.getMessage());
        return null;

      } finally {
        con.close();
      }

    } catch (SQLException e) {

      System.out.println("Errore in ObjectManager:");
      System.out.println(e.getMessage());
      return null;

    }

  }


  // 3 - Metodo per un oggetto di tipo Store

  public Store getStoreInstance(String storeCode) {

    try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/Elio")) {
      
      try (PreparedStatement pst = con.prepareStatement("SELECT * FROM store WHERE storeCode = ?")) {

        // Setto i parametri della query
        pst.clearParameters();
        pst.setString(1, storeCode);

        // Eseguo il comando
        ResultSet rs = pst.executeQuery();
        Store resultStore = new Store();

        // Costruisco il risultato
        while (rs.next()) {
          resultStore.setStoreCode(rs.getString(1));
          resultStore.setStoreName(rs.getString(2));
          resultStore.setStoreAddress(rs.getString(3));
          resultStore.setStoreCity(rs.getString(4));
        }

        return resultStore;
        
      } catch (SQLException e) {

      System.out.println("Errore in ObjectManager:");
      System.out.println(e.getMessage());
      return null;

      } finally {
        con.close();
      }

    } catch (SQLException e) {

      System.out.println("Errore in ObjectManager:");
      System.out.println(e.getMessage());
      return null;

    }

  }


  // 4 - Metodo per un oggetto di tipo Order

  public Order getOrderInstance(int orderCode) {

    try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/Elio")) {

      try (PreparedStatement pst = con.prepareStatement("SELECT * FROM listaOrdini WHERE orderCode = ?")) {

        // Popolo i parametri
        pst.clearParameters();
        pst.setInt(1, orderCode);

        // Prendo il risultato
        ResultSet rs = pst.executeQuery();
        Order resultOrder = new Order();
        List<ItemListComponent> itemList = new ArrayList<ItemListComponent>();
        int i = 0;

        // Costruisco l'ordine risultato
        while (rs.next()) {

          ItemListComponent temp = new ItemListComponent(rs.getString(4), rs.getInt(5));
          itemList.add(i, temp);
          i++;

          if (rs.isLast()) {
            resultOrder.setOrderCode(rs.getInt(1));
            resultOrder.setOrderDate(rs.getDate(2).toLocalDate());
            resultOrder.setOrderSource(rs.getString(3));
            resultOrder.setOrderCost(rs.getDouble(6));
            resultOrder.setOrderStatus(rs.getInt(7));
          }

        }

        resultOrder.setOrderItemList(itemList);

        return resultOrder;

      } catch (SQLException e) {

        System.out.println("Errore in ObjectManager:");
        System.out.println(e.getMessage());
        return null;

      } finally {
        con.close();
      }
      
    } catch (SQLException e) {

      System.out.println("Errore in ObjectManager:");
      System.out.println(e.getMessage());
      return null;

    }

  }

}