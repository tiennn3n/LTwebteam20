Create Database ShopSwatch
GO

USE ShopSwatch

GO
create table KhachHang (
	[Email] nvarchar(50) primary key,
	[Name] nvarchar(50),
	[Phone] nvarchar(10),
	[Pass] nvarchar(20)
)
GO
 
-- DROP table SanPham

create table SanPham(
	[id] int,
	[name] nvarchar(30),
	[category] nvarchar(30),
	[price] int,
	[image] nvarchar(30),
	[quantity] int,
	[sold] int)
GO

--DROP table YeuCau

create table YeuCau(
	[oid] int IDENTITY(1,1) PRIMARY KEY,
	[pid] int,
	[mid] nvarchar(50),
	[pnumber] int ,
	[odate] nvarchar(50)

	)
GO

Insert into Sanpham values (1,'G-SHOCK','Casio',50,'GSHOCK.png',50,30)
Insert into Sanpham values (2,'BABY-G','Casio',35,'BABYG.jpg',50,10)
Insert into Sanpham values (3,'PRO-TREK','Casio',40,'PROTREK.jpg',50,20)
Insert into SanPham values (4,'APPLE-WATCH','SmartWatch',40,'APPLEWATCH.jpg',50,80)
Insert into SanPham values (5,'GALAXY-WATCH','SmartWatch',35,'GALAXYWATCH.jpg',90,66)
Insert into SanPham values (6,'SHEEN','Casio',60,'SHEEN.jpg',40,75)
Insert into SanPham values (7,'XIAOMI WATCH','SmartWatch',35,'XIAO.jpg',90,30)
Insert into SanPham values (8,'SEVEN-SINS','UNKNOWN',100,'SEVENSINS.jpg',5,40)


select * from SanPham where sold =(
	select max(sold) from SanPham )

select * from SanPham where id =(
	select max(id) from SanPham )

select * from YeuCau

insert into YeuCau (pid,mid,pnumber,odate) values (1,'trung@gmail.com',2,'2022-12-12')