CREATE DATABASE ThogaKade;

USE ThogaKade;

CREATE TABLE Customer(
                         id VARCHAR(6) NOT NULL,
                         name VARCHAR(30),
                         address VARCHAR(30),
                         salary DECIMAL(10,2),
                         CONSTRAINT PRIMARY KEY (id)
);

CREATE TABLE Orders(
                       id VARCHAR(6) NOT NULL,
                       date DATE,
                       customerId VARCHAR(6) NOT NULL,
                       CONSTRAINT PRIMARY KEY (id),
                       CONSTRAINT FOREIGN KEY(customerId) REFERENCES Customer(id) on Delete Cascade on Update Cascade
);

CREATE TABLE Item(
                     code VARCHAR(6) NOT NULL,
                     description VARCHAR(50),
                     unitPrice DECIMAL(8,2),
                     qtyOnHand INT(5),
                     CONSTRAINT PRIMARY KEY (code)
);

CREATE TABLE OrderDetail(
                            orderId VARCHAR(6) NOT NULL,
                            itemCode VARCHAR(6) NOT NULL,
                            qty INT(11),
                            unitPrice DECIMAL(8,2),
                            CONSTRAINT PRIMARY KEY (orderId,itemCode),
                            CONSTRAINT FOREIGN KEY (orderId) REFERENCES Orders(id) on Delete Cascade on Update Cascade,
                            CONSTRAINT FOREIGN KEY (itemCode) REFERENCES Item(code) on Delete Cascade on Update Cascade
);

//--------------------Customer------------------------------------------

INSERT INTO Customer VALUES('C001','Danapala','Panadura',54000);
INSERT INTO Customer VALUES('C002','Gunapala','Matara',44000);
INSERT INTO Customer VALUES('C003','Somapala','Galle',82000);
INSERT INTO Customer VALUES('C004','Siripala','Galle',24000);
INSERT INTO Customer VALUES('C005','Jinadasa','Panadura',94000);
INSERT INTO Customer VALUES('C006','Sepalika','Kalutara',58000);
INSERT INTO Customer VALUES('C007','Jinasena','Ambalangoda',51000);
INSERT INTO Customer VALUES('C008','Somadasa','Baddegama',34000);
INSERT INTO Customer VALUES('C009','Danasiri','Moratuwa',29000);
INSERT INTO Customer VALUES('C010','Somasiri','Kandy',64000);


//--------------------Item----------------------------------------------

INSERT INTO Item VALUES('P001','Keerisamba Retail',105.00,3000);
INSERT INTO Item VALUES('P002','Keerisamba 5Kg ',525.00,200);
INSERT INTO Item VALUES('P003','Keerisamba 10Kg',995.00,36);
INSERT INTO Item VALUES('P004','Keerisamba 50Kg',4100.00,36);
INSERT INTO Item VALUES('P005','Red Raw Rice',60.00,6000);
INSERT INTO Item VALUES('P006','Red Raw Rice 10Kg Pack',560.00,300);
INSERT INTO Item VALUES('P007','Red Raw Rice 50Kg Pack',5230.00,80);
INSERT INTO Item VALUES('P008','White Raw Rice 5Kg Pack',275.00,130);
INSERT INTO Item VALUES('P009','White Raw Rice 50Kg Pack',2600.00,13);
INSERT INTO Item VALUES('P010','Wattana Dhal 500g',90.00,83);
INSERT INTO Item VALUES('P011','Wattana Dhal 1Kg',170.00,40);
INSERT INTO Item VALUES('P012','Mysoor Dhal 500g',90.00,89);
INSERT INTO Item VALUES('P013','Mysoor Dhal 1Kg',180.00,59);
INSERT INTO Item VALUES('P014','Orient Green Gram 500g',118.00,39);
INSERT INTO Item VALUES('P015','Orient Green Gram 1Kg',220.00,12);
INSERT INTO Item VALUES('P016','Anchor F/C Milk powder 450g',220.00,93);
INSERT INTO Item VALUES('P017','Anchor F/C Milk powder 1Kg',580.00,40);
INSERT INTO Item VALUES('P018','Anchor N/F Milk powder 1Kg',560.00,33);
INSERT INTO Item VALUES('P019','Milo Packet 400g',240.00,33);
INSERT INTO Item VALUES('P020','Lipton Ceylon Tea 100g',107.00,40);



//--------------------Orders-------------------------


INSERT INTO Orders VALUES('D001','2008-2-15','C001');
INSERT INTO Orders VALUES('D002','2008-2-15','C005');
INSERT INTO Orders VALUES('D003','2008-2-20','C007');
INSERT INTO Orders VALUES('D004','2008-2-28','C002');
INSERT INTO Orders VALUES('D005','2008-3-20','C001');
INSERT INTO Orders VALUES('D006','2008-4-10','C003');
INSERT INTO Orders VALUES('D007','2008-5-10','C004');
INSERT INTO Orders VALUES('D008','2008-6-20','C005');
INSERT INTO Orders VALUES('D009','2008-8-12','C006');
INSERT INTO Orders VALUES('D010','2008-9-20','C006');
INSERT INTO Orders VALUES('D011','2009-2-13','C002');
INSERT INTO Orders VALUES('D012','2009-2-16','C007');
INSERT INTO Orders VALUES('D013','2009-2-20','C009');
INSERT INTO Orders VALUES('D014','2009-3-16','C002');
INSERT INTO Orders VALUES('D015','2009-4-15','C007');
INSERT INTO Orders VALUES('D016','2009-8-26','C009');
INSERT INTO Orders VALUES('D017','2010-2-16','C010');
INSERT INTO Orders VALUES('D018','2010-3-26','C002');
INSERT INTO Orders VALUES('D019','2011-6-11','C010');
INSERT INTO Orders VALUES('D020','2011-9-26','C010');


//--------------------OrderDetail---------------------

INSERT INTO OrderDetail VALUES('D001','P001',3,105.00);
INSERT INTO OrderDetail VALUES('D001','P002',5,525.00);
INSERT INTO OrderDetail VALUES('D001','P003',8,995.00);
INSERT INTO OrderDetail VALUES('D001','P006',1,560.00);
INSERT INTO OrderDetail VALUES('D002','P002',4,525.00);
INSERT INTO OrderDetail VALUES('D002','P003',4,995.00);
INSERT INTO OrderDetail VALUES('D002','P008',3,275.00);
INSERT INTO OrderDetail VALUES('D002','P010',7,90.00);
INSERT INTO OrderDetail VALUES('D002','P012',3,90.00);
INSERT INTO OrderDetail VALUES('D003','P001',9,105.00);
INSERT INTO OrderDetail VALUES('D003','P004',6,4100.00);
INSERT INTO OrderDetail VALUES('D003','P014',7,118.00);
INSERT INTO OrderDetail VALUES('D004','P002',2,525.00);
INSERT INTO OrderDetail VALUES('D004','P005',7,60.00);
INSERT INTO OrderDetail VALUES('D004','P008',1,275.00);
INSERT INTO OrderDetail VALUES('D004','P019',9,240.00);
INSERT INTO OrderDetail VALUES('D004','P015',5,220.00);
INSERT INTO OrderDetail VALUES('D004','P016',8,220.00);
INSERT INTO OrderDetail VALUES('D004','P020',5,107.00);
INSERT INTO OrderDetail VALUES('D004','P012',2,90.00);
INSERT INTO OrderDetail VALUES('D004','P004',3,190.00);
INSERT INTO OrderDetail VALUES('D004','P014',4,118.00);
INSERT INTO OrderDetail VALUES('D005','P001',6,105.00);
INSERT INTO OrderDetail VALUES('D005','P003',8,995.00);
INSERT INTO OrderDetail VALUES('D005','P007',9,5230.00);
INSERT INTO OrderDetail VALUES('D006','P003',9,995.00);
INSERT INTO OrderDetail VALUES('D006','P006',8,560.00);
INSERT INTO OrderDetail VALUES('D007','P020',1,107.00);
INSERT INTO OrderDetail VALUES('D008','P001',3,105.00);
INSERT INTO OrderDetail VALUES('D009','P002',2,525.00);
INSERT INTO OrderDetail VALUES('D009','P003',2,995.00);
INSERT INTO OrderDetail VALUES('D009','P005',2,60.00);
INSERT INTO OrderDetail VALUES('D009','P007',9,5230.00);
INSERT INTO OrderDetail VALUES('D009','P008',2,275.00);
INSERT INTO OrderDetail VALUES('D009','P001',9,105.00);
INSERT INTO OrderDetail VALUES('D009','P006',5,560.00);
INSERT INTO OrderDetail VALUES('D010','P010',6,90.00);
INSERT INTO OrderDetail VALUES('D010','P012',3,90.00);
INSERT INTO OrderDetail VALUES('D010','P004',6,4100.00);
INSERT INTO OrderDetail VALUES('D010','P001',6,105.00);
INSERT INTO OrderDetail VALUES('D010','P016',5,220.00);
INSERT INTO OrderDetail VALUES('D010','P002',3,525.00);