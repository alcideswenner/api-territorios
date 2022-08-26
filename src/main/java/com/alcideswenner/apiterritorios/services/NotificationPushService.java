package com.alcideswenner.apiterritorios.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.alcideswenner.apiterritorios.dto.PushNotificationRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class NotificationPushService {

    @Value("${firebase.key}")
    private String keyFirebase;

    final RestTemplate restTemplate = new RestTemplate();

    public Boolean requestSendPush(String body, String title) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", keyFirebase);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        PushNotificationRequestDTO dto = new PushNotificationRequestDTO();
        dto.setTo("/topics/all");
        dto.setPriority("high");

        PushNotificationRequestDTO.Data data = new PushNotificationRequestDTO.Data();
        data.setClickaction("FLUTTERNOTIFICATIONCLICK");
        data.setId("1");
        data.setStatus("done");
        dto.setData(data);

        PushNotificationRequestDTO.Notification notification = new PushNotificationRequestDTO.Notification();
        notification.setBody(body);
        notification.setTitle(title);
        dto.setNotification(notification);

        HttpEntity<PushNotificationRequestDTO> request = new HttpEntity<PushNotificationRequestDTO>(dto, headers);

        ResponseEntity<?> resposta = restTemplate.postForEntity("https://fcm.googleapis.com/fcm/send", request,
                PushNotificationRequestDTO.class);
        if (resposta.getStatusCode().value() == 200) {
            return true;
        }
        return false;
    }
}
