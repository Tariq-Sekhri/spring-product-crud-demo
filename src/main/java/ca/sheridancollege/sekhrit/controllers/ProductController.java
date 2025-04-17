package ca.sheridancollege.sekhrit.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ca.sheridancollege.sekhrit.beans.Product;
import ca.sheridancollege.sekhrit.database.DatabaseAccess;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final DatabaseAccess da;

    @GetMapping
    public List<Product> getAll() {
        return da.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getOne(@PathVariable int id) {
        Product p = da.getOne(id);
        return p == null
            ? ResponseEntity.notFound().build()
            : ResponseEntity.ok(p);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Product p) {
        da.postOne(p);
        return ResponseEntity.status(201).body("Created successfully");
    }

    @PostMapping("/batch")
    public ResponseEntity<String> createBatch(@RequestBody List<Product> list) {
        da.postCollection(list);
        return ResponseEntity.status(201).body("Batch created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Product p) {
        p.setId(id);
        da.putOne(p);
        return ResponseEntity.ok("Updated successfully");
    }

    @PutMapping("/batch")
    public ResponseEntity<String> updateBatch(@RequestBody List<Product> list) {
        da.putCollection(list);
        return ResponseEntity.ok("Batch updated successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patch(@PathVariable int id, @RequestBody Product p) {
        p.setId(id);
        da.patchOne(p);
        return ResponseEntity.ok("Patched successfully");
    }

    @PatchMapping("/batch")
    public ResponseEntity<String> patchBatch(@RequestBody List<Product> list) {
        da.patchCollection(list);
        return ResponseEntity.ok("Batch patched successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        da.deleteOne(new Product(id));
        return ResponseEntity.ok("Deleted successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        da.deleteCollection();
        return ResponseEntity.ok("All deleted successfully");
    }

}
