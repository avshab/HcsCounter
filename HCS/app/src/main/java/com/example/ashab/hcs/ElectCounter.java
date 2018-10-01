package com.example.ashab.hcs;

public class ElectCounter {



    ElectCounter(){
        innerElectricData = new InnerElectricData();
    }

    public class InnerElectricData{
        double dayTariff = 0.0;
        double nightTariff = 0.0;
        int previousDaytimeData = 0;
        int previousNightData = 0;

        private int currentDaytimeData = 0;
        private int currentNightData = 0;
        private double daytimeSum = 0.0;
        private double nightSum = 0.0;
    }

    InnerElectricData innerElectricData;

    public void SetNightData( int night_data ){
        innerElectricData.currentNightData = night_data;
        CountElectric();
    }

    public void SetDaytimeData( int data ){
        innerElectricData.currentDaytimeData = data;
        CountElectric();
    }
    InnerElectricData GetForRefreshData(){
        return innerElectricData;
    }

    public void SetTariff( double day_tariff, double night_tariff ){
        innerElectricData.dayTariff = day_tariff;
        innerElectricData.nightTariff = night_tariff;
        CountElectric();
    }

    public void SetPreviousData( int day_data, int night_data ){
        innerElectricData.previousDaytimeData = day_data;
        innerElectricData.previousNightData = night_data;
        CountElectric();
    }


    public double GetNightSum(){
        return innerElectricData.nightSum;
    }

    public double GetDaytimeSum(){
        return innerElectricData.daytimeSum;
    }

    public double GetSum(){
        return innerElectricData.nightSum + innerElectricData.daytimeSum;
    }

    private void CountElectric(){

        innerElectricData.daytimeSum = innerElectricData.dayTariff * ( innerElectricData.currentDaytimeData <= innerElectricData.previousDaytimeData ? 0.0 : (innerElectricData.currentDaytimeData - innerElectricData.previousDaytimeData) );

        innerElectricData.nightSum = innerElectricData.nightTariff * ( innerElectricData.currentNightData <= innerElectricData.previousNightData ? 0.0 : (innerElectricData.currentNightData - innerElectricData.previousNightData) );
    }
}
