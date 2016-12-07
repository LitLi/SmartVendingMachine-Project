Insert dbo.svmstatus(deviceid,svmtype,configversion,clientversion,osversion,signal,cashonecoin,cashfivecoin,cashcurrencypartstatus,setcodetemperature,sethottemperature,svmtime,disablesale)
 values('440501B2D711','G5_2',15,100,'4.4.4',3,50,100,0,'19','26','2016-9-1 12:00:01',0)

Insert dbo.svmgoodsstatus(deviceid,goodsno,goodsid,goodsname,goodseventtype,goodscashsale,goodsalisale,goodsjdsale,goodswechatsale,goodsunionsale,goodsnownum,goodsmaxnum,goodsstatus,goodsnogoods,svmtime)
 values('440501B2D711',1,'0001','Cocacola','goodsdiscount',250,250,250,230,250,9,25,0,0,'2016-9-1 12:00:01')

Insert dbo.svmtemperaturestatus(deviceid,warmareaname,temperature,temperaturemode,svmtime)
 values('440501B2D711','Low Code Zone',21,2,'2016-9-1 12:00:01')