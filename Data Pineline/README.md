# Data Pipeline

In this project, Vending Machine's Android client send status message to IoT Hub per 3 mins, and IoT Hub as SA's input, and SA process the data message then send to Azure SQL DB tables. PowerBI will access the Azure SQL DB to present the data to their devices operator and goods manager. 
*Figure 1. Smart Vending Machine solution architecture*

![Smart Vending Machine solution architecture](/Images/GumpCome_010.PNG)


## Data Message
Pls check with DataMessage.docx file, Vending Machine client will package devices data follow this Jason format to IoT Hub per 3mins

## Azure SQL DB
Support ISV design vending machine devices status db on Azure SQLDB, script file see GCSVMStatusDB.sql and GCSVMStatusDB_Insert.sql

## Azure Streame Analytics 
Support ISV create SA services on Azure, and setup IoT Hub as Input stream, and setup three table in Azure DB as output stream. Create SA Query as following :
```java
SELECT
    deviceid,svmtype,svmlat,svmlon,
    configversion,clientversion,osversion,expversion,boisversion,
    signal, cash.oneCoin as cashonecoin, cash.fiveCoin as cashfivecoin, 
    cash.currencyPartStatus as cashcurrencypartstatus, 
    temperatureStatus.setColdTemperature as setcoldtemperature, 
    temperatureStatus.setHotTemperature as sethottemperature, 
    svmTime as svmtime, disableSale as disablesale
INTO
    iotstreamingjob1output
FROM
    iotstreamingjob1
    
SELECT
    input.deviceid as deviceid, input.svmTime as svmtime,
    arrayElement.ArrayValue.no as goodsno,
    arrayElement.ArrayValue.id as goodsid,
    arrayElement.ArrayValue.name as goodsname,
    arrayElement.ArrayValue.event.eventType as goodseventtype,
    arrayElement.ArrayValue.cashSale as goodscashsale,
    arrayElement.ArrayValue.aliSale as goodsalisale,
    arrayElement.ArrayValue.jdSale as goodsjdsale,
    arrayElement.ArrayValue.wechatSale as goodswechatsale,
    arrayElement.ArrayValue.nowNum as goodsnownum,
    arrayElement.ArrayValue.maxNum as goodsmaxnum,
    arrayElement.ArrayValue.status as goodsstatus,
    arrayElement.ArrayValue.errorInfo as goodserrorinfo,
    arrayElement.ArrayValue.errorTime as goodserrortime,
    arrayElement.ArrayValue.noGoods as goodsnogoods
INTO
    iotstreamingjob1output1
FROM
    iotstreamingjob1 as input
CROSS APPLY GetArrayElements(input.goods) AS arrayElement

SELECT
    input.deviceid as deviceid, input.svmTime as svmtime,
    arrayElement.ArrayValue.wramAreaName as warmareaname,
    arrayElement.ArrayValue.temperature as temperature,
    arrayElement.ArrayValue.mode as temperaturemode
INTO
    iotstreamingjob1output2
FROM
    iotstreamingjob1 as input
CROSS APPLY GetArrayElements(input.temperatureStatus.temperatureAreas) AS arrayElement
```
