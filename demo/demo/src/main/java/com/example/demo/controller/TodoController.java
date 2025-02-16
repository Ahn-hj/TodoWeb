package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo")
public class TodoController {
	@Autowired
	private TodoService service;
	
	 @GetMapping("/test")
	    public ResponseEntity<?> testTodo() {
	        String str = service.testService();
	        List<String> list = new ArrayList<>();
	        list.add(str);
	        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
	        return ResponseEntity.ok().body(response);
	    }

	
	  @PostMapping
	    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
	        try {
	            //임시 유저 아이디
	            String temporaryUserId = "temporary-user";

	            //TodoEntity로 변환
	            TodoEntity entity = TodoDTO.toEntity(dto);
	            // entity id -> null로 초기화
	            entity.setId(null);
	            entity.setUserId(temporaryUserId);
	            //서비스를 이용해 todo엔티티 생성
	            List<TodoEntity> entities = service.create(entity);
	            // entities를 dto 리스트로 변환
	            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

	            // 응답 생성
	            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
	            return ResponseEntity.ok().body(response);
	        } catch (Exception e) {
	            String error = e.getMessage();
	            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
	            return ResponseEntity.badRequest().body(response);
	        }
	    }

	    @GetMapping
	    public ResponseEntity<?> retrieveTodoList() {
	        String temporaryUserId = "temporary-user";
	        List<TodoEntity> entities = service.retrieve(temporaryUserId);
	        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
	        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
	        return ResponseEntity.ok().body(response);
	    }

	    @PutMapping
	    public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto) {
	        String temporaryUserId = "temporary-user";
	        TodoEntity entity = TodoDTO.toEntity(dto);
	        entity.setUserId(temporaryUserId);
	        List<TodoEntity> entities = service.update(entity);
	        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
	        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
	        return ResponseEntity.ok().body(response);
	    }

	    @DeleteMapping
	    public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto) {
	        try {
	            String temporaryUserId = "temporary-user";
	            TodoEntity entity = TodoDTO.toEntity(dto);
	            entity.setUserId(temporaryUserId);
	            List<TodoEntity> entities = service.delete(entity);
	            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
	            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
	            return ResponseEntity.ok().body(response);
	        } catch (Exception e) {
	            String error = e.getMessage();
	            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
	            return ResponseEntity.badRequest().body(response);
	        }
	    }
	}