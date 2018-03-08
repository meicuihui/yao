package com.android.xuelian.ye;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.widget.Toast;
import java.io.File;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by yb on 2017-10-26.
 * @author yebin
 */

public class Utils {
    static String tag="Utils";
    public static String MD5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static  String num(int n){
        String n1="";
        if(n>9){
            n1=n+"";
        }else{
            n1="0"+n;
        }
        return n1;
    }

    public static boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable() && mNetworkInfo.isConnected();
            }
        }

        return false;
    }
public static void  toast(Context context,String info){
    Toast.makeText(context,info, Toast.LENGTH_SHORT).show();
}
    public static boolean isHaveHttp(String str){
        int inde=str.indexOf("http://");
        if(inde!=-1) {
            return true;
        }
        int inde1=str.indexOf("https://");
        if(inde1!=-1) {
            return true;
        }
        return false;
    }

//    public static void setNoNeedDelete(){
//        SharedPreferences sp= Application.getInstance().getSharedPreferences("needDelete",MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putBoolean("need",false);
//        editor.commit();
//    }
//
//    public static boolean getNeedDelete(){
//        SharedPreferences sp= Application.getInstance().getSharedPreferences("needDelete",MODE_PRIVATE);
//        return sp.getBoolean("need",true);
//    }
//
//    public static void setNeedDelete(){
//        SharedPreferences sp= Application.getInstance().getSharedPreferences("needDelete",MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putBoolean("need",true);
//        editor.commit();
//    }

    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return "";
    }
//    public static int getExtensionNameForId(String filename) {
//        String name="";
//        if ((filename != null) && (filename.length() > 0)) {
//            int dot = filename.lastIndexOf('.');
//            if ((dot >-1) && (dot < (filename.length() - 1))) {
//                name= filename.substring(dot + 1);
//            }
//        }
//        if(name.equals("doc")||name.equals("docx")||name.equals("DOC")||name.equals("DOCX")){
//            return R.drawable.word;
//        }else if(name.equals("pdf")||name.equals("PDF")){
//            return R.drawable.pdf1;
//        }else if(name.equals("xlsx")||name.equals("xls")||name.equals("XLSX")||name.equals("XLS")){
//            return R.drawable.excel;
//        }else if(name.equals("ppt")||name.equals("pptx")||name.equals("PPT")||name.equals("PPTX")){
//            return R.drawable.ppt;
//        }else if(name.equals("txt")||name.equals("TXT")){
//            return R.drawable.txt;
//        }
//        return R.drawable.word;
//    }
    public static String getUrlName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf("/uploads");
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return "";
    }
    public static String getFristName(String filename,int index) {
        if(TextUtils.isEmpty(filename)){
            return "";
        }
        if(filename.length()==0){
            return "";
        }
        if(filename.length()<index){
            return filename.substring(0,filename.length());
        }
       return filename.substring(0,index);

    }
    public static String getFileName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('/');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return "";
    }
    public static String chDate(long t){
        String str="";
        Date date=new Date(t);
        DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        str=sdf2.format(date);
        return str;
    }

    public static Intent openLocalFile(Context context,String filePath){

        File file = new File(filePath);
        if(!file.exists()) return null;
        /* 取得扩展名 */
        String end=file.getName().substring(file.getName().lastIndexOf(".") + 1,file.getName().length()).toLowerCase();
        /* 依扩展名的类型决定MimeType */
        if(end.equals("m4a")||end.equals("mp3")||end.equals("mid")||
                end.equals("xmf")||end.equals("ogg")||end.equals("wav")){
            return getAudioFileIntent(context,filePath);
        }else if(end.equals("3gp")||end.equals("mp4")){
            return getAudioFileIntent(context,filePath);
        }else if(end.equals("jpg")||end.equals("gif")||end.equals("png")||
                end.equals("jpeg")||end.equals("bmp")){
            return getImageFileIntent(context,filePath);
        }else if(end.equals("apk")){
            return getApkFileIntent(context,filePath);
        }else if(end.equals("ppt")||end.equals("pptx")){
            return getPptFileIntent(context,filePath);
        }else if(end.equals("xls")||end.equals("xlsx")){
            return getExcelFileIntent(context,filePath);
        }else if(end.equals("doc")||end.equals("docx")){
            return getWordFileIntent(context,filePath);
        }else if(end.equals("pdf")){
            return getPdfFileIntent(context,filePath);
        }else if(end.equals("chm")){
            return getChmFileIntent(context,filePath);
        }else if(end.equals("txt")){
            return getTextFileIntent(context,filePath,false);
        }else{
            return getAllIntent(context,filePath);
        }
    }

    //Android获取一个用于打开任意文件
    public static Intent getAllIntent(  Context context,String param ) {
        File apkfile = new File(param);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(Build.VERSION.SDK_INT>=24) {
            Uri apkUri =FileProvider.getUriForFile(context, "com.fcp.gradual.service.GenericFileProvider", apkfile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "*/*");
        }else{
            intent.setDataAndType(Uri.fromFile(apkfile),"*/*");
        }
        return intent;
    }
    //Android获取一个用于打开APK文件的intent
    public static Intent getApkFileIntent(  Context context,String param ) {
        File apkfile = new File(param);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(Build.VERSION.SDK_INT>=24) {
            Uri apkUri =FileProvider.getUriForFile(context, "com.fcp.gradual.service.GenericFileProvider", apkfile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        }else{
            intent.setDataAndType(Uri.fromFile(apkfile),"application/vnd.android.package-archive");
        }
        return intent;
    }

    //Android获取一个用于打开VIDEO文件的intent
    public static Intent getVideoFileIntent(  Context context,String param ) {

        File apkfile = new File(param);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        if(Build.VERSION.SDK_INT>=24) {
            Uri apkUri =FileProvider.getUriForFile(context, "com.fcp.gradual.service.GenericFileProvider", apkfile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "video/*");
        }else{
            intent.setDataAndType(Uri.fromFile(apkfile),"video/*");
        }
        return intent;
    }

    //Android获取一个用于打开AUDIO文件的intent
    public static Intent getAudioFileIntent( Context context,String param ){
        File apkfile = new File(param);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        if(Build.VERSION.SDK_INT>=24) {
            Uri apkUri =FileProvider.getUriForFile(context, "com.fcp.gradual.service.GenericFileProvider", apkfile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "audio/*");
        }else{
            intent.setDataAndType(Uri.fromFile(apkfile),"audio/*");
        }
        return intent;
    }

    //Android获取一个用于打开Html文件的intent
    public static Intent getHtmlFileIntent(  Context context,String param ){

        Uri uri = Uri.parse(param ).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(param ).build();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "text/html");
        return intent;
    }

    //Android获取一个用于打开图片文件的intent
    public static Intent getImageFileIntent( Context context, String param ) {
        File apkfile = new File(param);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(Build.VERSION.SDK_INT>=24) {
            Uri apkUri =FileProvider.getUriForFile(context, "com.fcp.gradual.service.GenericFileProvider", apkfile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "image/*");
        }else{
            intent.setDataAndType(Uri.fromFile(apkfile),"image/*");
        }
        return intent;
    }

    //Android获取一个用于打开PPT文件的intent
    public static Intent getPptFileIntent(  Context context,String param ){
        File apkfile = new File(param);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(Build.VERSION.SDK_INT>=24) {
            Uri apkUri =FileProvider.getUriForFile(context, "com.fcp.gradual.service.GenericFileProvider", apkfile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.ms-powerpoint");
        }else{
            intent.setDataAndType(Uri.fromFile(apkfile),"application/vnd.ms-powerpoint");
        }
        return intent;
    }

    //Android获取一个用于打开Excel文件的intent
    public static Intent getExcelFileIntent( Context context, String param ){

        File apkfile = new File(param);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(Build.VERSION.SDK_INT>=24) {
            Uri apkUri =FileProvider.getUriForFile(context, "com.fcp.gradual.service.GenericFileProvider", apkfile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.ms-excel");
        }else{
            intent.setDataAndType(Uri.fromFile(apkfile),"application/vnd.ms-excel");
        }
        return intent;
    }

    //Android获取一个用于打开Word文件的intent
    public static Intent getWordFileIntent(  Context context,String param ){

        File apkfile = new File(param);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(Build.VERSION.SDK_INT>=24) {
            Uri apkUri =FileProvider.getUriForFile(context, "com.fcp.gradual.service.GenericFileProvider", apkfile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/msword");
        }else{
            intent.setDataAndType(Uri.fromFile(apkfile),"application/msword");
        }
        return intent;
    }

    //Android获取一个用于打开CHM文件的intent
    public static Intent getChmFileIntent( Context context, String param ){
        File apkfile = new File(param);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(Build.VERSION.SDK_INT>=24) {
            Uri apkUri =FileProvider.getUriForFile(context, "com.fcp.gradual.service.GenericFileProvider", apkfile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/x-chm");
        }else{
            intent.setDataAndType(Uri.fromFile(apkfile),"application/x-chm");
        }
        return intent;
    }

    //Android获取一个用于打开文本文件的intent
    public static Intent getTextFileIntent(  Context context,String param, boolean paramBoolean){

        File apkfile = new File(param);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(Build.VERSION.SDK_INT>=24) {
            Uri apkUri =FileProvider.getUriForFile(context, "com.fcp.gradual.service.GenericFileProvider", apkfile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "text/plain");
        }else{
            intent.setDataAndType(Uri.fromFile(apkfile),"text/plain");
        }
        return intent;
    }
    //Android获取一个用于打开PDF文件的intent
    public static Intent getPdfFileIntent( Context context,String param ){

        File apkfile = new File(param);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(Build.VERSION.SDK_INT>=24) {
            Uri apkUri =FileProvider.getUriForFile(context, "com.fcp.gradual.service.GenericFileProvider", apkfile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/pdf");
        }else{
            intent.setDataAndType(Uri.fromFile(apkfile),"application/pdf");
        }
        return intent;
    }

}
