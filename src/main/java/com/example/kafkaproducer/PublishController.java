package com.example.kafkaproducer;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;

@RestController
public class PublishController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateString;

    @GetMapping("/upload")
    public void publishToKafka() throws IOException {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(ResourceUtils.getFile("classpath:flight_data.json")));
            String line = reader.readLine();
            while (line != null) {
                kafkaTemplateString.send("TEST.STRING.CONFIG", line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/upload3")
    public void publishToKafka3() throws IOException {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(ResourceUtils.getFile("classpath:flight_data.json")));
            String line = reader.readLine();
            while (line != null) {
                kafkaTemplateString.send("TEST.STRING.PARTITION3.CONFIG", line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
