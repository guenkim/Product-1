package com.study.user.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.common.vo.BaseSttVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "TB_USER")

public class UserVO extends BaseSttVO {

    private static final long serialVersionUID = 1L;

    @Id
    @Size(min = 6, max = 36, message = "로그인 ID는 필수이며, min6, max=36 입니다.")
    @Column(name = "LOGINID", length = 36, nullable = false, unique = true)
    private String loginid;


    @Size(min = 2, max = 128, message = "사용자 이름은 필수이며, min=2, max=36 입니다.")
    @Column(name = "USERNM", length = 128, nullable = false)
    private String userName;


    @JsonIgnore
    @Column(name = "PWD", length = 128, nullable = false)
    private String password;

}
