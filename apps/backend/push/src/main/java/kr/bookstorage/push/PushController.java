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
    public static final String FCM_SERVER_API_KEY = "AAAA2eKcSFg:APA91bGK7ZNNubI4kjM0p-AQvAl4TS8nKeZtN83L3lJx8OPedaWdxxrV3PoV0C0Odtbm6fAywy4LqoxtRwsMS37Ytd_uTLmT_R1-vpS6xos_tax50H-h5_HPiYprfQ2DOWb7jYQdFXNP";
//    private String deviceRegistrationId = "cqaDZsAXdDc:APA91bGyszuuHyEi9TFHrRtnOOqowS_7kL9v-oZpUpVr-9ArnIt03BcILu9SM6f97NVLQId63ZaZgPXqCXgu7l6ziSxjeGmwL4MqL-AfUAgqhUrQt-2J_hAjR3Nr1Ry5Wl8U85D8gmXx";
//    private String deviceRegistrationId = "fER9l1aPEoY:APA91bHoFWHAwvx2BphKGRKfjA9KUmqdufAEj-t7VeLgNMK457N7kFjttmA4BPEeINdZ-lOecgbhRrgLUx5T3fEmoRY8hGYCX_XEH_Xv-QDzCkegXvREPC4x0snzG2QehfoxZ62b8hg7";
//    private String deviceRegistrationId = "ctVlzKFetKY:APA91bHBXHLF4iIJXgEcra3LCm6BUq0IagUA_wN-IpRxNMyUILLg_G7b0z3R9zrW5ofWY9qK7j3s-WBUMLf4VV1TiravyPfGuw5u8qqLgaYgSiiWtNOINsl179zyrs7yUaZObEncOHr3";
    private String deviceRegistrationId = "cznpZkkUmoE:APA91bFhSnahI-Mk0I1O5BbTma_95-C2IP0QB2-wEQ0s6VyDWfxZcNqQh1xBgDDUWeyqKa8pgmjGx7jqc_2shHvRCMP65cd0TqesRo9k1cTt9YrSj0oozXQYGjDCYJXokFQWEKIqCpv-";

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
