package kr.bookstorage.push;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by ksb on 2017. 6. 10..
 */
@RestController
@RequestMapping("/push")
public class PushController {

    //https://github.com/sushant47/FCM-server-sample-java

    public static final String FCM_URL = "https://fcm.googleapis.com/fcm/send";
    public static final String FCM_SERVER_API_KEY = "AAAAIu1XbMA:APA91bHkFypC3gmWJ8a8mvyL8b6JgxKRGFx3Xl2iz-T1kzQSiro62vnCaH5HqMT7ULneb1E3XWi3bE7mcsm7nXdIxwOxlqAj3QHhqe52vdPLuQLApgpxyy4fhvPyT_H8swuqiCNsvOXs";
    private String deviceRegistrationId = "cqaDZsAXdDc:APA91bGyszuuHyEi9TFHrRtnOOqowS_7kL9v-oZpUpVr-9ArnIt03BcILu9SM6f97NVLQId63ZaZgPXqCXgu7l6ziSxjeGmwL4MqL-AfUAgqhUrQt-2J_hAjR3Nr1Ry5Wl8U85D8gmXx";

    @RequestMapping(value = "{token}") //here token is device registeration ID (deviceRegistrationId). It can be called from client code.
    public PushObject getPush(@PathVariable final String token) {
        PushObject push = new PushObject();
        push.setName("philip");
        push.setId(token);
//        deviceRegistrationId = token;

        String result = "";
        URL url;
        try {
            url = new URL(FCM_URL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "key=" + FCM_SERVER_API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject json = new JSONObject();

            json.put("to", deviceRegistrationId.trim());
            JSONObject info = new JSONObject();
            info.put("title", "notification title"); // Notification title
            info.put("body", "message body"); // Notification
            // body
            json.put("notification", info);


            OutputStreamWriter wr = new OutputStreamWriter(
                    conn.getOutputStream());
            wr.write(json.toString());
            wr.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            result = "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            result = "FAILURE";
        }
        System.out.println("GCM Notification is sent successfully");

        return push;
    }

    public static byte[] getPostData(String registrationId) throws JSONException {
        HashMap<String, String> dataMap = new HashMap<>();
        JSONObject payloadObject = new JSONObject();

        dataMap.put("name", "Aniket!");
        dataMap.put("country", "India");

        JSONObject data = new JSONObject(dataMap);
        ;
        payloadObject.put("data", data);
        payloadObject.put("to", registrationId);

        return payloadObject.toString().getBytes();
    }

    public static String convertStreamToString(InputStream inStream) throws Exception {
        InputStreamReader inputStream = new InputStreamReader(inStream);
        BufferedReader bReader = new BufferedReader(inputStream);

        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = bReader.readLine()) != null) {
            sb.append(line);
        }

        return sb.toString();
    }

}
