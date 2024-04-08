package com.msa.template.order.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditEntity implements Serializable {

    @Column(name = "deleted", nullable = false)
    @Comment("삭제여부")
    private boolean deleted = false;

    @CreatedBy
    @Column(name = "create_user", nullable = false, updatable = false)
    @Comment("등록자")
    protected Long createUser;

    @CreatedDate
    @Column(name = "create_date", nullable = false, updatable = false)
    @Comment("등록일자")
    protected LocalDateTime createDate;

    @Comment("수정자")
    @LastModifiedBy protected Long updateUser;

    @LastModifiedDate
    @Column(name = "update_date")
    @Comment("수정일자")
    protected LocalDateTime updateDate;
}
