CREATE DATABASE restaurant8 ;

use restaurant8;
CREATE TABLE ta_vip_food(
  I_IDFood int NOT NULL auto_increment,
  T_FoodName text NOT NULL,
  F_Price double NOT NULL,
  I_Number int NOT NULL,
  PRIMARY KEY (I_IDFood)
 ) ;

 INSERT INTO `restaurant8`.`ta_vip_food` (`I_IDFood`, `T_FoodName`, `F_Price`,`I_Number`) VALUES 
('1', 'Súp nghêu kem sữa 1 ', '200.00 ','100 '),
('2', 'Gỏi hải sâm kèm bánh phồng tôm ', '120.000 ','100'),
('3', 'Súp cua bể hải sâm ', '250.000 ','100'),
('4', 'Súp rau củ kiểu Ý', '130.000 ','100'),
('5', 'Salad hoàng đế ', '130.000 ','100'),
('6', 'Súp hải sản hoàng kim','130.000 ','100'),
('7', 'Càng cua bách hoa', '130.000 ','100'),
('8', 'Súp bào ngư và cua măng tây',' 130.000 ','100');
CREATE TABLE ta_vip_infor(
  I_CodeStaff int NOT NULL auto_increment ,
  T_Name text NOT NULL,
  T_Sex  text NOT NULL,
  D_DateOfDay datetime NOT NULL,
  T_Address text NOT NULL,
  T_UserName text NOT NULL,
  T_Password text NOT NULL,
  T_Position text NOT NULL,
  PRIMARY KEY (I_CodeStaff )
) ;

INSERT INTO `restaurant8`.`ta_vip_infor` (`I_CodeStaff`, `T_Name`, `T_Sex`,`D_DateOfDay`,`T_Address`,`T_UserName`,`T_Password`,`T_Position`) VALUES
('1','Kham Khanh Trang','nữ','2004-04-28 ', 'Quang Nam', 'trang','123','admin' );

CREATE TABLE ta_vip_bill(
  I_CodeBill int NOT NULL ,
  I_IDTable int NOT NULL,
  D_Date datetime NOT NULL,
  F_Total double NOT NULL,
  I_CodeStaff int NOT NULL,

  PRIMARY KEY (I_CodeBill)  
);

CREATE TABLE ta_vip_BillDetail (
  I_IDCodeBillDetail int NOT NULL auto_increment,
  I_IDFood int NOT NULL,
  T_FoodName text NOT NULL,
  F_Price double NOT NULL,
  I_Number int NOT NULL,
  F_Total double NOT NULL,
  I_CodeBill int NOT NULL,
  PRIMARY KEY (I_IDCodeBillDetail)
) ;


ALTER TABLE ta_vip_BillDetail
ADD FOREIGN KEY (I_CodeBill) REFERENCES ta_vip_bill(I_CodeBill);

ALTER TABLE ta_vip_BillDetail
ADD FOREIGN KEY (I_IDFood ) REFERENCES ta_vip_food(I_IDFood);

CREATE TABLE ta_vip_table (
  I_IDTable int NOT NULL auto_increment,
  T_TableName text NOT NULL,
  T_Status text NOT NULL,
  PRIMARY KEY (I_IDTable)
) ;
INSERT INTO `restaurant8`.`ta_vip_table` (`I_IDTable`, `T_TableName`, `T_Status`) VALUES 
('1', 'Bàn 1 ', 'Trống '),
('2', 'Bàn 2 ', 'Trống '),
('3', 'Bàn 3 ', 'Trống '),
('4', 'Bàn 4 ', 'Trống '),
('5', 'Bàn 5 ', 'Trống '),
('6', 'Bàn 6 ', 'Trống '),
('7', 'Bàn 7', 'Trống '),
('8', 'Bàn 8 ', 'Trống '),
('9', 'Bàn 9 ', 'Trống '),
('10', 'Bàn 10 ', 'Trống '),
('11', 'Bàn 11 ', 'Trống '),
('12', 'Bàn 12 ', 'Trống '),
('13', 'Bàn 13 ', 'Trống '),
('14', 'Bàn 14 ', 'Trống '),
('15', 'Bàn 15 ', 'Trống '),
('16', 'Bàn 16 ', 'Trống '),
('17', 'Bàn 17', 'Trống '),
('18', 'Bàn 18 ', 'Trống '),
('19', 'Bàn 19 ', 'Trống '),
('20', 'Bàn 20 ', 'Trống ');

ALTER TABLE ta_vip_bill
ADD FOREIGN KEY (I_IDTable) REFERENCES ta_vip_table(I_IDTable);

ALTER TABLE ta_vip_bill
ADD FOREIGN KEY (I_CodeStaff) REFERENCES ta_vip_infor(I_CodeStaff);







