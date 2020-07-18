package com.sdm.ims.controller;

import com.sdm.ims.entity.UnitOfMeasurement;
import com.sdm.ims.repository.UnitOfMeasurementRepository;
import org.aspectj.weaver.tools.cache.AsynchronousFileCacheBacking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.swing.text.html.Option;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/uom")
public class UnitOfMeasurementController {
    @Autowired
    UnitOfMeasurementRepository repository;
    @GetMapping("")
    public ResponseEntity<MessageResponse> welcome() {
        MessageResponse message = new MessageResponse("Welcome!", "Never give up to be a warrior.");
        return ResponseEntity.ok(message);
    }

    @GetMapping("/hello/{name}")
    public ResponseEntity<String> helloWorld(@PathVariable("name")String name){
        return ResponseEntity.ok("Hello "+name);
    }

    @GetMapping("{id}")
    public ResponseEntity<UnitOfMeasurement> getById(@PathVariable("id")int id) throws Exception {
        Optional<UnitOfMeasurement> result=repository.findById(id);
        if(result.isEmpty()){
            throw new Exception("no data with id : "+id);
        }

        return ResponseEntity.ok(result.get());
    }

    @GetMapping("all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("paging")
    public ResponseEntity<?> getPaging(@RequestParam("page")int page,@RequestParam("size")int size){
        Page<UnitOfMeasurement> result=repository.findAll(PageRequest.of(page,size));
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody UnitOfMeasurement request){
        try{
            UnitOfMeasurement result=repository.save(request);
            return ResponseEntity.ok(result);
        }catch(Exception ex){
            throw ex;
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody UnitOfMeasurement request,@PathVariable("id")int id)throws Exception{
        if(repository.findById(id).isEmpty()){
            throw new Exception("No data with id : "+id);
        }
        try{
            UnitOfMeasurement result=repository.save(request);
            return ResponseEntity.ok(result);
        }catch(Exception ex){
            throw ex;
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity patchData(@RequestBody Map<String,Object> request,@PathVariable("id")int id)throws Exception{
        Optional<UnitOfMeasurement> data=repository.findById(id);
        if(data.isEmpty()){
            throw new Exception("No data with id : "+id);
        }
        UnitOfMeasurement patchData=data.get();
        if(request.containsKey("code")){
            patchData.setCode(request.get("code").toString());
        }
        if(request.containsKey("name")){
            patchData.setName(request.get("name").toString());
        }
        if(request.containsKey("description")){
            patchData.setDescription(request.get("description").toString());
        }
        patchData=repository.save(patchData);
        return ResponseEntity.ok(patchData);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id")int id)throws Exception{
        Optional<UnitOfMeasurement> data=repository.findById(id);
        if(data.isEmpty()){
            throw new Exception("No data with id : "+id);
        }
        try{
            repository.deleteById(id);
            return ResponseEntity.ok("Delete success!");
        }catch(Exception ex){
            throw ex;
        }
    }
}
