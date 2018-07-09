
package Data.ObjectPackage;
import java.time.LocalDate;
import java.util.List;


public class Restock {


  // Un oggetto di tipo Restock rappresenta un'entrata di una serie di articoli in magazzino. Mi servono un codice interno univoco, la data d'entrata, la lista degli articoli entrati e le posizioni nei vari settori.

  private int restockCode;
  private LocalDate restockDate;
  private List<RestockItem> restockItems;


  // Costruttore della classe
  
  public Restock(int restockCode, LocalDate restockDate, List<RestockItem> restockItems) {
    this.restockCode = restockCode;
    this.restockDate = restockDate;
    this.restockItems = restockItems;
  }


  // Metodi Get

  public int getRestockCode() {
    return this.restockCode;
  }

  public LocalDate getRestockDate() {
    return this.restockDate;
  }

  public List<RestockItem> getRestockItems() {
    return this.restockItems;
  }

}