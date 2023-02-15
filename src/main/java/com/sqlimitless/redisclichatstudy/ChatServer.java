package com.sqlimitless.redisclichatstudy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServer implements MessageListener {

    private final RedisMessageListenerContainer redisMessageListenerContainer;

    private final RedisTemplate<String,String> stringRedisTemplate;

    public void enterChatRooms(String chatRoomName){
        redisMessageListenerContainer.addMessageListener(this, new ChannelTopic(chatRoomName));

        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()){
            String line = in.nextLine();
            if(line.equals("exit")){
                System.out.println("Good Bye !");
                break;
            }
            stringRedisTemplate.convertAndSend(chatRoomName, line);
        }
        redisMessageListenerContainer.removeMessageListener(this);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("Message : " + message.toString());
    }
}
