
package Data.FilePackage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Data.ObjectPackage.*;


public class AuthenticationManager {

  // Costruttore vuoto
  public AuthenticationManager() {
  }


  // Metodo che chiama un azione sul database, sulla tabella UserList. Ritorna un elemento di tipo User se il metodo getCredentials ritorna un User non nullo e il campo della password corrisponde
  
  public User login(String userCode, String userPassword) throws IOException, SQLException {

    // Inizializzo le classi per connettermi al database
    DatabaseManager dbManager = new DatabaseManager();
    User userInfo = new User();
    userInfo = null;

    try {
      userInfo = dbManager.getCredentials(userCode);
    } catch (Exception e) {
      System.out.println("Errore:");
      System.out.println(e.getMessage());
      return null;
    }

    // Controllo che la password sia giusta e che la lista tornata non sia vuota
    if (userInfo != null && userInfo.getUserPassword().equals(userPassword)) {
      return userInfo;
    } else {
      return null;
    }

  }

}