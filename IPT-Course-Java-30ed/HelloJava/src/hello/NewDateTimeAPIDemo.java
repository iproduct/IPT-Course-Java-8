package hello;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;


public class NewDateTimeAPIDemo {

	public static void main(String[] args) {
		LocalDate today = LocalDate.now();
		System.out.println("Today: " + today);
		LocalDate yearAfterToday = today.plusYears(1);
		System.out.println("After one year: " + yearAfterToday 
				+ "(" + yearAfterToday.getDayOfWeek() + ")");
		LocalDate payday = today.with(TemporalAdjusters.lastDayOfMonth()).minusDays(2);
		System.out.println("Accounting should trnasfer salary at: " + payday 
				+ "(" + payday.getDayOfWeek() + ")");
		LocalDate dateOfBirth = LocalDate.of(2012, Month.MAY, 14);
		LocalDate firstBirthday = dateOfBirth.plusYears(30);
		System.out.println("Birthday: " + payday 
				+ "(" + payday.getDayOfWeek() + ")");
	
	    String input = "17.05.2015";
		try {
		    DateTimeFormatter formatter =
		                      DateTimeFormatter.ofPattern("dd.MM.yyyy");

		    LocalDate date = LocalDate.parse(input, formatter);
		    System.out.printf("%s%n", date.format(formatter));
		}
		catch (DateTimeParseException exc) {
		    System.out.printf("%s is not parsable!%n", input);
		    throw exc;      // Rethrow the exception.
		}
		
		TemporalQuery<TemporalUnit> query = TemporalQueries.precision();
		System.out.printf("LocalDate precision is %s%n",
		                  LocalDate.now().query(query));
		System.out.printf("LocalDateTime precision is %s%n",
		                  LocalDateTime.now().query(query));
		System.out.printf("Year precision is %s%n",
		                  Year.now().query(query));
		System.out.printf("YearMonth precision is %s%n",
		                  YearMonth.now().query(query));
		System.out.printf("Instant precision is %s%n",
		                  Instant.now().query(query));

	}

}
