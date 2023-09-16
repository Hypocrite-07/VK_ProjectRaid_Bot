package ru.projectraid;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;

import java.util.List;
import java.util.Random;

public class Bot {
    private TransportClient transportClient;
    private VkApiClient vkApiClient;
    private GroupActor group;
    private Random random;
    private Integer ts;

    public Bot() {
        try {
            transportClient = new HttpTransportClient();
            vkApiClient = new VkApiClient(transportClient);
            random = new Random();
            group = new GroupActor(222574479, "vk1.a.umOj-hRMmmIlipTzs74Su3wTxeq9NPS6vz1-fWKCxcTbSO7JhHiQnwvNTzjcLr-eDj1tK7eJeMB3L5n30nhVyc8-tBLmwRZePnvcDAlGzqPfkJNSGHUJZmdHTM32huxRka95m53QXKxc4-4N2aNGuetoXJs1Bx54wozXr_m4mI66PF_IL_kOFcms-K5cnlYdQ6oXDjAeHNRkmoxsUSF0ew");
            ts = vkApiClient.messages().getLongPollServer(group).execute().getTs();
        } catch (ApiException | ClientException e) {
            throw new RuntimeException(e);
        }
    }

    public void launch() {
        while (true) {
            try {
                MessagesGetLongPollHistoryQuery historyQuery = vkApiClient.messages().getLongPollHistory(group).ts(ts);
                List<Message> messages = historyQuery.execute().getMessages().getItems();

                if(!messages.isEmpty()) {
                    messages.forEach(message -> {

                        System.out.println(message.toString());
                        try {
                            if(message.getText().equals("Privet")) vkApiClient.messages().send(group).message("Privet").userId(message.getFromId()).randomId(random.nextInt(400000)).execute()  ;
                            else vkApiClient.messages().send(group).message("Privet").userId(message.getFromId()).randomId(random.nextInt(400000)).execute()  ;
                        }catch (ApiException | ClientException exception) {exception.printStackTrace();}

                    });
                }
                ts = vkApiClient.messages().getLongPollServer(group).execute().getTs();
                System.out.println(ts);
                Thread.sleep(500);
            } catch (InterruptedException | ClientException | ApiException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
