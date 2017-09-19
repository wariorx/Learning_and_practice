window.onload =function ()
{
    //alert("hello, hello, hello");
    var date = new Date();
    var monthDisplay; //stores the month that's currrently being displayed
    var yearDisplay;
    setName(date, -2);//document.getElementsByClassName("name").onload = setName;
    setYear(date, -2);//document.getElementById("year").onload = setYear;
    setDay(date);//document.getElementsByClassName("days").onload = setDay;
    document.getElementById("previous").onclick = previous;
    document.getElementById("next").onclick = next;


    function setName(date, month_number) // sets the month name, according to the date retrieved
    {
      var month = date.getMonth();
      //this if/else deals with the month assignment for prevvious / next
      if(month_number >= 0 && month_number < 12) //should only occur when the method is called from next()/previous(), also prevents -1 from breaking the assignment
          month = month_number;
      else if(month_number == -1) //previous from January, so December of the previous year
      {
          month = 11;
          previousYear(); //yer change
      }

      else if (month_number == 12) //next from december, so January of next year
      {
        month = 0;
        nextYear(); //year change
      }
      //performs the month switch
      switch (month)
      {
      case 0:
        document.getElementById("name").innerHTML = "January";
        monthDisplay = month;
        break;
      case 1:
        document.getElementById("name").innerHTML = "February";
        monthDisplay = month;
        break;
      case 2:
        document.getElementById("name").innerHTML = "March";
        monthDisplay = month;
        break;
      case 3:
        document.getElementById("name").innerHTML = "April";
        monthDisplay = month;
        break;
      case 4:
        document.getElementById("name").innerHTML = "May";
        monthDisplay = month;
        break;
      case 5:
        document.getElementById("name").innerHTML = "June";
        monthDisplay = month;
        break;
      case 6:
        document.getElementById("name").innerHTML = "July";
        monthDisplay = month;
        break;
      case 7:
        document.getElementById("name").innerHTML = "August";
        monthDisplay = month;
        break;
      case 8:
        document.getElementById("name").innerHTML = "September";
        monthDisplay = month;
        break;
      case 9:
        document.getElementById("name").innerHTML = "October";
        monthDisplay = month;
        break;
      case 10:
        document.getElementById("name").innerHTML = "November";
        monthDisplay = month;
        break;
      case 11:
        document.getElementById("name").innerHTML = "December";
        monthDisplay = month;
        break;
      default:
        document.getElementById("name").innerHTML = "Something went wrong";

      }

    }//end of setName

    function setYear(date, year) //sets the year, according to the date retrieved
    {
      if(year == -2) //first call, when the page is loaded
      {
      document.getElementById("year").innerHTML = date.getFullYear();
      yearDisplay = date.getFullYear();
      }
      else //whenever the arrows are used to change the year
      {
        yearDisplay = year;
        document.getElementById("year").innerHTML = yearDisplay;

      }

    } //end of setYear

    function setDay(date) //sets the day marked as current, according to the date retrieved
    {
      //Use Zeller's Rule to match day number to weekdays, might not be used in final version
      /*
        var day = 1;
        var calc_month;
        var first_two = date.getFullYear().substring(0,1);
        var last_two = date.getFullYear().substring(2,3);
        //the following variables are used to map each day of the week to a set of ids in the html
        var mon = 1;
        var tues = 2;
        var wed = 3;
        var thurs = 4;
        var fri = 5;
        var sat = 6;
        var sun = 7;
        calc_month = zeller_setUp(); //this statement sets up the month so that it fits Zeller's rule

        while( day <= 31)
        {
          var zeller = zeller_formula(day, calc_month, first_two, last_two);
          zeller =  Math.round(zeller); // round the result

          if(zeller < 0) //apply appropiate rule when the number is negative
              zeller = ( zeller % (-7) ) + 7;
          else
            zeller = zeller % 7;

          if(zeller == 0)
            {
              document.getElementById(sun).innerHTML = day;
              sun += 7;
            }
          else if (zeller == 1)
          {
            document.getElementById(mon).innerHTML = day;
            mon += 7;
          }
          else if (zeller == 2)
          {
            document.getElementById(mon).innerHTML = day;
            tues += 7;
          }
          else if (zeller == 3)
          {
            document.getElementById(mon).innerHTML = day;
            wed += 7;
          }
          else if (zeller == 4)
          {
            document.getElementById(mon).innerHTML = day;
            thurs += 7;
          }
          else if (zeller == 5)
          {
            document.getElementById(mon).innerHTML = day;
            fri += 7;
          }
          else if (zeller == 6)
          {
            document.getElementById(mon).innerHTML = day;
            sat += 7;
          }
        }
        */
        //the following code calculates the number of days in the month displayed
        var numberOfDays;
        if (monthDisplay == 3 || monthDisplay == 5 || monthDisplay == 8 || monthDisplay == 10)
          numberOfDays = 30;
        else if (monthDisplay == 1)
        {
          var isLeapYear = (yearDisplay % 4 == 0 && yearDisplay % 100 != 0) || (yearDisplay % 400 == 0);
          if (isLeapYear)
            numberOfDays = 29;
          else
            numberOfDays = 28;
        }
        else
        numberOfDays = 31;

        var id = 1;
        for(; id <= numberOfDays; id++) //loop through the days to find current day and set up all the day values
        {

          if(monthDisplay == date.getMonth() && yearDisplay == date.getFullYear()) //current day will only be set if the year and displayed are the same as today's date
            {
              if(date.getDate() == id)
              {
                document.getElementById(id).innerHTML =" <span id = active> " + id + "</span>";
                document.getElementById("active").style.color = "blue"; //change the color of the currrent day
              }
              else //will set the color back to normal when the month / year is not the current one
                  document.getElementById(id).innerHTML = id;
            }
          else //will set the color back to normal when the month / year is not the current one
              document.getElementById(id).innerHTML = id;

        }

        for(; id <= 31; id++) // reset remainging days
        {
          document.getElementById(id).innerHTML = "";
        }

    }//end of set day

    function next() //changes to the next month, makes use of the setName method
    {
      setName(date, ( monthDisplay + 1) ); //set month to next
      setDay(date); //change the active day indicator
    } //end of next

    function previous() //changes to the previous month, makes use of the setNmae method
    {

      setName(date, ( monthDisplay- 1) ); //set month to
      setDay(date); //change the active day indicator
    } //end of previous

    function nextYear() //set the year displayed to be the next one, makes use of setYear
    {
        setYear(date, ( yearDisplay + 1) );
    }//End of nexxt year

    function previousYear() //set the year displayed to be the previous one, males use of setYear
    {
        setYear(date, (yearDisplay - 1) );
    }//end of previous year

/*
    function zeller_setUp() //this function sets up the month to fit zeller's rule's calculation
    {
      var calc_month;
      if(monthDisplay <= 1)
      {
        calc_month = monthDisplay + 11;
      }
      else
      {
        calc_month = monthDisplay - 1;
      }

      return calc_month;
    }

    function zeller_formula (day, calc_month, first_two, last_two)
    {
      var result = day + ( (13 * calc_month - 1) / 5 ) + last_two + (last_two / 4) + (first_two / 4) - (2 * first_two) );
      return result;
    }
    */

}
