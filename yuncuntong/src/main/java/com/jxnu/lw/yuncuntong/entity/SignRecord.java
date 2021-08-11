package com.jxnu.lw.yuncuntong.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="signRecord")
public class SignRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)//关闭懒加载,一对多或多对一时，多的那方关闭懒加载
    @JoinColumn(name="userId")
    private User user;

    @Column(name = "time")
    private Date time;

    @Column(name = "result")
    private int result;//签到结果 0 未签到 1 已签到
}
