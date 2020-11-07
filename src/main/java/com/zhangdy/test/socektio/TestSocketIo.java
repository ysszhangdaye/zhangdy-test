package com.zhangdy.test.socektio;
import com.alibaba.fastjson.JSON;
import io.socket.client.IO;
import io.socket.client.Socket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TestSocketIo {


    public static void main(String[] args) {
        String url = "http://test.mobile.icctoro.com:7007";
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{"websocket"};
            //失败重试次数
            options.reconnectionAttempts = 10;
            //失败重连的时间间隔
            options.reconnectionDelay = 1000;
            //连接超时时间(ms)
            options.timeout = 500;
            final Socket socket = IO.socket(url, options);

            String channel  = "collection";
            String deviceId  = "zhangdaye";
            String walletId  = "zhangdaye";
            CollectionReq collectionReq = new CollectionReq();
            collectionReq.setChannel(channel);
            collectionReq.setDeviceId(deviceId);
            collectionReq.setWalletId(walletId);
            System.out.println("23123");
            socket.on("add", objects -> System.out.println("client: " + "订阅成功，收到反馈->" +JSON.toJSONString(objects)));
            socket.connect();
            socket.emit("subscribe", JSON.toJSON(collectionReq));
            socket.on("disconnect", objects -> System.out.println("断开连接"));
            socket.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        @NoArgsConstructor
        @Setter@Getter

        static
        class CollectionReq{
        private String channel;
        private String deviceId;
        private String walletId;
    }

}
