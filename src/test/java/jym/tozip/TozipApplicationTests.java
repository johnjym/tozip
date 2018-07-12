package jym.tozip;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
//import java.util.Scanner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TozipApplicationTests {
    CompressUtils compressUtils =new CompressUtils();
    @Test
    public void contextLoads() {



        TexttoImage texttoImage = new TexttoImage(new File("E:/jym/jym.txt"), new File("E:/jym/jym.jpg"));
        boolean success = texttoImage.convert();
        System.out.println("文本转图片：" + (success ? "成功" : "失败"));


        compressUtils.zip("E:/jym/jym.jpg", "E:/jym/jym.zip", true, "123456");

    }

}
