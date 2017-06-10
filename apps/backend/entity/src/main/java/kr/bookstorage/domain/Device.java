package kr.bookstorage.domain;

import kr.bookstorage.domain.code.DeviceOs;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by ksb on 2017. 6. 10..
 */
@Entity
@Table(name = "DEVICE")
@Data
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDX")
    private Long idx;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "DEVICE_OS")
    @Enumerated(EnumType.STRING)
    private DeviceOs deviceOs;

    @Column(name = "NOTICE_ON")
    private boolean noticeOn;

}
