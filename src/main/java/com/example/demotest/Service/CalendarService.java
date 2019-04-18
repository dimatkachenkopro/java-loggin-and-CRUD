package com.example.demotest.Service;

import com.example.demotest.domain.Application;
import com.example.demotest.repository.ApplicationRepo;
import com.example.demotest.repository.CalendarRepo;
import com.example.demotest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class CalendarService {
    @Autowired
    ApplicationRepo appRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CalendarRepo calendarRepo;
    @Autowired
    UserService userService;
    @Autowired
    ApplicationRepo applicationRepo;

    public void showCalendarsDays(){
        Application app = new Application();

    }
    //Liczy ilosc dniow miedzy dwoma datami urlopa dla danego uzera (Od  - Do)
    public List<Date> getDaysFromFirstToLast(ArrayList<Application> allUserApp) {
        List<Date> allWeekendDays = new ArrayList();
        Date date1;
        Date date2;

        for (Application a : allUserApp) {
            if (a.isActive()) {
                date1 = a.getStartDate();
                date2 = a.getLastDate();
                while (date1.before(date2)) {

                    GregorianCalendar cal = new GregorianCalendar();
                    cal.setTime(date1);
                    cal.add(GregorianCalendar.DATE, 1);
                    date1 = cal.getTime();
                    allWeekendDays.add(date1);
                }
            }
        }
        return  allWeekendDays;
    }
}
