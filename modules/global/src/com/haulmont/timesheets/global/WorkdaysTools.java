
package com.haulmont.timesheets.global;

import com.haulmont.timesheets.entity.DayOfWeek;
import com.haulmont.timesheets.service.ProjectsService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.util.Date;

/**
 * @author gorelov
 */
@ManagedBean(WorkdaysTools.NAME)
public class WorkdaysTools {

    public static final String NAME = "ts_WorkdaysTools";

    @Inject
    protected WorkTimeConfigBean workTimeConfigBean;
    @Inject
    protected ProjectsService projectsService;

    public boolean isHoliday(Date date) {
        return !projectsService.getHolidaysForPeriod(date, date).isEmpty();
    }

    public boolean isWeekend(Date date) {
        DayOfWeek day = DayOfWeek.fromCalendarDay(DateTimeUtils.getCalendarDayOfWeek(date));
        return workTimeConfigBean.getWeekends().contains(day);
    }

    public boolean isWorkday(Date date) {
        return !isWeekend(date) && !isHoliday(date);
    }
}