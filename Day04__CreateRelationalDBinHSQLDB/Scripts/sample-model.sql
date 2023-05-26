/*==============================================================*/
/* Table: Customer                                              */
/*==============================================================*/
createTables.add( "create table Customer ("
+ "   Id                   int                  ,"
+ "   FirstName            nvarchar(40)         not null,"
+ "   LastName             nvarchar(40)         not null,"
+ "   City                 nvarchar(40)         null,"
+ "   Country              nvarchar(40)         null,"
+ "   Phone                nvarchar(20)         null,"
+ "   constraint PK_CUSTOMER primary key (Id)"
+ ")"
+ ";");

/*==============================================================*/
/* Table: "Order"                                               */
/*==============================================================*/
createTables.add( "create table Order ("
+ "   Id                   int                  ,"
+ "   OrderDate            datetime             not null default getdate(),"
+ "   OrderNumber          nvarchar(10)         null,"
+ "   CustomerId           int                  not null,"
+ "   TotalAmount          decimal(12,2)        null default 0,"
+ "   constraint PK_ORDER primary key (Id)"
+ ")"
+ ";");


/*==============================================================*/
/* Table: OrderItem                                             */
/*==============================================================*/
createTables.add( "create table OrderItem ("
+ "   Id                   int                  ,"
+ "   OrderId              int                  not null,"
+ "   ProductId            int                  not null,"
+ "   UnitPrice            decimal(12,2)        not null default 0,"
+ "   Quantity             int                  not null default 1,"
+ "   constraint PK_ORDERITEM primary key (Id)"
+ ")"
+ ";");

/*==============================================================*/
/* Table: Product                                               */
/*==============================================================*/
createTables.add( "create table Product ("
+ "   Id                   int                  ,"
+ "   ProductName          nvarchar(50)         not null,"
+ "   SupplierId           int                  not null,"
+ "   UnitPrice            decimal(12,2)        null default 0,"
+ "   Package              nvarchar(30)         null,"
+ "   IsDiscontinued       bit                  not null default 0,"
+ "   constraint PK_PRODUCT primary key (Id)"
+ ")"
+ ";");

/*==============================================================*/
/* Table: Supplier                                              */
/*==============================================================*/
createTables.add( "create table Supplier ("
+ "   Id                   int                  ,"
+ "   CompanyName          nvarchar(40)         not null,"
+ "   ContactName          nvarchar(50)         null,"
+ "   ContactTitle         nvarchar(40)         null,"
+ "   City                 nvarchar(40)         null,"
+ "   Country              nvarchar(40)         null,"
+ "   Phone                nvarchar(30)         null,"
+ "   Fax                  nvarchar(30)         null,"
+ "   constraint PK_SUPPLIER primary key (Id)"
+ ")"
+ ";");


createTables.add( "alter table Order"
+ "   add constraint FK_ORDER_REFERENCE_CUSTOMER foreign key (CustomerId)"
+ "      references Customer (Id)"
+ ";");


createTables.add( "alter table OrderItem"
+ "   add constraint FK_ORDERITE_REFERENCE_ORDER foreign key (OrderId)"
+ "      references Order (Id)"
+ ";");

createTables.add( "alter table OrderItem"
+ "   add constraint FK_ORDERITE_REFERENCE_PRODUCT foreign key (ProductId)"
+ "      references Product (Id)"
+ ";");

createTables.add( "alter table Product"
+ "   add constraint FK_PRODUCT_REFERENCE_SUPPLIER foreign key (SupplierId)"
+ "      references Supplier (Id)"
+ ";");
