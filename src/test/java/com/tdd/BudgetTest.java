package com.tdd;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Assert;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Author:Lance Date:2018/6/9 Description:
 */
public class BudgetTest {

  @Test
  public void fullmonth_20180601_20180630() throws ParseException {

    Budget budget = new Budget();
    assertThat(budget.query("20180601","20180630")).isEqualTo(300);
  }

  @Test
  public void fullmonth_20180615_20180630() throws ParseException {

    Budget budget = new Budget();
    assertThat(budget.query("20180615","20180630")).isEqualTo(160);
  }


  @Test
  public void fullmonth_20180701_20180731() throws ParseException {
    Budget budget = new Budget();
    assertThat(budget.query("20180701","20180731")).isEqualTo(310);
  }

  @Test
  public void fullmonth_20180701_20180730() throws ParseException {
    Budget budget = new Budget();
    assertThat(budget.query("20180701","20180730")).isEqualTo(300);
  }

  @Test
  public void fullmonth_20180715_20180730() throws ParseException {
    Budget budget = new Budget();
    assertThat(budget.query("20180715","20180730")).isEqualTo(160);
  }

//  @Test
//  public void fullmonth_20180601_20180730() throws ParseException {
//    Budget budget = new Budget();
//    assertThat(budget.query("20180601","20180730")).isEqualTo(600);
//  }
}
