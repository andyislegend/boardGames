package com.softserveinc.edu.boardgames.persistence.entity.mapper;

public interface GenericMapper<T, V> {
	public T toDTO(V v);
	
	public V toEntity(T t);
}
