package datetime;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BulgarianWorkingDayTemporalQuery implements TemporalQuery<Boolean> {
	public static final int[][][] holidaysFrom2015 = {
		{
			{1,2,3,4,10,11,17,18,25,31},
			{1,7,8,14,15,21,22,28},
			{1,2,3,7,8,14,15,22,28,29},
			{4,5,10,11,12,13,18,19,25,26},
		},
	};
	
	
	
	public static final Map<Integer, List<List<Integer>>> officialHolidays;
	
	static {
		officialHolidays = new HashMap<>();
		for(int year = 0; year < holidaysFrom2015.length; year++) {
			List<List<Integer>> currentYear = new ArrayList<List<Integer>>();
			officialHolidays.put(year + 2015, currentYear);
			int[][] yearHolidays = holidaysFrom2015[year];
			for(int[] monthHolidays: yearHolidays) {
				List<Integer> currentMonth = new ArrayList<Integer>();
				currentYear.add(currentMonth);
				for(int holiday: monthHolidays) {
					currentMonth.add(holiday);
				}
			}
		}
	}
	
	@Override
	public Boolean queryFrom(TemporalAccessor date) {
		 int year = date.get(ChronoField.YEAR);
		 int month = date.get(ChronoField.MONTH_OF_YEAR);
		 int day   = date.get(ChronoField.DAY_OF_MONTH);
		 boolean isHoliday = false;
		 try {
			 isHoliday = officialHolidays.get(year).get(month-1).contains(day);
		 } catch (NullPointerException e) {
			 
		 }
		 return !isHoliday;
	}
	
	public static void main(String[] args){
		LocalDate april23 = LocalDate.of(2015, Month.APRIL, 23);
		System.out.println(april23.query(new BulgarianWorkingDayTemporalQuery()));
		LocalDate april18 = LocalDate.of(2015, Month.APRIL, 18);
		System.out.println(april18.query(new BulgarianWorkingDayTemporalQuery()));
	}

}
