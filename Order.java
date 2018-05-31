
package ObjectPackage;
import java.util.Date;
import java.util.List;


public class Order {


  // Un oggetto di tipo Ordine è formato da un codice che lo identifica univocamente, dalla data dell'ordine, dalla lista di oggetti che fanno parte dell'ordine (con quantità), dal prezzo totale dell'ordine e dal negozio che l'ha effettuato.

  private int orderCode;
  private Date orderDate;
  private List itemList;
  private int totalCost;
  private Store orderSource;


  // Costruttore vuoto della classe Ordine

  public Order() {
    this.orderCode = 0;
    this.orderDate = null;
    this.itemList = null;
    this.totalCost = 0;
    this.orderSource = null;
  }


  // Costruttore della classe Order

  public Order(int orderCode, Date orderDate, List itemList, int totalCost) {
    this.orderCode = orderCode;
    this.orderDate = orderDate;
    this.itemList = itemList;
    this.totalCost = totalCost;
  }


  // Metodi Get

  public int getOrderCode() {
    return orderCode;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public List getOrderItemList() {
    return itemList;
  }

  public int getOrderCost() {
    return totalCost;
  }

  public Store getOrderSource() {
    return orderSource;
  }

}
