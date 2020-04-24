package com.sky7th.springdatajpashop.web.handlebars;

import com.github.jknack.handlebars.Options;
import com.google.common.base.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@HandlebarsHelper
public class CustomHandlbarsHelper {

//    @Autowired
//    private Resource resource;

    public  String DateFormat(String date) throws ParseException {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date2= transFormat.parse(date);
        return transFormat.format(date2);
    }

    public CharSequence stringEquals(final Object obj1, final Options options) throws IOException {
        Object obj2 = options.param(0);
        return obj1.toString().equals(obj2.toString()) ? options.fn() : options.inverse();
    }

    public String isEquals(String data1, String data2){
        if(data1.equals(data2))    return "true";
        else return "false";
    }


    public String pageNavigaterOn(int data1, int data2){
        //log.debug("pageNavigaterOn:{}:{}",data1,data2);
        if(data1==data2)   return "class='on'";
        else return "";
    }

    public String pageNavigaterActive(int data1, int data2){
        //log.debug("pageNavigaterOn:{}:{}",data1,data2);
        if(data1==data2)   return "class='active'";
        else return "";
    }

    public String adminChannelPrintUl(int index, int size, List<Object> list, String mode){
        //log.debug("adminChannelPrintUl:{}:{}:{}:{}:{}",index,size,list.size(),index%size,mode);
        if(index%size==1 && mode.equals("start")) return "<ul>" ;
        else if(mode.equals("end") &&  (index%size==0 || index==list.size())) return "</ul>";
        else return "";
    }

    //{{/printKoreaFormat}}
    public String printKoreaFormat(Date date){
        if (date ==null) return null;
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy년 MM월 dd일  HH시 mm분");
        String result = format1.format(date);
        return result;

    }

    public String printDate(Date date, String mode){
        if (date ==null) return null;
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd hh:mm a",new Locale("en", "US"));
        String result = format1.format(date);
        String result2 = format2.format(date);
        switch (mode){
            case "yyyy-MM-dd" : return result.substring(0, 10);
            case "HH:mm" : return result.substring(11, 16);
            case "HH:mm:ss" : return result.substring(11, 19);
            case "hh:mm a" : return result2.substring(11, 19);
            case "HH:mm a" : return result2.substring(11, 19);
            case "yyyy" : return result.substring(0, 4);
            case "MM" : return result.substring(5, 7);
            case "dd" : return result.substring(8, 10);
            case "HH" : return result.substring(11, 13);
            case "mm" : return result.substring(14, 16);
            case "ss" : return result.substring(17, 19);
            default : return result;
        }
    }


    public CharSequence equals(final Object obj1, final Options options) throws IOException {
        Object obj2 = options.param(0);
        //log.debug("equals:obj1:{},obj2:{}", obj1, obj2);
        if(obj1 != null && obj2 != null){
            return Objects.equal(obj1.toString(), obj2.toString()) ? options.fn() : options.inverse();
        }
        return Objects.equal(obj1, obj2) ? options.fn() : options.inverse();
    }


    public CharSequence compare(final Object obj1, final Options options) throws IOException {
        Object obj2 = options.param(0);
        int data1 = (int)obj1;
        int data2 = (int)obj2;
        return  data1 > data2 ? options.fn() : options.inverse();
    }



    public CharSequence isLanguagesLengthMoreOne(final List<String> languaes, final Options options) throws IOException {
        return    languaes !=null && languaes.size() > 1  ? options.fn() : options.inverse();
    }

    public String getListSize(List<Object> list)  {
        if(list == null ) return "0";
        return    String.valueOf(list.size());
    }

//    public String resourceBundle(Object code, Options options){
////    String obj2 = options.param(0);
//        String title="";
//        if (code == null) return "";
//        if(code instanceof String){
//            title = resource.getResource(code.toString());
//        }else if (code.getClass().isEnum()){
//            title = resource.getResource((Enum<?>) code);
//        }
//        return title;
//    }
}