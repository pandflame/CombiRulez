
package Data.ObjectPackage;


public class Item {


  // Un oggetto di tipo Item rappresenta l'articolo sportivo in sè, ha un nome, una descrizione, lo sport e i materiali utilizzati per produrlo.

  private String itemName;
  private String itemDescription;
  private String itemSport;
  private String itemMaterial;


  // Costruttore vuoto

  public Item() {
  }


  // Costruttore più complesso

  public Item(String itemName, String itemDescription, String itemSport, String itemMaterial) {
    this.itemName = itemName;
    this.itemDescription = itemDescription;
    this.itemSport = itemSport;
    this.itemMaterial = itemMaterial;
  }


  // Metodi Get

  public void getItemInformation() {
    System.out.println("\n"+itemName+"\n"+itemDescription+"\n"+itemSport+"\n"+itemMaterial+"\n");
  }

  public String getItemInformationString() {
    return itemName+","+itemDescription+","+itemSport+","+itemMaterial+System.lineSeparator();
  }
  
  public String getName() {
    return this.itemName;
  }

  public String getDescription() {
    return this.itemDescription;
  }

  public String getSport() {
    return this.itemSport;
  }

  public String getMaterial() {
    return this.itemMaterial;
  }


  // Metodi Set

  public void setName(String itemName) {
    this.itemName = itemName;
  }

  public void setDescription(String itemDescription) {
    this.itemDescription = itemDescription;
  }

  public void setSport(String itemSport) {
    this.itemSport = itemSport;
  }

  public void setMaterial(String itemMaterial) {
    this.itemMaterial = itemMaterial;
  }

}