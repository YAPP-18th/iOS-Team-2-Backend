package com.yapp.yongyong.infra.fcm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.yapp.yongyong.infra.fcm.FcmMessage.Message;
import com.yapp.yongyong.infra.fcm.FcmMessage.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class FcmService {
    private final String API_URL = "https://fcm.googleapis.com/v1/projects/yongyong-db0f9/messages:send";
    private final ObjectMapper objectMapper;


    public void sendMessageTo(String targetToken, String title, String body, String image) throws IOException {
        String message = makeMessage(targetToken, title, body, image);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        Response response = client.newCall(request)
                .execute();

        log.info("FCM 발신: ", response.body().toString());
    }

    private String makeMessage(String targetToken, String title, String body, String image) throws JsonProcessingException {
        FcmMessage fcmMessage = FcmMessage.builder()
                .message(Message.builder()
                        .token(targetToken)
                        .notification(Notification.builder().title(title).body(body).image(image).build())
                        .build())
                .validate_only(false)
                .build();

        return objectMapper.writeValueAsString(fcmMessage);
    }

    private String getAccessToken() throws IOException {
        String firebaseConfigPath = "/home/ec2-user/app/firebase-adminsdk.json";
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));
        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }
}
