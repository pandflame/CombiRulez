
package Data.ObjectPackage;

public class User {


  // Un oggetto di tipo User identifica un utente del sistema, questo deve avere un nome e cognome, username, password, codice fiscale, indirizzo, città e così via.

  private String userCode;
  private String userFirstName;
  private String userLastName;
  private String userPassword;
  private int userRole;


  // Costruttore vuoto

  public User() {
    
  }

  // Costruttore della classe

  public User(String userCode, String userFirstName, String userLastName, String userPassword, int userRole) {
    this.userCode = userCode;
    this.userFirstName = userFirstName;
    this.userLastName = userLastName;
    this.userPassword = userPassword;
    this.userRole = userRole;
  }


  // Metodi Get

  public String getUserCode() {
    return this.userCode;
  }
  
  public String getUserFirstName() {
    return this.userFirstName;
  }

  public String getUserLastName() {
    return this.userLastName;
  }

  public String getUserPassword() {
    return this.userPassword;
  }

  public int getUserRole() {
    return this.userRole;
  }


  // Metodi Set

  public void setUserCode(String userCode) {
    this.userCode = userCode;
  }
  
  public void setUserFirstName(String userFirstName) {
    this.userFirstName = userFirstName;
  }

  public void setUserLastName(String userLastName) {
    this.userLastName = userLastName;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public void setUserRole(int userRole) {
    this.userRole = userRole;
  }

}