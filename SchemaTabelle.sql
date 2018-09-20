
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
  orderStatus INTEGER NOT NULL CHECK( orderStatus::VARCHAR SIMILAR TO '[1-2]') DEFAULT (1),
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
  shipmentItemQuantity INTEGER NOT NULL,
  shipmentDate DATE NOT NULL DEFAULT(CURRENT_DATE+1),
  shipmentHandler VARCHAR NOT NULL,
  shipmentDestination VARCHAR NOT NULL,
  PRIMARY KEY(shipmentCode, shipmentItem)
);

CREATE TABLE UserList (
  userCode CHAR(16) PRIMARY KEY,
  userFirstName VARCHAR NOT NULL,
  userLastName VARCHAR NOT NULL,
  userPassword VARCHAR NOT NULL,
  userRole INTEGER NOT NULL CHECK(userRole BETWEEN 1 AND 3)
);

CREATE TABLE Store (
  storeCode CHAR(11) PRIMARY KEY CHECK(storeCode SIMILAR TO '[0-9]{11}'),
  storeName VARCHAR NOT NULL,
  storeAddress VARCHAR NOT NULL,
  storeCity VARCHAR NOT NULL
);