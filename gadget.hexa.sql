-- task1


CREATE DATABASE OnlineStore;
USE OnlineStore;


CREATE TABLE Customers (
    CustomerID int primary key auto_increment,
    FirstName varchar(100) not null,
    LastName varchar(100) not null,
    Email varchar(255) unique not null,
    Phone varchar(15) not null,
    Address text not null
);


CREATE TABLE Products (
    ProductID int primary key auto_increment,
    ProductName varchar(255) not null,
    Description text,
    Price decimal(10,2) not null
);

CREATE TABLE Orders (
    OrderID INT PRIMARY KEY AUTO_INCREMENT,
    CustomerID INT NOT NULL,
    OrderDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    TotalAmount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID) ON DELETE CASCADE
);

CREATE TABLE OrderDetails (
    OrderDetailID INT PRIMARY KEY AUTO_INCREMENT,
    OrderID INT NOT NULL,
    ProductID INT NOT NULL,
    Quantity INT NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID) ON DELETE CASCADE
);

CREATE TABLE Inventory (
    InventoryID INT PRIMARY KEY AUTO_INCREMENT,
    ProductID INT NOT NULL,
    QuantityInStock INT NOT NULL,
    LastStockUpdate DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID) ON DELETE CASCADE
);
INSERT INTO Customers (CustomerID, FirstName, LastName, Email, Phone, Address) VALUES  
(1, 'Amit', 'Sharma', 'amit.sharma@email.com', '9876543210', 'Mumbai, India'),  
(2, 'Priya', 'Singh', 'priya.singh@email.com', '9867543210', 'Delhi, India'),  
(3, 'Rajesh', 'Kumar', 'rajesh.kumar@email.com', '9856543210', 'Bangalore, India'),  
(4, 'Neha', 'Verma', 'neha.verma@email.com', '9846543210', 'Chennai, India'),  
(5, 'Vikram', 'Rao', 'vikram.rao@email.com', '9836543210', 'Hyderabad, India');

select * from Customers;
insert into Products(ProductName ,Description,Price) values  
(1, 'Laptop', 'Dell XPS 13', 82998.00),  
(2, 'Smartphone', 'iPhone 14', 74999.00),  
(3, 'Headphones', 'Sony WH-1000XM5', 22998.00),  
(4, 'Smartwatch', 'Samsung Galaxy Watch 4', 3999.00),  
(5, 'Tablet', 'iPad Air', 19999.00);
 
select * from Products;

select * from Orders;
INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES  
(1, '2024-03-15 10:00:00', 82998.00),  
(2, '2024-03-16 12:30:00', 74999.00),  
(3, '2024-03-17 14:15:00', 22998.00),  
(4, '2024-03-18 09:45:00', 3999.00),  
(5, '2024-03-19 11:20:00', 19999.00);
set foreign_key_checks = 0;
insert into OrderDetails (OrderID, ProductID, Quantity) value
(1, 1, 1),  
(1, 3, 1),  
(2, 2, 1),
(3, 4, 1),   
(3, 5, 1), 
(4, 4, 1),   
(5, 5, 1); 

set foreign_key_checks = 0;
INSERT INTO Inventory (ProductID, QuantityInStock) VALUES
(1, 50),  
(2, 30),  
(3, 100), 
(4, 40);  




-- task2

select FirstName,LastName,Email from Customers;
select Orders.OrderID,
Orders.OrderDate,
Customers.FirstName,
Customers.LastName from Orders join Customers on Orders.CustomerID = Customers.CustomerID;
insert into Customers (CustomerID,FirstName,LastName,Email,phone ,Address) values
(45,'harshini','mohan','1234@gmail.com','1234567899','45/1 ashoknagar street');
UPDATE Products  
SET Price = Price * 1.10  
WHERE ProductID IN (1, 2, 3, 4, 5);

-- delete specific order and order details

-- 9 th query not complete
 -- delete from  OrderDetails where OrderID='user@ID';
-- delete from Orders where OrderId='user@ID';
set foreign_key_checks = 0;
insert into Orders (CustomerID, OrderDate, TotalAmount) VALUES (56,'2025-08-25 10:00:00',70000);
select * from Orders;
update Customers set Email='mharsh1234@gmail.com' ,Address='salem' where CustomerID=45;
--  COALESCE(SUM(...), 0) ensure null value replace 0 
update  Orders set TotalAmount=(select  COALESCE(
sum(OrderDetails.Quantity*Products.Price),0)
 from OrderDetails join Products
 on OrderDetails.ProductID=Products.ProductID
 where OrderDetails.OrderID=Orders.OrderID
 );
SET SQL_SAFE_UPDATES = 0;


insert into Products (ProductName ,Description,Price) values ('poco' ,'Dell XPS 189',55000);
-- 11 th not complete
ALTER TABLE Customers ADD COLUMN OrderCount INT DEFAULT 0;

UPDATE Customers 
JOIN (
    SELECT CustomerID, COUNT(OrderID) AS TotalOrders
    FROM Orders
    GROUP BY CustomerID  
) AS OrderCounts 
ON Customers.CustomerID = OrderCounts.CustomerID
SET Customers.OrderCount = OrderCounts.TotalOrders;

-- task3



select od.orderid, od.productid, od.quantity, 
       c.customerid, c.firstname, c.lastname, 
       c.email, c.phone, c.address 
from orderdetails od
join orders o on od.orderid = o.orderid  
join customers c on o.customerid = c.customerid  
limit 1000;


select ProductName  ,sum( Price) as Total_Quantity  from Products group by ProductName;
 select c.customerID, c.Firstname, c.lastname, 
       c.email, c.phone, c.address from Customers c cross
       join Orders o on c.CustomerId=o.CustomerId group by c.CustomerId
       having count(o.OrderID)>=1;
select p.ProductName, sum(od.Quantity)as Ordered_quantity from OrderDetails od
join Products p on p.ProductID=od.ProductID group bY p.Productname order by 
 Ordered_quantity
 desc limit 1;
 select productname from products where productname like 
 '%category%' or productname like '%electronic%';
 
 select c.firstname,c.lastname ,avg(o.totalamount) as average_order 
 from customers c
 join orders o on c.customerid=o.customerid
 group by c.firstname,c.lastname ,c.customerid;
 
 
select od.orderid,c.firstname,c.lastname,c.email,c.phone,c.address 
from orders od 
left join customers c on c.customerid=od.customerid
where od.totalamount= (select max(totalamount) from orders);

select  p. productname  ,count(od.orderid) as order_times 
from OrderDetails od 
left join  products p on p.productid=od.productid 
group by productname
order by order_times desc ;

select c.firstname,c.lastname,c.email,c.phone,c.address
from customers c
join orders o on c.customerid=o.customerid
 join  orderdetails od  on od.orderid =o.orderid
 join products p on p.productid=od.productid
where p.productname='gadget';

select sum(o.totalamount) as order_date
from orders o
where o.orderdate between '2024-01-01' and '2024-12-31';

 
 
 
--  task 4

select firstname ,lastname from Customers where CustomerId not in(
select distinct CustomerID from Orders);
select p.Productname from products p where p. productId in  (
select i.productId from 
Inventory  i where i.quantityinstock>=0
);

select firstname ,lastname ,(select
sum(TotalAmount) from orders o
where o.CustomerId=c.CustomerId )as total_orders from Customers c ;

select p. productname ,coalesce((
select avg(od .quantity)from OrderDetails  od where
p.productid=od.productid),0)
 as totalquantity from products p;
 select * from orderdetails;
 
 SELECT coalesce((SELECT SUM(od.Quantity * p.Price) 
        FROM OrderDetails od 
        JOIN Products p ON od.ProductID = p.ProductID) ,0)
        AS TotalRevenue;
select c.firstname, c.lastname
from customers c
join (
    select o.customerid, count(o.orderid) as total_order
    from orders o
    group by o.customerid
    order by total_order desc
    limit 1
) as order_counts on c.customerid = order_counts.customerid;
SELECT p.ProductName, SUM(od.Quantity) AS Total_Quantity_Ordered
FROM OrderDetails od
JOIN Products p ON od.ProductID = p.ProductID
GROUP BY p.ProductID
ORDER BY Total_Quantity_Ordered DESC
LIMIT 1;

select c.firstname ,c.lastname ,
sum(
case 
when p.Price is  not null then (od.Quantity * p.Price )
else 0
end)as total_spending 
from customers c join Orders o on c.CustomerID = o.CustomerID
join OrderDetails od on o.OrderID = od.OrderID
join Products p ON od.ProductID = p.ProductID
group by c.CustomerID
order by Total_Spending desc
limit 1;
select avg(totalamount) as average_order_value 
from orders;

select c.firstname, c.lastname, count(o.orderid) as order_count 
from customers c 
join orders o on c.customerid = o.customerid 
group by c.customerid;

