package com.example.ashab.hcs;


public class HcsManager {
    ElectCounter electCounter;
    HcsCounter hcsCounter;

    static int previousDaytimeData = 0;
    static int previousNightData = 0;

   class InnerCurrentData{
        String sumData;
        String hcsData;
        String dayElectData;
        String nightElectData;
        String sumElectData;

       String dayTariffValue = "3.61";
       String nightTariffValue = "2.09";
       String prElDayValue;
       String prElNightValue;
   }

    InnerCurrentData innerCurrentData;
    HcsManager(){
        electCounter = new ElectCounter();
        hcsCounter = new HcsCounter();
        innerCurrentData = new InnerCurrentData();
    }

    public void SetDayPreviousNightData(String data){
        innerCurrentData.prElNightValue = data;
    }

    public void SetDayPreviousDaytimeData(String data){
        innerCurrentData.prElDayValue = data;
    }

    public void SetCurrentNightData(String data){
        innerCurrentData.nightElectData = data;
        electCounter.SetNightData((int)GetNumFromString(data));
        RefreshData();
    }

    public void SetCurrentDaytimeData(String data){
        innerCurrentData.dayElectData = data;
        electCounter.SetDaytimeData((int)GetNumFromString(data));
        RefreshData();
    }

    public void SetHcs(String data){
        hcsCounter.SetHcs(GetNumFromString(data));
        RefreshData();
    }


    private void RefreshData(){
        electCounter.SetPreviousData((int)GetNumFromString(innerCurrentData.prElDayValue), (int)GetNumFromString(innerCurrentData.prElNightValue));
        electCounter.SetTariff(GetNumFromString(innerCurrentData.dayTariffValue), GetNumFromString(innerCurrentData.nightTariffValue));
        //innerCurrentData.dayElectData = String.valueOf(electCounter.GetDaytimeSum());
        //innerCurrentData.nightElectData = String.valueOf(electCounter.GetNightSum());
        innerCurrentData.sumElectData = String.valueOf(electCounter.GetNightSum() + electCounter.GetDaytimeSum());
        innerCurrentData.sumData = String.valueOf(electCounter.GetSum() + hcsCounter.GetHcs());
        innerCurrentData.hcsData = String.valueOf(hcsCounter.GetHcs());
    }

    private double GetNumFromString( String data ) {
        try {
            return Double.valueOf(data);
        } catch (NumberFormatException ex) {

            System.out.println("NumberFormatException");
        }
        return 0;
    }


    public HcsLetter GetLetter(){
        HcsLetter hcsLetter = new HcsLetter();
        hcsLetter.SetData(innerCurrentData);
        hcsLetter.SendLetter();
        return hcsLetter;
    }

}
