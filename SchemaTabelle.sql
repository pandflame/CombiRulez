
CREATE TABLE Item (
  itemName VARCHAR(20) PRIMARY KEY,
  itemDescription VARCHAR NOT NULL,
  itemSport VARCHAR(20) NOT NULL,
  itemMaterial VARCHAR(30)
);

CREATE TABLE Warehouse (
  itemCode CHAR(3) PRIMARY KEY CHECK( itemCode SIMILAR TO '[0-9]{3}' ),
  itemType VARCHAR(20) NOT NULL REFERENCES Item,
  itemCost NUMERIC(6,2) NOT NULL CHECK( itemCost > 0 ),
  itemDate DATE NOT NULL DEFAULT (CURRENT_DATE),
  itemWarehouseSector INTEGER NOT NULL CHECK ( itemWarehouseSector::VARCHAR SIMILAR TO '[1-5]')
);

CREATE TABLE ListaOrdini (
  orderCode INTEGER CHECK( orderCode > 0 ),
  orderDate DATE NOT NULL DEFAULT( CURRENT_DATE ),
  orderSource VARCHAR NOT NULL,
  orderItem VARCHAR NOT NULL,
  orderQuantity INTEGER NOT NULL CHECK( orderQuantity > 0 ),
  orderCost NUMERIC(6,2) NOT NULL CHECK ( orderCost > 0 ),
  PRIMARY KEY(orderCode, orderItem)
);

CREATE TABLE EntrataMagazzino (
  restockCode INTEGER CHECK( restockCode > 0 ),
  restockItem VARCHAR,
  restockItemSector INTEGER NOT NULL CHECK( restockItemSector::VARCHAR SIMILAR TO '[1-5]'),
  restockDate DATE NOT NULL DEFAULT(CURRENT_DATE),
  PRIMARY KEY(restockCode, restockItem)
);

CREATE TABLE UscitaMagazzino (
  shipmentCode INTEGER CHECK( shipmentCode > 0 ),
  shipmentItem VARCHAR,
  shipmentDate DATE NOT NULL DEFAULT(CURRENT_DATE),
  shipmentHandler VARCHAR NOT NULL,
  PRIMARY KEY(shipmentCode, shipmentItem)
);