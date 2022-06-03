package com.web.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.web.api.model.Farmacia;
import com.web.api.repository.FarmaciaRepository;

@RestController
@RequestMapping("/farmacia")
public class FarmaciaController {

	@Autowired
	private FarmaciaRepository farmaciaRepository;

	@GetMapping
	public List<Farmacia> get() {
		return farmaciaRepository.findAll();
	}

	@GetMapping("/{id}")
    public Farmacia getById(@PathVariable Long id) {
		System.out.println(id);
		var farmaciaOptional = farmaciaRepository.findById(id);

        if (farmaciaOptional.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return farmaciaOptional.get();
    }

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Farmacia insert(@RequestBody Farmacia farmacia) {
		return farmaciaRepository.save(farmacia);
	}

	@DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        var farmaciaOptional = farmaciaRepository.findById(id);

        if (farmaciaOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        farmaciaRepository.delete(farmaciaOptional.get());
    }

	@PutMapping("/{id}")
	public Farmacia replaceFarmaciaById(@PathVariable Long id, @RequestBody Farmacia newFarmacia) {
		var farmaciaOptional = farmaciaRepository.findById(id);

		if (farmaciaOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

		newFarmacia.setId(id);

		return farmaciaRepository.save(newFarmacia);
	}

}
