package jym.tozip;

import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;



public class CompressUtils {
    public void zip(String src,String dest,boolean is,String passwd){
        File srcfile = new File(src);
        //创建目标文件
        String destname = buildDestFileName(srcfile,dest);
        ZipParameters par =new ZipParameters();
        par.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        par.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        if (passwd!=null){
            par.setEncryptFiles(true);
            par.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
            par.setPassword(passwd.toCharArray());
        }

        try{
            ZipFile zipfile = new ZipFile(destname);
            if (srcfile.isDirectory()){
                if (!is){
                    File [] listFiles = srcfile.listFiles();
                    ArrayList<File> temp = new ArrayList<File>();
                    Collections.addAll(temp,listFiles);
                    zipfile.addFiles(temp, par);
                }
                zipfile.addFolder(srcfile,par);
            }else {
                zipfile.addFile(srcfile,par);
            }

        }catch (ZipException e){
            e.printStackTrace();
        }

    }


    /**
          * 目标文件名称
          * @param srcfile
          * @param dest
          * @return
          */
    public String buildDestFileName(File srcfile,String dest){
               if(dest==null){//没有给出目标路径时
                       if(srcfile.isDirectory()){
                              dest=srcfile.getParent()+File.separator+srcfile.getName()+".zip";
                           }else{
                             String filename=srcfile.getName().substring(0,srcfile.getName().lastIndexOf("."));
                               dest=srcfile.getParent()+File.separator+filename+".zip";
                           }
                   }else{
                       createPath(dest);//路径的创建
                      if(dest.endsWith(File.separator)){
                              String filename="";
                             if(srcfile.isDirectory()){
                                       filename=srcfile.getName();
                                   }else{
                                       filename=srcfile.getName().substring(0, srcfile.getName().lastIndexOf("."));
                                 }
                           dest+=filename+".zip";
                           }
                     }
              return dest;
            }


    /**
         * 路径创建
           * @param dest
        */
    private void createPath(String dest){
        File destDir=null;
        String separator=File.separator;
        if(dest.endsWith(separator)){
            destDir=new File(dest);
        }else{
            destDir=new File(dest.substring(0, dest.lastIndexOf("/")));//确认：/ \\ 路径分割符号，第一种验证时空
            //使用/做路径分割时，
        }
        if(!destDir.exists()){
            destDir.mkdirs();
        }
    }

}
