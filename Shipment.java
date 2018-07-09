
package Data.ObjectPackage;
import java.time.LocalDate;
import java.util.List;


public class Shipment {

  // Un oggetto di tipo Shipment, rappresenta un'uscita dal magazzino. Ciò viene identificato dalla data e dal numero di bolla, tutti gli articoli usciti, il negozio che li ha ordinati e lo spedizioniere che li ritira.
  
  private int shipmentNumber;
  private LocalDate shipmentDate;
  private List<Integer> shipmentItems;
  private Store shipmentStore;
  private String shipmentHandler;


  // Costruttore della clasee

  public Shipment(int shipmentNumber, LocalDate shipmentDate, List<Integer> shipmenItems, Store shipmentStore, String shipmentHandler) {
    this.shipmentNumber = shipmentNumber;
    this.shipmentDate = shipmentDate;
    this.shipmentItems = shipmenItems;
    this.shipmentStore = shipmentStore;
    this.shipmentHandler = shipmentHandler;
  }


  // Metodi Get

  public int getShipmentNumber() {
    return this.shipmentNumber;
  }

  public LocalDate getShipmentDate() {
    return this.shipmentDate;
  }

  public List<Integer> getShipmentItems() {
    return this.shipmentItems;
  }

  public Store getShipmentStore() {
    return this.shipmentStore;
  }

  public String getShipmentHandler() {
    return this.shipmentHandler;
  }

}