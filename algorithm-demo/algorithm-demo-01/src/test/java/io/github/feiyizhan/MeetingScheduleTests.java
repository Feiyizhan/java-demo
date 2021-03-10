package io.github.feiyizhan;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;
import io.github.feiyizhan.pojo.MeetingInfo;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间 。你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
 * 返回最多的宣讲场次。
 * @author 徐明龙 XuMingLong 2021-03-04
 */
public class MeetingScheduleTests {


    @Test
    public void test(){
        //构建随机时间区间
        List<MeetingInfo> applyMeetingList = new ArrayList<>();
        int size = 20;
        for(int i=0;i<size;i++){
            MeetingInfo meetingInfo = new MeetingInfo();
            meetingInfo.setNumber(i+10000);
            meetingInfo.setBegin(getRandomTime());
            meetingInfo.setEnd(meetingInfo.getBegin().plusMinutes(RandomUtil.randomLong(180)));
            meetingInfo.setDurationMinutes(ChronoUnit.MINUTES.between(meetingInfo.getBegin(),meetingInfo.getEnd()));
            applyMeetingList.add(meetingInfo);
        }

        Console.log("会议原始数据为：{}",applyMeetingList);
        //先按照会议开始时间
        applyMeetingList.sort(Comparator.comparing(MeetingInfo::getBegin));

        Console.log("排序后的数据为：{}",applyMeetingList);
        List<MeetingInfo> result = new ArrayList<>();
        MeetingInfo preMeeting = null;
        for(MeetingInfo meetingInfo:applyMeetingList){
            if(preMeeting==null){
                preMeeting = meetingInfo;
                result.add(meetingInfo);
            }else{
                //会议开始时间>会议结束时间
                if(meetingInfo.getBegin().isAfter(preMeeting.getEnd())){
                    result.add(meetingInfo);
                    preMeeting = meetingInfo;
                }
            }
        }

        Console.log("最终的数据为：{}",result);
    }

    /**
     * 获取随机时间
     * @author 徐明龙 XuMingLong 2021-03-10
     * @return java.time.LocalTime
     */
    private LocalTime getRandomTime(){
        return LocalTime.of(RandomUtil.randomInt(0,23),RandomUtil.randomInt(0,60),RandomUtil.randomInt(0,60));
    }
}
