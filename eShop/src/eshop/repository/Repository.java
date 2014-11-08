package eshop.repository;

import java.util.Collection;

import eshop.repository.exception.NonExistingItemException;

public interface Repository <K, V extends Identifiable<K>>{
	V getItem(K key);
	Collection<V> getAllItems();
	V addItem(V item);
	V editItem(V item) throws NonExistingItemException;
	V deleteItem(K itemId) throws NonExistingItemException;
	int count();
}
