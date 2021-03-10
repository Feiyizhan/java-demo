package io.github.feiyizhan.pojo;

import lombok.Data;

import java.time.LocalTime;

/**
 * 会议信息实体
 * @author 徐明龙 XuMingLong 2021-03-04
 */
@Data
public class MeetingInfo {

    /**
     * 会议编号
     * @author 徐明龙 XuMingLong 2021-03-04
     */
    private int number;

    /**
     * 会议开始时间
     * @author 徐明龙 XuMingLong 2021-03-10
     */
    private LocalTime begin;

    /**
     * 会议结束四号机
     * @author 徐明龙 XuMingLong 2021-03-10
     */
    private LocalTime end;

    /**
     * 分钟持续时间
     * @author 徐明龙 XuMingLong 2021-03-10
     */
    private long durationMinutes;

}
