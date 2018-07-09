
package Data.ObjectPackage;


public class Store {


  // Un oggetto di tipo Store rappresenta un negozio. Questo va identificato come un insieme di codice fiscale, nome, indirizzo e città.

  private String storeCity;
  private String storeCode;
  private String storeName;
  private String storeAddress;

  // Costruttore vuoto
  public Store() {
    
  }

  // Costruttore della classe
  public Store(String storeCode, String storeName, String storeAddress, String storeCity) {
    this.storeCode = storeCode;
    this.storeName = storeName;
    this.storeAddress = storeAddress;
    this.storeCity = storeCity;
  }


  // Metodi Get

  public String getStoreCode() {
    return storeCode;
  }

  public String getStoreName() {
    return storeName;
  }

  public String getStoreAddress() {
    return storeAddress;
  }

  public String getStoreCity() {
    return storeCity;
  }
  

  // Metosi Set

  public void setStoreCode(String storeCode) {
    this.storeCode = storeCode;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public void setStoreAddress(String storeAddress) {
    this.storeAddress = storeAddress;
  }

  public void setStoreCity(String storeCity) {
    this.storeCity = storeCity;
  }
  
}