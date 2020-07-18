package com.sdm.ims.controller;

import com.sdm.ims.entity.ProductType;
import com.sdm.ims.entity.UnitOfMeasurement;
import com.sdm.ims.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/product/type")
public class ProductTypeController {
    @Autowired
    ProductTypeRepository repository;

    @GetMapping("/hello/{name}")
    public ResponseEntity<String> helloWorld(@PathVariable("name")String name){
        return ResponseEntity.ok("Hello "+name);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductType> getById(@PathVariable("id")int id) throws Exception {
        Optional<ProductType> result=repository.findById(id);
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
    public ResponseEntity<?> getPaging(@RequestParam("page")int page, @RequestParam("size")int size){
        Page<ProductType> result=repository.findAll(PageRequest.of(page,size));
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody ProductType request){
        try{
            ProductType result=repository.save(request);
            return ResponseEntity.ok(result);
        }catch(Exception ex){
            throw ex;
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody ProductType request,@PathVariable("id")int id)throws Exception{
        if(repository.findById(id).isEmpty()){
            throw new Exception("No data with id : "+id);
        }
        try{
            ProductType result=repository.save(request);
            return ResponseEntity.ok(result);
        }catch(Exception ex){
            throw ex;
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity patchData(@RequestBody Map<String,Object> request, @PathVariable("id")int id)throws Exception{
        Optional<ProductType> data=repository.findById(id);
        if(data.isEmpty()){
            throw new Exception("No data with id : "+id);
        }
        ProductType patchData=data.get();
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
        Optional<ProductType> data=repository.findById(id);
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
