--Clean the DataBase
IF EXISTS(SELECT * FROM sysobjects WHERE name='svmstatus')
  DROP TABLE svmstatus
IF EXISTS(SELECT * FROM sysobjects WHERE name='svmgoodsstatus')
  DROP TABLE svmgoodsstatus
IF EXISTS(SELECT * FROM sysobjects WHERE name='svmtemperaturestatus')
  DROP TABLE svmtemperaturestatus


--Create Table
--Device Status Table
go
CREATE TABLE svmstatus(
	deviceid varchar(50) Not Null ,
	svmtype varchar(30) Not Null,
	svmlat	varchar(30),
	svmlon	varchar(30),
	configversion int Not Null,
	clientversion int Not Null,
	osversion varchar(20),
	expversion int,
	boisversion varchar(20),
	signal int,
	cashonecoin int,
	cashfivecoin int,
	cashcurrencypartstatus int,
	setcodetemperature varchar(20),
	sethottemperature varchar(20),
	svmtime datetime Not Null,
	disablesale int
);
CREATE CLUSTERED INDEX SVMStatusIndex ON svmstatus (svmtime ASC); 
go


--Good Channel Status Table
go
CREATE TABLE svmgoodsstatus(
	deviceid varchar(50) Not Null,
	goodsno int	Not Null,
	goodsid varchar(50),
	goodsname varchar(200),
	goodseventtype varchar(200),
	goodscashsale int,
	goodsalisale int,
	goodsjdsale int,
	goodswechatsale int,
	goodsunionsale int,
	goodsnownum int,
	goodsmaxnum int,
	goodsstatus int,
	goodserrorinfo varchar(200),
	goodserrortime datetime,
	goodsnogoods int,
	svmtime date Not Null
);
CREATE CLUSTERED INDEX SVMGoodsStatusIndex ON svmgoodsstatus (svmtime ASC); 
go



--Device Temperature Status Table
go
CREATE TABLE svmtemperaturestatus(
	deviceid varchar(50) Not Null,
	warmareaname varchar(200),
	temperature int,
	temperaturemode int,
	svmtime datetime Not Null
);
CREATE CLUSTERED INDEX SVMTempStatusIndex ON SVMTemperatureStatus (svmtime ASC); 
go

