package com.example.ashab.hcs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class HcsLetter {

    private String emailAddress = "annashabaeva953@gmail.com";
    private String themeLine;

    private String hcsLine;
    private String electLine;
    private String sumLine;
    private String detail;
    static final String regex = " ";

    private String dayTariffValue = "";
    private String nightTariffValue = "";
    private String prElDayValue =  "";
    private String prElNightValue =  "";
    private String curElDayValue =  "";
    private String curElNightValue =  "";

    private String hcsValue ="";
    private String electValue = "";
    private String sumValue = "";

    static final Map<String , String> MONTH_MAP = new HashMap<String , String>() {{
        put("Sep", "сентябрь");
        put("Okt", "октябрь");
        put("Nov", "ноябрь");
        put("Dec", "декабрь");
        put("Jan", "январь");
        put("Feb", "февраль");
        put("Mar", "март");
        put("Apr", "апрель");
        put("May", "май");
        put("Jun", "июнь");
        put("Jul", "июль");
        put("Aug", "август");
    }};

    public void SendLetter(){
        MakeLetterString();
        String address ="annashabaeva953@gmail.com";
        String subject = GetTheme();
        String message = GetMessage();

        System.out.println(address);
        System.out.println(subject);
        System.out.println(message);
    }

    public String GetEmail() {
        return emailAddress;
    }

    public String GetTheme() {
        return themeLine;
    }

    public String GetMessage(){
        return hcsLine + electLine + sumLine;
    }

    public String GetDetail(){
        return detail;
    }

    public void SetData(HcsManager.InnerCurrentData innerCurrentData){
        hcsValue = innerCurrentData.hcsData;
        electValue = innerCurrentData.sumElectData;
        sumValue = innerCurrentData.sumData;

        dayTariffValue = innerCurrentData.dayTariffValue;
        nightTariffValue = innerCurrentData.nightTariffValue;
        prElDayValue = innerCurrentData.prElDayValue;
        prElNightValue = innerCurrentData.prElNightValue;
        curElDayValue = innerCurrentData.dayElectData;
        curElNightValue = innerCurrentData.nightElectData;

    }

    private void MakeLetterString(){
        Date date = new Date();
        String s = date.toString();
        System.out.println(s);
        String[] stringBuffer = s.split(regex);
        int bufSize = stringBuffer.length;
        themeLine = "Расчет за " + MONTH_MAP.get(stringBuffer[1]) + " " + stringBuffer[bufSize - 1];
        hcsLine = "ЖКУ за " + "август" + " = " + hcsValue + " руб." + '\n';
        electLine = "Электричество за " + MONTH_MAP.get(stringBuffer[1]) + " " + stringBuffer[bufSize - 1] + " = " + electValue + " руб." + '\n';
        sumLine = "Итого за " + MONTH_MAP.get(stringBuffer[1])  + " "+ stringBuffer[bufSize - 1]  + " = " + sumValue + " руб." + '\n';

        detail = " ( " + curElDayValue + " - " + prElDayValue +" ) * " + dayTariffValue + " + " +
                " ( " + curElNightValue + " - " + prElNightValue +" ) * " + nightTariffValue + " = " + electValue + '\n' +
                electValue + " + " + hcsValue + " = " + sumValue;
    }
}
