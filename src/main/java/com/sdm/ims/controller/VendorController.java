package com.sdm.ims.controller;

import com.sdm.ims.entity.Vendor;
import com.sdm.ims.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    VendorRepository repository;

    @GetMapping("/hello/{name}")
    public ResponseEntity hello(@PathVariable("name")String name)
    {
        return ResponseEntity.ok("Hello "+name);
    }

    @GetMapping("{id}")
    public ResponseEntity select(@PathVariable("id")int id)throws Exception
    {
        Optional<Vendor> v=repository.findById(id);
        if (v.isEmpty())
            throw new Exception("no found");
        return ResponseEntity.ok(v.get());
    }

    @GetMapping("all")
    public ResponseEntity<?> selectall()
    {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody Vendor request)
    {
        try {
            Vendor v = repository.save(request);
            return ResponseEntity.ok(v);
        }catch (Exception ex){
            throw ex;}
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id")int id)throws Exception
    {
        if (repository.findById(id).isEmpty())
            throw new Exception("no found");
        repository.deleteById(id);
        return ResponseEntity.ok("delete success");
    }

    @PatchMapping("{id}")
    public ResponseEntity update(@RequestBody Map<String,Object> request,@PathVariable("id")int id)throws Exception
    {
        Optional<Vendor> v=repository.findById(id);
        if (v.isEmpty())
            throw new Exception("no found");
        Vendor vData=v.get();
        if (request.containsKey("code"))
            vData.setCode(request.get("code").toString());
        if (request.containsKey("name"))
            vData.setName(request.get("name").toString());
        vData=repository.save(vData);
        return ResponseEntity.ok(vData);
    }

    @PutMapping("{id}")
    public ResponseEntity update1(@RequestBody Vendor request,@PathVariable("id")int id)throws Exception
    {
        Optional<Vendor> v=repository.findById(id);
        if (v.isEmpty())
            throw new Exception("no found");
        request.setId(id);
        Vendor vData=repository.save(request);
        return ResponseEntity.ok(vData);
    }

}
