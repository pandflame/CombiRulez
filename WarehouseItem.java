
package ObjectPackage;
import java.util.Date;


public class WarehouseItem {


  // Un oggetto di tipo WarehouseItem rappresenta come viene salvato l'articolo sportivo all'interno del magazzino. Mi servono 4 parametri, il tipo di articolo, il codice univoco, il prezzo e la data di produzione. Possibilmente posso estendere l'oggetto di tipo articolo.

  private Item itemInfo;
  private int itemCode;
  private int itemCost;
  private Date itemDate;


  // Costruttore della classe

  public WarehouseItem(Item itemInfo, int itemCode, int itemCost, Date itemDate) {
    this.itemInfo = itemInfo;
    this.itemCode = itemCode;
    this.itemCost = itemCost;
    this.itemDate = itemDate;
  }


  // Metodi Get

  public void getWarehouseItemInformation() {
    itemInfo.getItemInformation();
  }

  public Item getWarehouseItem() {
    return itemInfo;
  }

  public int getWarehouseItemCode() {
    return itemCode;
  }

  public int getWarehouseItemCost() {
    return itemCost;
  }

  public Date getWarehouseItemDate() {
    return itemDate;
  }


}