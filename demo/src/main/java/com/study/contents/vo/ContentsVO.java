package com.study.contents.vo;


import com.study.common.vo.BaseSttVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = "TB_CONTENTS")
@SequenceGenerator(
        name = "SEQ_CONTENT_GEN",
        sequenceName = "SEQ_CONTENT",
        initialValue = 1,
        allocationSize = 1
)
@NoArgsConstructor
public class ContentsVO extends BaseSttVO {
    private static final long serialVersionUID = 1L;
/**
 id를 입력 받는다 , IdsValidator를 사용하여 입력값 검증
 **/
    /**
     * @Id
     * @NotBlank
     * @Ids
     * @Column(name="CONTETNT_ID" , nullable = false, unique = true)
     * private String contentId;
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTENT_GEN")
    @Column(name = "CONTETNT_ID", nullable = false, unique = true)
    private int contentId;
    @NotBlank
    @Length(max = 128)
    @Column(name = "CONTETNT_NM", length = 128, nullable = false)
    private String contentNm;

    @Column(name = "CONTETNT", nullable = true)
    @Lob
    private String content;

    public ContentsVO(String contentNm, String content) {
        this.contentNm = contentNm;
        this.content = content;
    }

    public ContentsVO(int contentId, String contentNm, String content) {
        this.contentId = contentId;
        this.contentNm = contentNm;
        this.content = content;
    }

/**
 @OneToMany(mappedBy = "board" , fetch = FetchType.LAZY)
 @JsonManagedReference private List<ArticleVO> articleList;
 **/
}
