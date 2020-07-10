package com.example.tc.utils

import android.util.Log
import java.text.SimpleDateFormat

class DateTimeUtil {
    /* fun formateDate(dateString : String):String{
         try {
             var format : DateFormat= SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
            // DateFormat format = new SimpleDateFormat(, );
             var date: Date= format.parse(dateString);
             var format1 : DateFormat= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

             // var formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
             var formattedDate = format1.format(date);
             return formattedDate;
         } catch (e: Exception) {
             e.printStackTrace();
         }
         return "";
     }*/
    fun formateDate(dateString: String): String {
        val parts = dateString.split("T");
        return parts[0]
    }

    fun compareDates(date1: String, date2: String): Int {

        /*  try {
              val formatter = SimpleDateFormat("dd/MM/yyyy")
             // val str1 = date1
              val date1 = formatter.parse(date1)
             // val str2 = "13/10/2013"
              val date2 = formatter.parse(date2)
              if (date1.compareTo(date2) < 0) {
                  Log.d("Date:","greater"+date2)
                  //println("date2 is Greater than my date1")
              }else{
                  Log.d("Date:","greater"+date1)

              }
          } catch (e1: ParseException) {
              e1.printStackTrace()
          }
          return 0*/

        try {


            val sdf = SimpleDateFormat("dd/MM/yyyy");
            val date1 = sdf.parse(date1);
            val date2 = sdf.parse(date2);
            val i = date1.compareTo(date2);
            if (date1<date2){
                return -1
                Log.d("Date:","greater"+date2)
            }else if(date1>date2){
                return  1
                Log.d("Date:","greater"+date1)
            }else if(date1 == date2){
                return 2
                Log.d("Date:","Equal"+date2)
            }
          /*  switch(i) {
                case - 1: //date1<date2 = -1
                return date2;
                case 1: //date1>date2 = 1
                return sDate1;
                case 0: //date1==date2= 0
                default:
                return sDate2;
            }*/
        } catch (e:Exception) {
            e.printStackTrace()
        }
        Log.d("Date:","no data"+0)
        return 0

    }
}