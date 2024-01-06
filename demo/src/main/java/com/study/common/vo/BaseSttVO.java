package com.study.common.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@MappedSuperclass
public abstract class BaseSttVO extends BaseVO {

    private static final long serialVersionID = 1L;

    @Type(type = "yes_no")
    @Column(name = "USEYN", length = 1, nullable = false)
    private boolean useyn = true;

    @Column(name = "STTCD", length = 1)
    private String sttcd;

    @PrePersist
    public void prePersist() {
        super.prePersist();
        this.sttcd = "I";
    }

    @PreUpdate
    public void preUpdate() {
        super.preUpdate();
        this.sttcd = "U";
    }

}

