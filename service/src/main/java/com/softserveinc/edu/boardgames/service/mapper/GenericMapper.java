package com.softserveinc.edu.boardgames.service.mapper;

public interface GenericMapper<T, V> {
	public T toDTO(V v);
	
	public V toEntity(T t);
}
