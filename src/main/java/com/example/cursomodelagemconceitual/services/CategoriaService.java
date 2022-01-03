package com.example.cursomodelagemconceitual.services;

import java.util.Optional;

import com.example.cursomodelagemconceitual.services.exception.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

	public Categoria update(Categoria categoria){
		find(categoria.getId());
		return categoriaRepository.save(categoria);
	}

	public void delete(Integer id){
		find(id);
		try{
			categoriaRepository.deleteById(id);
		}catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
}
