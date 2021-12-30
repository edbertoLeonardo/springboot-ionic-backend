package com.example.cursomodelagemconceitual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cursomodelagemconceitual.domain.Categoria;
import com.example.cursomodelagemconceitual.repositories.CategoriaRepository;
import com.example.cursomodelagemconceitual.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria categoria){
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}
}
