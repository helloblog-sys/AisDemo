package com.three.ais;

import dk.tbsalling.aismessages.AISInputStreamReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/** AIS解码
 * @author ThreeStone
 * @create 2020/8/19 - 21:03
 **/
public class ais {
    /**
     * 获取Ais解码信息
     * @param input
     * @throws IOException
     */
    public static void AisDecode(String input)throws IOException {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        AISInputStreamReader streamReader = new AISInputStreamReader(inputStream, aisMessage ->
                System.out.println("Received AIS message from MMSI " + aisMessage.getSourceMmsi().getMMSI() + ":" + aisMessage)
        );
        streamReader.run();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("输入AIS原始数据");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        AisDecode(input);
    }
}