
package Data.ObjectPackage;
import java.time.LocalDate;


public class WarehouseItem {


  // Un oggetto di tipo WarehouseItem rappresenta come viene salvato l'articolo sportivo all'interno del magazzino. Mi servono 4 parametri, il tipo di articolo, il codice univoco, il prezzo e la data di produzione. Possibilmente posso estendere l'oggetto di tipo articolo.

  private String itemCode;
  private String itemType;
  private double itemCost;
  private LocalDate itemDate;
  private int itemWarehouseSector;


  // Costruttore vuoto
  public WarehouseItem() {

  }
  

  // Costruttore della classe

  public WarehouseItem(String itemType, String itemCode, double itemCost, LocalDate itemDate, int itemWarehouseSector) {
    this.itemType = itemType;
    this.itemCode = itemCode;
    this.itemCost = itemCost;
    this.itemDate = itemDate;
    this.itemWarehouseSector = itemWarehouseSector;
  }


  // Metodi Get

  public String getWarehouseItemType() {
    return this.itemType;
  }

  public String getWarehouseItemCode() {
    return this.itemCode;
  }

  public double getWarehouseItemCost() {
    return this.itemCost;
  }

  public LocalDate getWarehouseItemDate() {
    return this.itemDate;
  }

  public int getWarehouseItemSector() {
    return this.itemWarehouseSector;
  }


  // Metodi Set

  public void setWarehouseItemType(String wItemType) {
    this.itemType = wItemType;
  }

  public void setWarehouseItemCode(String wItemCode) {
    this.itemCode = wItemCode;
  }

  public void setWarehouseItemCost(double wItemCost) {
    this.itemCost = wItemCost;
  }

  public void setWarehouseItemDate(LocalDate wItemDate) {
    this.itemDate = wItemDate;
  }

  public void setWarehouseItemSector(int wItemSector) {
    this.itemWarehouseSector = wItemSector;
  }

}