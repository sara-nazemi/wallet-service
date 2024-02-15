package com.example.bootcampwalletservice.models.dtoes;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@MappedSuperclass
public abstract class BaseDto {
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date lastModifiedDate;
    @Version
    private Integer version;
}
