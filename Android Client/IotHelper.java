package com.gumpcome.iot;

import android.os.SystemClock;

import com.gumpcome.kernel.debug.GumpLog;
import com.microsoft.azure.iothub.IotHubEventCallback;
import com.microsoft.azure.iothub.IotHubStatusCode;
import com.microsoft.azure.iothub.DeviceClient;
import com.microsoft.azure.iothub.IotHubClientProtocol;
import com.microsoft.azure.iothub.IotHubEventCallback;
import com.microsoft.azure.iothub.IotHubMessageResult;
import com.microsoft.azure.iothub.IotHubStatusCode;
import com.microsoft.azure.iothub.Message;
import com.microsoft.azure.iothub.MessageCallback;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @Lit Li
 *         Created at 2016/8/30.
 */
public class IotHelper {
    private static final String TAG = "IotHelper";
    String connString = "HostName=iotdemo.azure-devices.cn;DeviceId=myFirstJavaDevice;SharedAccessKey=od1/RiE0ItHtbuewQ/5Tgw==";

    public void SendMessage(String message) throws URISyntaxException, IOException {
        IotHubClientProtocol protocol = IotHubClientProtocol.AMQPS;
        DeviceClient client = new DeviceClient(connString, protocol);

        try {
            client.open();
        } catch (IOException e) {
            GumpLog.e(TAG, "Iot连接异常:" + e.toString());
        }
        try {
            Message msg = new Message(message);
            msg.setProperty("messageCount", Integer.toString(1));
            System.out.println(message);
            EventCallback eventCallback = new EventCallback();
            client.sendEventAsync(msg, eventCallback, 1);
        } catch (Exception e) {
        }
        SystemClock.sleep(2000);
        client.close();
    }

    protected static class EventCallback implements IotHubEventCallback {
        public void execute(IotHubStatusCode status, Object context) {
            Integer i = (Integer) context;
            GumpLog.d(TAG, "IoTHub 返回的消息 " + i.toString()
                    + " ，状态:" + status.name());
        }
    }

    public void receiveMessage() throws URISyntaxException, IOException {

        IotHubClientProtocol protocol = IotHubClientProtocol.AMQPS;

        DeviceClient client = new DeviceClient(connString, protocol);
        {
            MessageCallback callback = new MessageCallback();
            Counter counter = new Counter(0);
            client.setMessageCallback(callback, counter);
        }
        try {
            client.open();
        } catch (IOException e) {
            GumpLog.e(TAG, "Iot连接异常:" + e.toString());
        }
        SystemClock.sleep(2000);
        client.close();
    }

    protected static class MessageCallback implements com.microsoft.azure.iothub.MessageCallback {
        public IotHubMessageResult execute(Message msg, Object context) {
            Counter counter = (Counter) context;

            GumpLog.d(TAG,
                    "Iot收到的消息:" + counter.toString()
                            + ",内容: " + new String(msg.getBytes(), Message.DEFAULT_IOTHUB_MESSAGE_CHARSET));

            int switchVal = counter.get() % 3;
            IotHubMessageResult res;
            switch (switchVal) {
                case 0:
                    res = IotHubMessageResult.COMPLETE;
                    break;
                case 1:
                    res = IotHubMessageResult.ABANDON;
                    break;
                case 2:
                    res = IotHubMessageResult.REJECT;
                    break;
                default:
                    throw new IllegalStateException("Invalid message result specified.");
            }

            GumpLog.d(TAG, "Responding to message " + counter.toString() + " with " + res.name());

            counter.increment();

            return res;
        }
    }

    /**
     * 作为消息的回调
     */
    protected static class Counter {
        protected int num;

        public Counter(int num) {
            this.num = num;
        }

        public int get() {
            return this.num;
        }

        public void increment() {
            this.num++;
        }

        @Override
        public String toString() {
            return Integer.toString(this.num);
        }
    }
}
