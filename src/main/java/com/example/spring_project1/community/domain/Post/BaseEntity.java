package com.example.spring_project1.community.domain.Post;
//모든 table에 "공통으로" 사용되는 데이터 추가 시간, 수정 시간을 "자동으로" 관리하기 위한 class -> @MappedSuperClass로 처리

import com.fasterxml.jackson.databind.ser.Serializers.Base;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
//자동 생성하므로 생성자가 필요 없음. builder 필요 X
@Getter
//자동으로 시간 값 저장하는 listener를 지정.
@EntityListeners(value = { AuditingEntityListener.class })

abstract class BaseEntity {
    //entity 생성 시 자동으로 생성 시간을 저장한다. 한 번 저장된 생성 시간은 수정되지 않는다.
    @CreatedDate
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;
    //entity 수정 시간. 수정 시간은 update가 가능
    @LastModifiedDate
    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;

}
