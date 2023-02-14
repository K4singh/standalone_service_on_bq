package com.google;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.remoteFunctionObject;
import com.google.FunctionResponseObj;
import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.gson.*;
import java.io.BufferedWriter;
import java.lang.reflect.Array;
import java.util.logging.Logger;
public class helloWorld implements HttpFunction  {
    private static final Gson gson = new Gson();
    private static final Logger logger = Logger.getLogger(helloWorld.class.getName());


    @Override
    public void  service( HttpRequest request, HttpResponse response) throws Exception  {
//        System.out.println("Generating string");
        JsonElement requestParsed = gson.fromJson(request.getReader(), JsonElement.class);
        JsonObject requestJson = requestParsed.getAsJsonObject();
        ObjectMapper objectMapper = new ObjectMapper();
        FunctionResponseObj functionResponseObj = new FunctionResponseObj();
        String[][] calls = null;
        String[] reply = {"one", "two", "three"};
        if (requestJson != null) {
            logger.info(">> Request Json: " + requestJson);
            JsonNode jsonNode = objectMapper.readTree(String.valueOf(requestJson));
            remoteFunctionObject remoteFunctionObject = objectMapper.treeToValue(jsonNode,
                    remoteFunctionObject.class);
            calls = remoteFunctionObject.getCalls();

            logger.info(">>calls: " + calls);
        }
        functionResponseObj.setReplies(reply);
        BufferedWriter writer = response.getWriter();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        writer.write(gson.toJson(functionResponseObj));

    }

    public static void main(String[] args){
        String sample = "this_is_a_string";
//        String encoded = generateString(sample);
        System.out.println(sample);

    }
}
