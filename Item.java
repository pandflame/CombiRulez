
package ObjectPackage;


public class Item {


  // Un oggetto di tipo Item rappresenta l'articolo sportivo in sè, ha un nome, una descrizione, lo sport e i materiali utilizzati per produrlo.

  private String itemName;
  private String itemDescription;
  private String itemSport;
  private String itemMaterial;


  // Costruttore semplice

  public Item(String itemName) {
    this.itemName = itemName;
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

}