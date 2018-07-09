
package Data.ObjectPackage;
import java.time.LocalDate;
import java.util.List;


public class Order {


  // Un oggetto di tipo Ordine è formato da un codice che lo identifica univocamente, dalla data dell'ordine, dalla lista di oggetti che fanno parte dell'ordine (con quantità), dal prezzo totale dell'ordine e dal negozio che l'ha effettuato.

  private int orderCode;
  private LocalDate orderDate;
  private List<ItemListComponent> itemList;
  private double totalCost;
  private String orderSource;


  // Costruttore vuoto della classe Ordine

  public Order() {
    this.orderCode = 0;
    this.orderDate = null;
    this.itemList = null;
    this.totalCost = 0.;
    this.orderSource = null;
  }


  // Costruttore della classe Order

  public Order(int orderCode, LocalDate orderDate, List<ItemListComponent> itemList, double totalCost, String orderSource) {
    this.orderCode = orderCode;
    this.orderDate = orderDate;
    this.itemList = itemList;
    this.totalCost = totalCost;
    this.orderSource = orderSource;
  }


  // Metodi Get

  public int getOrderCode() {
    return orderCode;
  }

  public LocalDate getOrderDate() {
    return orderDate;
  }

  public List<ItemListComponent> getOrderItemList() {
    return itemList;
  }

  public double getOrderCost() {
    return totalCost;
  }

  public String getOrderSource() {
    return orderSource;
  }


  // Metodi Set

  public void setOrderCode(int orderCode) {
    this.orderCode = orderCode;
  }

  public void setOrderDate(LocalDate orderDate) {
    this.orderDate = orderDate;
  }

  public void setOrderItemList(List<ItemListComponent> orderItemList) {
    this.itemList = orderItemList;
  }

  public void setOrderCost(double orderCost) {
    this.totalCost = orderCost;
  }

  public void setOrderSource(String orderSource) {
    this.orderSource = orderSource;
  }

}
