# SmartVendingMachine-Project

This project is supporting the ISV who's provider of Smart Vending Machine solution in chinar,help to develop a way to monitor the working status of its machines and to get real-time information on replenishment needs.

## Pain Point

To date, this ISV has no solution that can monitor vending machine health status or get real-time information on whether a device is working. Any device needing attention usually requires two onsite visits—once to diagnose the issue and again to return with the component to fix the issue. All this adds to the cost of maintaining the machines. And also, their goods manager must check on the selling status of the vending machines to see if they need replenishing. 

## Solution 
Use Azure IoT Hub to collect data on supplies and device health for all vending machines and send to the uniform management platform for daily monitoring. This will help the staff to diagnose device issues online, which can save on maintenance costs and help realize predictive maintenance cost savings.

In this solution, ISV will use following Microsoft Technology:
- Azure IoT Hub
- Stream Analytics
- Azure SQL Database
- Power BI

## Android Client 
In this project, Vending Machine Client OS is Android, need integrated the IoT Hub Android SDK on (Sample: https://github.com/Azure/azure-iot-sdk-java/tree/master/device/samples/android-sample). Help ISV team to update protocal as AMQP and offer Android IoT Hub Intergration source code in [Android Client](https://github.com/LitLi/SmartVendingMachine-Project/blob/master/Android%20Client/IotHelper.java)

## Data Pipeline
In this project, help ISV to setup and configure IoT Hub, SA, and Azure SQL Database. More detail content and script files in [Data Pipeline](https://github.com/LitLi/SmartVendingMachine-Project/tree/master/Data%20Pineline) . 

## Other Content
This project is in MS DX Ascend+ programm, more result please reference [Ascend+](https://microsoft.github.io/techcasestudies/iot/2016/11/08/GumpCome.html)
