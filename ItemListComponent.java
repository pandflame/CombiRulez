
package ObjectPackage;


public class ItemListComponent {


  // Un oggetto di tipo ItemListComponent è formato dal tipo di articolo e dalla quantità di questo che è stato ordinato. Dunque devo avere un oggetto che sarà formato da una stringa, che rappresenti il tipo di Articolo (univoco), e da un intero che ne simboleggi la quantità.

  private Item itemType;
  private int itemQuantity;


  // Costruttore vuoto

  public ItemListComponent() {
    this.itemType = null;
    this.itemQuantity = 0;
  }


  // Costruttore della classe ItemListComponent

  public ItemListComponent(Item itemType, int itemQuantity) {
    this.itemType = itemType;
    this.itemQuantity = itemQuantity;
  }


  // Metodi Get

  public void getItemType() {
    itemType.getItemInformation();
  }

  public int getItemQuantity() {
    return itemQuantity;
  }

}