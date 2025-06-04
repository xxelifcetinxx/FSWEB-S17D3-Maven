package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private Map<Integer, Koala> koalas = new HashMap<>();

    @GetMapping
    public Collection<Koala> getAll() {
        return koalas.values();
    }

    @GetMapping("/{id}")
    public Koala getById(@PathVariable int id) {
        Koala koala = koalas.get(id);
        if (koala == null) {
            throw new ZooException("Koala not found", HttpStatus.NOT_FOUND);
        }
        return koala;
    }

    @PostMapping
    public Koala create(@RequestBody Koala koala) {
        if (koala.getId() == 0 || koala.getName() == null) {
            throw new ZooException("Invalid koala data", HttpStatus.BAD_REQUEST);
        }
        koalas.put(koala.getId(), koala);
        return koala;
    }

    @PutMapping("/{id}")
    public Koala update(@PathVariable int id, @RequestBody Koala koala) {
        if (!koalas.containsKey(id)) {
            throw new ZooException("Koala not found", HttpStatus.NOT_FOUND);
        }
        koala.setId(id);
        koalas.put(id, koala);
        return koala;
    }

    @DeleteMapping("/{id}")
    public Koala delete(@PathVariable int id) {
        Koala removed = koalas.remove(id);
        if (removed == null) {
            throw new ZooException("Koala not found", HttpStatus.NOT_FOUND);
        }
        return removed;
    }
}
