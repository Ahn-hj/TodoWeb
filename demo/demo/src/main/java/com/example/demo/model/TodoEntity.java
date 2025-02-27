package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Todo")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; // 이 오브젝트의 아이디
    //@GenericGenerator(name="system-uuid", strategy = "uuid") GenericGenerator 이제 지원하지않음 
    private String userId; // 이 오브젝트를 생성한 유저의 아이디
    private String title; // Todo 타이틀 예) 운동 하기
    private boolean done; // true - todo를 완료한 경우(checked)
}