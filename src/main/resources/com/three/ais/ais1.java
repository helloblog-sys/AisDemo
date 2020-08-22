package com.three.ais;

import dk.tbsalling.aismessages.AISInputStreamReader;
import dk.tbsalling.aismessages.ais.messages.AISMessage;

import java.io.*;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * @author ThreeStone
 * @create 2020/8/19 - 21:34
 **/
public class ais1 {
    /**
     * 解码函数
     * @param input
     * @throws IOException
     */
    public static void AisDecode(String input)throws IOException {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        //此处将Lambda表达式还原,实现Consumer接口中的accept方法
        AISInputStreamReader streamReader = new AISInputStreamReader(inputStream, new Consumer<AISMessage>() {
            @Override
            public void accept(AISMessage aisMessage) {
                //将aisMessage转换成字符串
                String ais2 = String.valueOf(aisMessage);
                //调用输出函数
                readString(ais2);
            }
        }
        );
        streamReader.run();
    }

    /**
     * 输出解码信息函数
     * @param ais2
     */
    public static void readString(String ais2){
        String ais=ais2;
        System.out.println("解码信息为：" + ais);
        AisFileWriter(ais);
    }

    /**
     * 将解码信息输出到文件
     */
    public static void AisFileWriter(String aismessage) {
        FileWriter fw = null;
        try {
            //1.提供File类的对象，指明写出到的文件
            File file = new File("Ais.txt");

            //2.提供FileWriter的对象，用于数据的写出
            fw = new FileWriter(file,false);

            //3.写出的操作
            fw.write(aismessage);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流资源的关闭
            if(fw != null){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("输入AIS原始数据");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        AisDecode(input);
    }

}
