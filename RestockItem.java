
package Data.ObjectPackage;


public class RestockItem {

  private String itemName;
  private int itemWarehouseSector;
  private String itemCode;


  // Costruttore della classe

  public RestockItem(Item item, int itemWarehouseSector, String itemCode) {
    this.itemName = item.getName();
    this.itemWarehouseSector = warehouseSector;
    this.itemCode = itemCode;
  }


  // Metodi Get

  public String getItemName() {
    return this.itemName;
  }

  public int getitemWarehouseSector() {
    return this.itemWarehouseSector;
  }

  public String getItemCode() {
    return this.itemCode;
  }


  // Metodi Set
  
  public void setItemCode(int itemCode) {
    this.itemCode = itemCode;
  }

}
