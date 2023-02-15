package com.sqlimitless.redisclichatstudy;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class RedisCliChatStudyApplication implements CommandLineRunner {

    private final ChatServer chatServer;

    public static void main(String[] args) {
        SpringApplication.run(RedisCliChatStudyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("어플리케이션 시작~!");
        chatServer.enterChatRooms("Room1");
    }
}
