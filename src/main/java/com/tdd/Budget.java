package com.tdd;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * Author:Lance Date:2018/6/9 Description:
 */
public class Budget {

  private TreeMap<Date, Integer> budgetSetting = new TreeMap<Date, Integer>();

  public Budget() throws ParseException {
    DateFormat sdf = new SimpleDateFormat("yyyyMM");

    budgetSetting.put(sdf.parse("201806"), 300);
    budgetSetting.put(sdf.parse("201807"), 310);

  }

  public int query(String szStart, String szEnd) throws ParseException {
    DateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    Date start = sdf.parse(szStart);
    Date end = sdf.parse(szEnd);
    int result = 0;

    for (Map.Entry<Date, Integer> entry : budgetSetting.entrySet()) {
//      DateFormat sdf2 = new SimpleDateFormat("yyyyMM");
//      if(sdf2.format(start).equals(sdf.format(entry.getKey())))

//      Calendar calendar = Calendar.getInstance();
//      calendar.setTime(entry.getKey());
//      int month = calendar.get(Calendar.MONTH) + 1;
//      int year = calendar.get(Calendar.YEAR);
//      YearMonth yearMonth = YearMonth.of(year, month);

      if (start.compareTo(entry.getKey()) * entry.getKey().compareTo(end) >= 0) {
        result += entry.getValue();
      } else {
        if(yearMonthEqual(entry.getKey(), start)){
          int daysBetween = (int) days(start, lastDayOfMonth(start));//Caculate days by date according to month
          int unit = getUnit(entry.getKey(), entry.getValue());
          result += (daysBetween + 1 ) * unit;;
        }else if(yearMonthEqual(entry.getKey(), end)){

          int daysBetween = (int) days(firstDayOfMonth(end), end);//Caculate days by date according to month
          int unit = getUnit(entry.getKey(), entry.getValue());
          result += (daysBetween + 1) * unit;;
        }else if(yearMonthEqual(entry.getKey(), start) && yearMonthEqual(entry.getKey(), end)) {

          int daysBetween = (int) days(firstDayOfMonth(end),
              end);//Caculate days by date according to month
          int unit = getUnit(entry.getKey(), entry.getValue());
          result += (daysBetween) * unit;
          ;
        }
      }
//      DateFormat yyyyMM = new SimpleDateFormat("yyyyMM");
//      String startDateString = new SimpleDateFormat("yyyyMM").format(start);
//      String endDateString = new SimpleDateFormat("yyyyMM").format(end);
//
//      //SAME
//      if (startDateString.equals(yyyyMM.format(entry.getKey()))
//          && endDateString.equals(yyyyMM.format(entry.getKey()))) {
//        int daysBetween = (int) days(start, end);
//        int unit = getUnit(entry.getKey(), entry.getValue());
//        return (daysBetween + 1) * unit;
//      }

//      Calendar startCal = Calendar.getInstance();
//      startCal.setTime(start);
//      startCal.add(Calendar.DAY_OF_YEAR,1);
//      startCal.
//      while(calendar.get(Calendar.YEAR) == )


    }

    return result;
  }

//  public Date lastDayOfMonth(Date start){
//
//  }

  public Date lastDayOfMonth(Date start){
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(start);
    calendar.add(Calendar.MONTH,1);
    calendar.set(Calendar.DAY_OF_MONTH,1);

    calendar.add(Calendar.DAY_OF_MONTH,-1);
    return calendar.getTime();
  }

  public Date firstDayOfMonth(Date end) throws ParseException {
    String startDateString = new SimpleDateFormat("yyyyMM").format(end);
    startDateString = startDateString + "01";
    return new SimpleDateFormat("yyyyMMdd").parse(startDateString);
  }

  private int getUnit(Date yyyyMM,int fullMoney) {

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(yyyyMM);
    int month = calendar.get(Calendar.MONTH) + 1;
    int year = calendar.get(Calendar.YEAR);

    YearMonth yearMonth = YearMonth.of(year, month);


    return fullMoney / yearMonth.lengthOfMonth();
  }

  private long days(Date start, Date end) {
    DateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    long diff = end.getTime() - start.getTime();
    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
  }

  private boolean yearMonthEqual(Date aDate, Date bDate){
    Calendar aCal = Calendar.getInstance();
    aCal.setTime(aDate);
    int aMonth = aCal.get(Calendar.MONTH) + 1;
    int aYear = aCal.get(Calendar.YEAR);

    Calendar bCal = Calendar.getInstance();
    bCal.setTime(bDate);
    int bMonth = bCal.get(Calendar.MONTH) + 1;
    int bYear = bCal.get(Calendar.YEAR);

    if(aMonth==bMonth && aYear==bYear){
      return  true;
    }
    return false;
  }
}
