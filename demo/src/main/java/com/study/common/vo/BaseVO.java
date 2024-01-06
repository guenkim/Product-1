package com.study.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@MappedSuperclass
public abstract class BaseVO implements Serializable {

    private static final long serialVersionID = 1L;

    @Column(name = "REGID", length = 36, updatable = false)
    protected String regid;


    @Column(name = "MODID", length = 36)
    protected String modid;

    @Column(name = "REGDT", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime regdt;

    @Column(name = "MODDT")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime moddt;

    @PrePersist
    public void prePersist() {
        this.regdt = LocalDateTime.now();
        this.moddt = this.regdt;
    }

    @PreUpdate
    public void preUpdate() {
        this.moddt = LocalDateTime.now();
    }

}
