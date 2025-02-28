package com.example.smile_v1;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public final class MemoryData {

    public static void saveData(String data, Context context){
        try{
            //FileOutputStream fileOutputStream = context.openFileOutput("data.txt", Context.MODE_PRIVATE);
            FileOutputStream fileOutputStream = context.openFileOutput("data.txt", Context.MODE_APPEND);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void saveLastMsgTs(String data, String chatId, Context context){
        try{
            FileOutputStream fileOutputStream = context.openFileOutput(chatId+".txt", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void saveName(String data, Context context){
        try{
            FileOutputStream fileOutputStream = context.openFileOutput("name.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getData(Context context){
        String data = "";
        try{
            FileInputStream fileInputStream = context.openFileInput("data.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            data = stringBuilder.toString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }

    public static String getName(Context context){
        String data = "";
        try{
            FileInputStream fileInputStream = context.openFileInput("name.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            data = stringBuilder.toString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }

    public static String getLastMsgTs(Context context, String chatId){
        String data = "0";
        try{
            FileInputStream fileInputStream = context.openFileInput(chatId+".txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            data = stringBuilder.toString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }
}
