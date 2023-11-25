package com.kh.countingBell.controller;

import com.kh.countingBell.domain.ResComment;
import com.kh.countingBell.service.ResCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/*")
public class ResCommentController {

    @Autowired
    private ResCommentService resComment;

    @GetMapping("/comment")
    public ResponseEntity<List<ResComment>> showResCommentList() {
        return ResponseEntity.status(HttpStatus.OK).body(resComment.showAllResComment());
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<ResComment> showResComment(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(resComment.showResComment(id));
    }

    @PostMapping("/comment")
    public ResponseEntity<ResComment> createResComment(@RequestBody ResComment vo){
        return ResponseEntity.status(HttpStatus.OK).body(resComment.createResComment(vo));
    }

    @PutMapping("/comment")
    public ResponseEntity<ResComment> updateResComment(@RequestBody ResComment vo){
        ResComment result = resComment.updateResComment(vo);
        if(result!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<ResComment> deleteResComment(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(resComment.deleteResComment(id));
    }



}
